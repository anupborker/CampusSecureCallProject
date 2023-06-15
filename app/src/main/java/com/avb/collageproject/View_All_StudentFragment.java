package com.avb.collageproject;

import android.content.Context;
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


public class View_All_StudentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public View_All_StudentFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static View_All_StudentFragment newInstance(String param1, String param2) {
        View_All_StudentFragment fragment = new View_All_StudentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyStudentAdapterViewAll Adapter;
    ArrayList<StudentsList> entrylist;
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
        View v = inflater.inflate(R.layout.fragment_view__all__student, container, false);
        recyclerView = v.findViewById(R.id.stdent_viewl_all_recycleview);
        loadStudentRecycleview();



        return v;
    }

    private void loadStudentRecycleview() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Students");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        entrylist = new ArrayList<StudentsList>();

        Adapter = new MyStudentAdapterViewAll(requireContext(), entrylist);
        recyclerView.setAdapter(Adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                entrylist.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    StudentsList entry = dataSnapshot.getValue(StudentsList.class);
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