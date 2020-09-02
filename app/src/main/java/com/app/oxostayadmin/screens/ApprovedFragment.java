package com.app.oxostayadmin.screens;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.oxostayadmin.adapter.ApprovedAdapter;
import com.app.oxostayadmin.model.ApprovedModel;
import com.app.oxostayadmin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ApprovedFragment extends Fragment {

    RecyclerView recycler_view_home;
    ApprovedAdapter approvingAdapter;
    ArrayList<ApprovedModel> approvedModels;
    ArrayList<String> approvedModelsIds;
    DatabaseReference ref;
    FirebaseDatabase database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.approved_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recycler_view_home = (RecyclerView) view.findViewById(R.id.recycler_view_home);
        recycler_view_home.setLayoutManager(new LinearLayoutManager(getActivity()));
        approvedModels = new ArrayList<>();
        approvedModelsIds = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("oxostaypartner").child("hotelsapproved");
    }

    public void getFirebaseData(){

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    ApprovedModel approvedModel = dataSnapshot1.getValue(ApprovedModel.class);
                    approvedModels.add(approvedModel);
                    approvedModelsIds.add(dataSnapshot1.getKey());
                }
                Log.e("checkKey",">>" + approvedModelsIds);

                approvingAdapter = new ApprovedAdapter(approvedModels,approvedModelsIds,getActivity());
                recycler_view_home.setAdapter(approvingAdapter);
                approvingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        approvedModels.clear();
        getFirebaseData();


    }
}