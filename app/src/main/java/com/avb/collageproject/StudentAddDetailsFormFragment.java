package com.avb.collageproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class StudentAddDetailsFormFragment extends Fragment {




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentAddDetailsFormFragment() {
        // Required empty public constructor
    }



    public static StudentAddDetailsFormFragment newInstance(String param1, String param2) {
        StudentAddDetailsFormFragment fragment = new StudentAddDetailsFormFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    EditText addname;
    EditText addrollno;
    EditText addfaculty;
    EditText addaddress;
    EditText addmobileno;
     Button add;
    Button update;
    Button delete;
    EditText addsemester,addacedemicyear ;

    FirebaseDatabase database;
    DatabaseReference dbReference;
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
        View v = inflater.inflate(R.layout.fragment_student_add_details_form, container, false);

            addname = v.findViewById(R.id.studentNameInput);
            addfaculty = v.findViewById(R.id.studentFacultyInput);
            addrollno = v.findViewById(R.id.studentRollnoInput);
            addaddress = v.findViewById(R.id.studentAddressInput);
            addmobileno = v.findViewById(R.id.studentContactNoInput);
            addsemester = v.findViewById(R.id.studentSemesterInput);
            addacedemicyear = v.findViewById(R.id.studentAcedemicYearInput);

            add = v.findViewById(R.id.studentdbform_AddBtn);
            update = v.findViewById(R.id.studentdbform_Updatebtn);
            delete = v.findViewById(R.id.studentdbform_Deletebtn);



        database = FirebaseDatabase.getInstance();
        dbReference = database.getReference("Students");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = addname.getText().toString();
                String faculty = addfaculty.getText().toString();
                String rollno = addrollno.getText().toString();
                String address = addaddress.getText().toString();
                String mobileno = addmobileno.getText().toString();
                String semester = addsemester.getText().toString();
                String acedemicyear = addacedemicyear.getText().toString();


                AddStudentData Studentdata = new AddStudentData(name,faculty,rollno,address,mobileno,semester,acedemicyear);
                dbReference.child(rollno).setValue(Studentdata);

                Toast.makeText(getActivity(), "Data has been added Succesfully", Toast.LENGTH_SHORT).show();
            }
        });





    return v;
    }


}