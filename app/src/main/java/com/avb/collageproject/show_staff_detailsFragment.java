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
 * A simple {@link Fragment} subclass.
 * Use the {@link show_staff_detailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class show_staff_detailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public show_staff_detailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment show_staff_detailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static show_staff_detailsFragment newInstance(String param1, String param2) {
        show_staff_detailsFragment fragment = new show_staff_detailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    TextView showNonteachingstaffName;
    TextView showNonteachingstaffUID;
    TextView showNonteachingstaffDesignation;
    TextView showNonteachingstaffDepartment;
    TextView showNonteachingstaffContacno;
    TextView showNonteachingstaffAddress;
    ImageView showNonteachingstaffImage;

    FirebaseDatabase Nonteachingstaffdatabase;
    DatabaseReference NonteachingstaffdbReference;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }        getParentFragmentManager().setFragmentResultListener("Staff_ID", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                String  ID = result.getString("StaffID");
                System.out.println("UID:" + ID);
                ShowTeacherInDataDB(ID);

            }
        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_show_staff_details, container, false);

        Nonteachingstaffdatabase = FirebaseDatabase.getInstance();
        NonteachingstaffdbReference=Nonteachingstaffdatabase.getReference("Nonteachingstaff");


        showNonteachingstaffName = v.findViewById(R.id.NonteachingstaffshowdetailName);
        showNonteachingstaffUID =  v.findViewById(R.id.NonteachingstaffshowdetailUID);
        showNonteachingstaffDesignation = v.findViewById(R.id.NonteachingstaffshowdetailDesignation);
        showNonteachingstaffDepartment = v.findViewById(R.id.NonteachingstaffshowdetailDepartment);
        showNonteachingstaffContacno = v.findViewById(R.id.NonteachingstaffshowdetailscontactNo);
        showNonteachingstaffAddress = v.findViewById(R.id.NonteachingstaffshowdetailAddress);
        showNonteachingstaffImage = v.findViewById(R.id.NonteachingstaffshowdetailImage);


        return v;

    }

    private void ShowTeacherInDataDB(String ID) {
        Nonteachingstaffdatabase = FirebaseDatabase.getInstance();
        System.out.println(ID);
        NonteachingstaffdbReference =Nonteachingstaffdatabase.getReference("Nonteachingstaff");


        NonteachingstaffdbReference.child(ID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();


                String name = String.valueOf(dataSnapshot.child("name").getValue());
                showNonteachingstaffName.setText(name);
                String UDI = String.valueOf(dataSnapshot.child("UDI").getValue());
                showNonteachingstaffUID.setText(UDI);
                String designation = String.valueOf(dataSnapshot.child("designation").getValue());
                showNonteachingstaffDesignation.setText(designation);
                String department = String.valueOf(dataSnapshot.child("department").getValue());
                showNonteachingstaffDepartment.setText(department);
                String contactno = String.valueOf(dataSnapshot.child("contactno").getValue());
                showNonteachingstaffContacno.setText(contactno);
                String address = String.valueOf(dataSnapshot.child("address").getValue());
                showNonteachingstaffAddress.setText(address);


            }


        });
    }
}