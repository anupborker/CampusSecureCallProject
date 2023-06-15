package com.avb.collageproject;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReportFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReportFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ReportFragment newInstance(String param1, String param2) {
        ReportFragment fragment = new ReportFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    PieChart pieChart;
RecyclerView recyclerView;
    DatabaseReference databaseReference;
    CampusEntryAdapter Adapter;
    ArrayList<CampusEntry> entrylist;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_report, container, false);
        recyclerView = v.findViewById(R.id.cpampus_renty_recycle_view);
        pieChart = v.findViewById(R.id.pie_chart);
        recyclerViewloader();
        retrivepidatafromfirebase();
        return v;
    }

    private void retrivepidatafromfirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        System.out.println("DBrefreance" + ref);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Retrieve data for teachers, staff, and campus entry and aggregate
                // data for the pie chart
                int numStudents = (int) snapshot.child("Students").getChildrenCount();
                int numTeachers = (int) snapshot.child("Teacher").getChildrenCount();
                int numStaff = (int) snapshot.child("Nonteachingstaff").getChildrenCount();;
                int numCampusEntries = (int) snapshot.child("CampusEntry").getChildrenCount();;

                for (DataSnapshot child : snapshot.getChildren()) {
                    String role = child.child("role").getValue(String.class);
                    if (role != null) {
                        switch (role) {
                            case "Teacher":
                                numTeachers++;
                                break;
                            case "Nonteachingstaff":
                                numStaff++;
                                break;
                            case "CampusEntry":
                                numCampusEntries++;
                                break;
                        }
                    }
                }
                updatePieChart(numStudents, numTeachers, numStaff, numCampusEntries);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });
    }
    private void updatePieChart(int numStudents, int numTeachers, int numStaff, int numCampusEntries) {


        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(numStudents, "Students"));
        entries.add(new PieEntry(numTeachers, "Teachers"));
        entries.add(new PieEntry(numStaff, "Staff"));
        entries.add(new PieEntry(numCampusEntries, "Campus Entry"));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextSize(16f);
        dataSet.setValueFormatter(new PercentFormatter(pieChart));


        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private void recyclerViewloader() {
        databaseReference = FirebaseDatabase.getInstance().getReference("CampusEntry");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        entrylist = new ArrayList<CampusEntry>();
        Adapter = new CampusEntryAdapter(getContext(), entrylist);
        recyclerView.setAdapter(Adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                entrylist.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CampusEntry entry = dataSnapshot.getValue(CampusEntry.class);
                    entrylist.add(entry);

                }
                Adapter.notifyDataSetChanged();
                Log.d("AdminDashboard", "onDataChange: entrylist size = " + entrylist.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("AdminDashboard", "onCancelled: " + error.getMessage());
            }
        });

    }


}