package com.cloud.medical.records.client_app.service;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cloud.medical.records.client_app.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationsRecyclerViewAdapter extends RecyclerView.Adapter<NotificationsRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "AccessGrantRecyclerView";

    private Context mContext;
    private ArrayList<String> name =  new ArrayList<>();
    private ArrayList<String> email = new ArrayList<>();
    private ArrayList<String> datePosted = new ArrayList<>();
    private ArrayList<String> imageName = new ArrayList<>();
    private ArrayList<String> message = new ArrayList<>();


    public NotificationsRecyclerViewAdapter(Context mContext, ArrayList<String> name, ArrayList<String> email, ArrayList<String> datePosted, ArrayList<String> imageName, ArrayList<String> message) {
        this.mContext = mContext;
        this.name = name;
        this.email = email;
        this.datePosted = datePosted;
        this.imageName = imageName;
        this.message = message;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_notification_holder, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.date_posted.setText(datePosted.get(position));
        holder.email.setText(email.get(position));
        holder.name.setText(name.get(position));
        holder.message.setText(message.get(position));

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView name, email, date_posted, message;
        Button delete, accept;
        RelativeLayout notificationHolder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.notification_client_image);
            name = itemView.findViewById(R.id.notification_client_name);
            date_posted = itemView.findViewById(R.id.notification_date_posted);
            email = itemView.findViewById(R.id.notification_client_email);
            message =itemView.findViewById(R.id.notification_message);


            delete =itemView.findViewById(R.id.notification_client_remove_btn);
            accept =itemView.findViewById(R.id.notification_client_accept);

            notificationHolder = itemView.findViewById(R.id.notification_obj_holder);
        }
    }
}
