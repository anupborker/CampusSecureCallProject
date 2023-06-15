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


public class Add_Teachers_details_formFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Add_Teachers_details_formFragment() {
        // Required empty public constructor
    }


    public static Add_Teachers_details_formFragment newInstance(String param1, String param2) {
        Add_Teachers_details_formFragment fragment = new Add_Teachers_details_formFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    EditText addname;
    EditText addUID;
    EditText adddepartment;
    EditText adddesignation;
    EditText addstatus;
    EditText addstream;
    EditText addcontactno;
    EditText addaddress;
    Button add;
    Button update;
    Button delete;
    FirebaseDatabase teachersdatabase;
    DatabaseReference teachersdbReference;


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
        View v = inflater.inflate(R.layout.fragment_add__teachers_details_form, container, false);

        addaddress = v.findViewById(R.id.TeacherAddressInput);
        addname = v.findViewById(R.id.teacherNameInput);
        adddepartment = v.findViewById(R.id.teacherDepartmentInput);
        addUID = v.findViewById(R.id.teacherUIDInput);
        adddesignation = v.findViewById(R.id.teacherDesignationInput);
        addstatus = v.findViewById(R.id.teacherStatusInput);
        addstream = v.findViewById(R.id.teacherStreamInput);
        addcontactno = v.findViewById(R.id.teacherContactInput);
        addstatus = v.findViewById(R.id.teacherStatusInput);
        add = v.findViewById(R.id.Teacherdbform_addbtn);
        update = v.findViewById(R.id.Teacherdbform_Updatebtn);
        delete = v.findViewById(R.id.Teacherdbform_Deletebtn);

        AddTeacherInDataDB();

        return  v;
    }


    private void AddTeacherInDataDB() {
        teachersdatabase = FirebaseDatabase.getInstance();
        teachersdbReference = teachersdatabase.getReference("Teacher");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = addname.getText().toString();
                String department = adddepartment.getText().toString();
                String UID = addUID.getText().toString();
                String designation = adddesignation.getText().toString();
                String status= addstatus.getText().toString();
                String stream = addstream.getText().toString();
                String contactno = addcontactno.getText().toString();
                String address = addaddress.getText().toString();


                AddTeachersData teachersData =  new AddTeachersData( name,  UID,  designation,  status,  department,  stream,  contactno,  address);
                teachersdbReference.child(UID).setValue(teachersData);

                Toast.makeText(getActivity(), "Data ha been added Succesully", Toast.LENGTH_SHORT).show();

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rollno = addUID.getText().toString();
                teachersdbReference.child(rollno).removeValue();
                Toast.makeText(getActivity(), "Data has been deleted Succesfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

}