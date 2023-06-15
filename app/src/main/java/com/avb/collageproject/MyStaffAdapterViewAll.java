package com.avb.collageproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;

import java.sql.Array;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class MyStaffAdapterViewAll extends RecyclerView.Adapter<MyStaffAdapterViewAll.StaffViewHolder> {

    Context context;
    ArrayList<StaffList> StaffListArray;

    public MyStaffAdapterViewAll(Context context, ArrayList<StaffList> staffListArray) {
        this.context = context;
        this.StaffListArray = staffListArray;
    }




    @NonNull
    @Override
    public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.user_list,parent,false);

        return new MyStaffAdapterViewAll.StaffViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewHolder holder, int position) {
        StaffList staffList = StaffListArray.get(position);
        holder.userName.setText(staffList.name);
        holder.userFaculty.setText(staffList.department);
        holder.userID.setText(staffList.udi);
        holder.userImage.setImageResource(staffList.image);

    }

    @Override
    public int getItemCount() {
        return StaffListArray.size();
    }

    public class  StaffViewHolder extends  RecyclerView.ViewHolder{

        ImageView userImage;
        TextView userName;
        TextView userFaculty;
        TextView userID;


        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.user_image_recycleview);
            userName = itemView.findViewById(R.id.user_name_xml);
            userFaculty = itemView.findViewById(R.id.user_faculty);
            userID = itemView.findViewById(R.id.user_id);
        }
    }

}
