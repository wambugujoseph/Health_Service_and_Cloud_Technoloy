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
import com.cloud.medical.records.client_app.model.AccessContract;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccessGrantRecyclerViewAdapter extends RecyclerView.Adapter<AccessGrantRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "AccessGrantRecyclerView";

    private Context mContext;
    private ArrayList<String> name =  new ArrayList<>();
    private ArrayList<String> email = new ArrayList<>();
    private ArrayList<String> phoneNumber = new ArrayList<>();
    private ArrayList<String> idNumber = new ArrayList<>();
    private ArrayList<String> imageName = new ArrayList<>();

    private AccessContract[] accessContract = null;

    public AccessGrantRecyclerViewAdapter(Context mContext, ArrayList<String> name, ArrayList<String> email, ArrayList<String> phoneNumber, ArrayList<String> idNumber, ArrayList<String> imageName) {
        this.mContext = mContext;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
        this.imageName = imageName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_access_grant, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

         holder.name.setText(name.get(position));
         holder.email.setText(email.get(position));
         holder.phoneNumber.setText(phoneNumber.get(position));
         holder.idNumber.setText(idNumber.get(position));

         // fix the image here;

        //Other text Update
        holder.name.setText(this.name.get(position));
        holder.idNumber.setText(this.idNumber.get(position));
        holder.phoneNumber.setText(this.phoneNumber.get(position));
        holder.email.setText(this.email.get(position));

         //block clients
         holder.block.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

             }
         });
         //remove client from the access grant list
         holder.remove.setOnClickListener(new View.OnClickListener() {
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
        TextView name, idNumber, email, phoneNumber;
        Button remove, block;
        RelativeLayout accessGrantObjHolder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.client_image);
            name = itemView.findViewById(R.id.client_name);
            idNumber = itemView.findViewById(R.id.client_id);
            email = itemView.findViewById(R.id.client_email);
            phoneNumber = itemView.findViewById(R.id.client_phone_number);

            remove =itemView.findViewById(R.id.client_remove_btn);
            block =itemView.findViewById(R.id.client_block_btn);

            accessGrantObjHolder = itemView.findViewById(R.id.access_grant_obj_holder);
        }
    }
}
