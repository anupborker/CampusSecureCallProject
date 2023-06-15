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

public class View_all_TeacherFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    public View_all_TeacherFragment() {
        // Required empty public constructor
    }
    public static View_all_TeacherFragment newInstance(String param1, String param2) {
        View_all_TeacherFragment fragment = new View_all_TeacherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyTeacherAdapterViewAll Adapter;
    ArrayList<TeacherList> entrylist;
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
        View v = inflater.inflate(R.layout.fragment_view_all__teacher, container, false);
        recyclerView = v.findViewById(R.id.teacheRecycleview);
        loadTeacherRecycleview();



        return v;
    }


    private void loadTeacherRecycleview() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Teacher");

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
        }
        entrylist = new ArrayList<TeacherList>();

        Adapter = new MyTeacherAdapterViewAll(requireContext(), entrylist);
        recyclerView.setAdapter(Adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                entrylist.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    TeacherList entry = dataSnapshot.getValue(TeacherList.class);
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