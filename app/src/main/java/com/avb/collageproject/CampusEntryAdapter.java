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

public class CampusEntryAdapter extends RecyclerView.Adapter<CampusEntryAdapter.viewHolder> {

    Context context;
    ArrayList<CampusEntry> arrEntry;

    CampusEntryAdapter(Context context ,ArrayList<CampusEntry> arrEntry) {
        this.context = context;
        this.arrEntry = arrEntry;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_campus_list, parent, false);
        viewHolder viewHolder = new viewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position ) {
        CampusEntry entry = arrEntry.get(position);
        holder.ID.setText(entry.getUserID());
        holder.entryTime.setText(entry.getTimeofentry());
        holder.exitTime.setText(entry.getTimeofexit());
        holder.Image.setImageResource(entry.getImage());

    }

    @Override
    public int getItemCount() {

        return arrEntry.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView  ID, entryTime, exitTime;
        ImageView Image;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            ID = itemView.findViewById(R.id.ID);
            entryTime = itemView.findViewById(R.id.entryTime);
            exitTime = itemView.findViewById(R.id.exitTime);
            Image =  itemView.findViewById(R.id.imagecampus);
        }
    }

}

