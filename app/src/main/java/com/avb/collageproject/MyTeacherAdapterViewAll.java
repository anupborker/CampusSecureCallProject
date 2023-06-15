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

public class MyTeacherAdapterViewAll extends RecyclerView.Adapter<MyTeacherAdapterViewAll.TeacherViewHolder> {


    Context context;
    ArrayList<TeacherList> TeacherListArrayList;
    public MyTeacherAdapterViewAll(Context context,ArrayList<TeacherList> TeacherListArrayList) {
        this.context = context;
        this.TeacherListArrayList = TeacherListArrayList;

    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.user_list,parent,false);

        return new TeacherViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {

        TeacherList teacherList = TeacherListArrayList.get(position);
        holder.userName.setText(teacherList.name);
        holder.userFaculty.setText(teacherList.department);
        holder.userID.setText(teacherList.UDI);


    }

    @Override
    public int getItemCount() {
        return TeacherListArrayList.size();
    }

    public class TeacherViewHolder extends  RecyclerView.ViewHolder{

        TextView userName;
        TextView userFaculty;
        TextView userID;
        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);


            userName = itemView.findViewById(R.id.user_name_xml);
            userFaculty = itemView.findViewById(R.id.user_faculty);
            userID = itemView.findViewById(R.id.user_id);

        }
    }
}
