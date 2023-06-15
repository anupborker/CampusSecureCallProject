package com.avb.collageproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@linkFragment} subclass.
 * Use the {@link Show_teachers_detailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Show_teachers_detailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Show_teachers_detailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Show_teachers_detailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Show_teachers_detailsFragment newInstance(String param1, String param2) {
        Show_teachers_detailsFragment fragment = new Show_teachers_detailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    TextView showTeacherName;
    TextView showTeacherUID;
    TextView showTeacherDesignation;
    TextView showTeacherDepartment;
    TextView showTeacherContacno;
    TextView showTeacherAddress;
    ImageView showTeacherImage;

    FirebaseDatabase teacherdatabase;
    DatabaseReference teacherdbReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        getParentFragmentManager().setFragmentResultListener("TRuser_ID", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                String  ID = result.getString("TeachersID");
                System.out.println("UID:" + ID);
                ShowTeacherInDataDB(ID);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_show_teachers_details, container, false);
        teacherdatabase = FirebaseDatabase.getInstance();
        teacherdbReference=teacherdatabase.getReference("Teacher");


        showTeacherName = v.findViewById(R.id.teachershowdetailName);
         showTeacherUID =  v.findViewById(R.id.teachershowdetailUID);
         showTeacherDesignation = v.findViewById(R.id.teachershowdetailDesignation);
         showTeacherDepartment = v.findViewById(R.id.teachershowdetailDepartment);
         showTeacherContacno = v.findViewById(R.id.teachershowdetailscontactNo);
         showTeacherAddress = v.findViewById(R.id.teachershowdetailAddress);
        showTeacherImage = v.findViewById(R.id.teachershowdetailImage);


        return v;
    }


    private void ShowTeacherInDataDB(String ID) {
        teacherdatabase = FirebaseDatabase.getInstance();
        System.out.println(ID);
        teacherdbReference=teacherdatabase.getReference("Teacher");


        teacherdbReference.child(ID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();


                String name = String.valueOf(dataSnapshot.child("name").getValue());
                showTeacherName.setText(name);
                String UDI = String.valueOf(dataSnapshot.child("UDI").getValue());
                showTeacherUID.setText(UDI);
                String designation = String.valueOf(dataSnapshot.child("designation").getValue());
                showTeacherDesignation.setText(designation);
                String department = String.valueOf(dataSnapshot.child("department").getValue());
                showTeacherDepartment.setText(department);
                String contactno = String.valueOf(dataSnapshot.child("contactno").getValue());
                showTeacherContacno.setText(contactno);
                String address = String.valueOf(dataSnapshot.child("address").getValue());
                showTeacherAddress.setText(address);


            }


        });
    }
}