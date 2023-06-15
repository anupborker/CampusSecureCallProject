package com.avb.collageproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link show_details_studentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class show_details_studentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public show_details_studentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment show_details_studentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static show_details_studentFragment newInstance(String param1, String param2) {
        show_details_studentFragment fragment = new show_details_studentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    TextView showName;
    TextView showRollno;
    TextView showFaculty;
    TextView showAddress;
    TextView showMobileno;
    TextView showSemester,showAcedemicyear ;

    FirebaseDatabase database;
    DatabaseReference dbReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
       getParentFragmentManager().setFragmentResultListener("user_ID", this, new FragmentResultListener() {
           @Override
           public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

               String  ID = result.getString("ID");
               System.out.println("ROLLNO" + ID);
               ShowStudentInDataDB(ID);

           }
       });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_show_details_student, container, false);
        database = FirebaseDatabase.getInstance();
        dbReference=database.getReference("Students");



        showName = v.findViewById(R.id.studentName);
        showFaculty = v.findViewById(R.id.studentFaculty);
        showRollno = v.findViewById(R.id.studentID);
        showAddress = v.findViewById(R.id.studentAddress);
        showMobileno = v.findViewById(R.id.studentcontactNo);
        showSemester = v.findViewById(R.id.studentSem);
        showAcedemicyear = v.findViewById(R.id.studentAcedemicyear);



        return v;
    }

    private void ShowStudentInDataDB(String ID) {
        database = FirebaseDatabase.getInstance();
        System.out.println(ID);
        dbReference = FirebaseDatabase.getInstance().getReference("Students");
        dbReference.child(ID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();
                String name = String.valueOf(dataSnapshot.child("name").getValue());
                showName.setText(name);
                String rollno = String.valueOf(dataSnapshot.child("rollno").getValue());
                showRollno.setText(rollno);
                String faculty = String.valueOf(dataSnapshot.child("faculty").getValue());
                showFaculty.setText(faculty);
                String address = String.valueOf(dataSnapshot.child("address").getValue());
                showAddress.setText(address);
                String mobileno = String.valueOf(dataSnapshot.child("contacno").getValue());
                showMobileno.setText(mobileno);
                String semester = String.valueOf(dataSnapshot.child("semester").getValue());
                showSemester.setText(semester);
                String acedemicyear = String.valueOf(dataSnapshot.child("acedemicyear").getValue());
                showAcedemicyear.setText(acedemicyear);

            }


        });
    }
}