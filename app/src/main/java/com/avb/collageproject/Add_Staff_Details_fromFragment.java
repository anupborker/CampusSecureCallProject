package com.avb.collageproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Add_Staff_Details_fromFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Add_Staff_Details_fromFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Add_Staff_Details_fromFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Add_Staff_Details_fromFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Add_Staff_Details_fromFragment newInstance(String param1, String param2) {
        Add_Staff_Details_fromFragment fragment = new Add_Staff_Details_fromFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    EditText Non_Teaching_Staff_addname;
    EditText Non_Teaching_Staff_addUID;
    EditText Non_Teaching_Staff_adddepartment;
    EditText Non_Teaching_Staff_adddesignation;
    EditText Non_Teaching_Staff_addcontactno;
    EditText Non_Teaching_Staff_addaddress;


    Button Non_Teaching_Staff_add;
    Button Non_Teaching_Staff_update;
    Button Non_Teaching_Staff_delete;

    FirebaseDatabase nonteachingstaffdatabase;
    DatabaseReference nonteachingstaffdatabasedbReference;

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
        View v = inflater.inflate(R.layout.fragment_add__staff__details_from, container, false);
        Non_Teaching_Staff_addname = v.findViewById(R.id.nonteachingstaffNameInput);
        Non_Teaching_Staff_adddepartment = v.findViewById(R.id.nonteachingstaffDepartmentInput);
        Non_Teaching_Staff_addUID = v.findViewById(R.id.nonteachingstaffUIDInput);
        Non_Teaching_Staff_adddesignation = v.findViewById(R.id.nonteachingstaffDesignationInput);
        Non_Teaching_Staff_addcontactno = v.findViewById(R.id.nonteachingstaffContactInput);
        Non_Teaching_Staff_addaddress = v.findViewById(R.id.NonteachingstaffAddressInput);

        Non_Teaching_Staff_add = v.findViewById(R.id.NonteachingstaffsDetails_addbtn);
        Non_Teaching_Staff_update = v.findViewById(R.id.NonteachingstaffsDetails_Updatebtn);
        Non_Teaching_Staff_delete = v.findViewById(R.id.NonteachingstaffsDetails_Deletebtn);

        AddStaffDataInDataDB();



        return v;
    }


    private void AddStaffDataInDataDB() {
        nonteachingstaffdatabase = FirebaseDatabase.getInstance();
        nonteachingstaffdatabasedbReference = nonteachingstaffdatabase.getReference("Nonteachingstaff");
        Non_Teaching_Staff_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Non_Teaching_Staff_addname.getText().toString();
                String department = Non_Teaching_Staff_adddepartment.getText().toString();
                String UID = Non_Teaching_Staff_addUID.getText().toString();
                String designation = Non_Teaching_Staff_adddesignation.getText().toString();

                String contactno = Non_Teaching_Staff_addcontactno.getText().toString();
                String address = Non_Teaching_Staff_addaddress.getText().toString();


                AddStaffData staffData =  new AddStaffData( name,  UID,  designation,  department,  contactno,  address);
                nonteachingstaffdatabasedbReference.child(UID).setValue(staffData);

                Toast.makeText(getActivity(), "Data ha been added Succesully", Toast.LENGTH_SHORT).show();

            }
        });
        Non_Teaching_Staff_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rollno =  Non_Teaching_Staff_addUID.getText().toString();
                nonteachingstaffdatabasedbReference.child(rollno).removeValue();
                Toast.makeText(getActivity(), "Data has been deleted Succesfully", Toast.LENGTH_SHORT).show();
            }
        });


    }
}