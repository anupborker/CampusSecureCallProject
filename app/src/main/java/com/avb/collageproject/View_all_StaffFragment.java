package com.avb.collageproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link View_all_StaffFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class View_all_StaffFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public View_all_StaffFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment View_all_StaffFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static View_all_StaffFragment newInstance(String param1, String param2) {
        View_all_StaffFragment fragment = new View_all_StaffFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyStaffAdapterViewAll Adapter;
    ArrayList<StaffList> entrylist;

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
        View v = inflater.inflate(R.layout.fragment_view_all__staff, container, false);
        recyclerView = v.findViewById(R.id.staff_recycle_view);
        loadStaffRecycleview();
        return v;
    }

    private void loadStaffRecycleview() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Nonteachingstaff");

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
        }
        entrylist = new ArrayList<StaffList>();

        Adapter = new MyStaffAdapterViewAll(requireContext(), entrylist);
        recyclerView.setAdapter(Adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                entrylist.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    StaffList entry = dataSnapshot.getValue(StaffList.class);
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