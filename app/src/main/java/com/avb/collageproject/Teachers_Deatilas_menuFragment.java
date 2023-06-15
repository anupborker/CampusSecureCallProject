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
 * Use the {@link Teachers_Deatilas_menuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Teachers_Deatilas_menuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Teachers_Deatilas_menuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Teachers_Deatilas_menuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Teachers_Deatilas_menuFragment newInstance(String param1, String param2) {
        Teachers_Deatilas_menuFragment fragment = new Teachers_Deatilas_menuFragment();
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
        View v = inflater.inflate(R.layout.fragment_teachers__deatilas_menu, container, false);
        AddDetails = v.findViewById(R.id.TeachersDetails_addbtn1);
        GetDetails = v.findViewById(R.id.TeachersDetails_getdetailsbtn);
        ViewAll = v.findViewById(R.id.TeachersDetails_viewbtn);

        buttonclick();
        return v;
    }

    private void buttonclick() {

        AddDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Add_Teachers_details_formFragment());
            }
        });

        GetDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new teachers_GetDeatislFragment());
            }
        });

        ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new View_all_TeacherFragment());
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
