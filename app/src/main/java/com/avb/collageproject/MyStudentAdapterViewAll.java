package com.avb.collageproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyStudentAdapterViewAll extends RecyclerView.Adapter<MyStudentAdapterViewAll.StudentViewHolder> {
    Context context;
    ArrayList<StudentsList> studentsListArrayList;
    public MyStudentAdapterViewAll(Context context,ArrayList<StudentsList> studentsListArrayList) {
        this.context = context;
        this.studentsListArrayList = studentsListArrayList;

    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.user_list,parent,false);

        return new StudentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
                StudentsList studentsList = studentsListArrayList.get(position);
                holder.userName.setText(studentsList.name);
                holder.userFaculty.setText(studentsList.faculty);
                holder.userID.setText(studentsList.rollno);
                holder.userImage.setImageResource(studentsList.image);
    }

    @Override
    public int getItemCount() {
        return studentsListArrayList.size();
    }

    public static class  StudentViewHolder extends RecyclerView.ViewHolder{
        ImageView userImage;
        TextView userName;
        TextView userFaculty;
        TextView userID;
            public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
                userImage = itemView.findViewById(R.id.user_image_recycleview);
                userName = itemView.findViewById(R.id.user_name_xml);
                userFaculty = itemView.findViewById(R.id.user_faculty);
                userID = itemView.findViewById(R.id.user_id);
        }
    }
}
