package com.app.oxostayadmin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.oxostayadmin.R;
import com.app.oxostayadmin.model.ApprovedModel;
import com.app.oxostayadmin.screens.ApprovingActivity;

import java.util.ArrayList;

public class ApprovedAdapter extends RecyclerView.Adapter<ApprovedAdapter.ViewHolder>{
    private ArrayList<ApprovedModel> listdata;
    private ArrayList<String> listdataIds;
    private Context context;

    // RecyclerView recyclerView;
    public ApprovedAdapter(ArrayList<ApprovedModel> listdata, ArrayList<String> listdataIds, Context context) {
        this.listdata = listdata;
        this.listdataIds = listdataIds;
        this.context = context;
    }
    @Override
    public ApprovedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.approved_item, parent, false);
        ApprovedAdapter.ViewHolder viewHolder = new ApprovedAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ApprovedAdapter.ViewHolder holder, final int position) {
//        final ArrayList<String> myListData = listdata[position];
        final ApprovedModel approvedModel = listdata.get(position);
//        Log.e("checkFirebaseData","1>>" + approvedModel.getFullName());
        holder.tv_name.setText(approvedModel.getFullName());
        holder.tv_address.setText(approvedModel.getAddress());
//        String keyId = this.getRef(position).getKey();
        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ApprovingActivity.class);
                intent.putExtra("act","approved");
                intent.putExtra("name",approvedModel.getFullName());
                intent.putExtra("address",approvedModel.getAddress());
                intent.putExtra("no",approvedModel.getPhNumber());
                intent.putExtra("aadhar",approvedModel.getAadhaarCard());
                intent.putExtra("pan",approvedModel.getPanCard());
                intent.putExtra("gst",approvedModel.getGstCert());
                intent.putExtra("key",listdataIds.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //        public ImageView iv_location;
        public TextView tv_name,tv_address;
        public RelativeLayout ll_item;
        public ViewHolder(View itemView) {
            super(itemView);
            this.ll_item = (RelativeLayout) itemView.findViewById(R.id.ll_item);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_address = (TextView) itemView.findViewById(R.id.tv_address);
//            rl_location = (RelativeLayout)itemView.findViewById(R.id.rl_location);
        }
    }
}

