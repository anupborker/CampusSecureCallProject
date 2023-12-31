package com.avb.collageproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Staff_menu_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Staff_menu_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Staff_menu_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Staff_menu_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Staff_menu_Fragment newInstance(String param1, String param2) {
        Staff_menu_Fragment fragment = new Staff_menu_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    Button AddDetails;
    Button GetDetails;
    Button ViewAll;
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
        View v = inflater.inflate(R.layout.fragment_staff_menu_, container, false);
        AddDetails = v.findViewById(R.id.staffDetails_addbtn);
        GetDetails = v.findViewById(R.id.staffDetails_getdetailsbtn);
        ViewAll = v.findViewById(R.id.staffDetails_ViewAll);

        buttonclick();

        return  v;
    }
    private void buttonclick() {

        AddDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Add_Staff_Details_fromFragment());
            }
        });

        GetDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Get_Deatils_StaffFragment());
            }
        });

        ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new View_all_StaffFragment());
            }
        });
    }


    private void loadFragment(Fragment fragemt) {
        FragmentTransaction ft = getFragmentManager().beginTransaction() ;
        ft.replace(R.id.campus_entry_container  ,fragemt);
        ft.commit();
        ft.addToBackStack(null);
        Log.d("TAG","Student data load ");
    }

}