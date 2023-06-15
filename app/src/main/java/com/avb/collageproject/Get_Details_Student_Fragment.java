package com.avb.collageproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Get_Details_Student_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Get_Details_Student_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Get_Details_Student_Fragment() {
        // Required empty public constructor
    }



    public static Get_Details_Student_Fragment newInstance(String param1, String param2) {
        Get_Details_Student_Fragment fragment = new Get_Details_Student_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    EditText getdetailsinput;
    Button getdetails;
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
        View v = inflater.inflate(R.layout.fragment_get__details__student_, container, false);

        getdetailsinput = v.findViewById(R.id.studentget_details_input);
        getdetails = v.findViewById(R.id.get_details);

        getdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                       sendUSerID();
                       loadFragment(new show_details_studentFragment());

            }
        });

        return v;
    }

    private void sendUSerID() {
        String StudentID = getdetailsinput.getText().toString();



        Bundle Std_ID = new Bundle();
        Std_ID.putString("ID",StudentID);
        getParentFragmentManager().setFragmentResult("user_ID",Std_ID);
    }

    private void loadFragment(Fragment fragemt) {
        FragmentTransaction ft = getFragmentManager().beginTransaction() ;
        ft.add(R.id.campus_entry_container  ,fragemt);
        ft.commit();
        Log.d("TAG","Student data load ");
    }
}