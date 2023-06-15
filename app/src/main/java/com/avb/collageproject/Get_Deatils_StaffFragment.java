package com.avb.collageproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Get_Deatils_StaffFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Get_Deatils_StaffFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Get_Deatils_StaffFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Get_Deatils_StaffFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Get_Deatils_StaffFragment newInstance(String param1, String param2) {
        Get_Deatils_StaffFragment fragment = new Get_Deatils_StaffFragment();
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
        View v = inflater.inflate(R.layout.fragment_get__deatils__staff, container, false);
        getdetailsinput = v.findViewById(R.id.staffget1_details_input);
        getdetails = v.findViewById(R.id.staffget1_details);

        getdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUSerID();
                loadFragment(new show_staff_detailsFragment());

            }
        });

        return v;
    }
    private void sendUSerID() {
        String StudentID = getdetailsinput.getText().toString();



        Bundle Std_ID = new Bundle();
        Std_ID.putString("StaffID",StudentID);
        getParentFragmentManager().setFragmentResult("Staff_ID",Std_ID);
    }


    private void loadFragment(Fragment fragemt) {
        FragmentTransaction ft = getFragmentManager().beginTransaction() ;
        ft.replace(R.id.campus_entry_container  ,fragemt);
        ft.commit();
        Log.d("TAG","Student data load ");
    }

}