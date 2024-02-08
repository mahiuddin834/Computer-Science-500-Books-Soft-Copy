package com.itnation.computersciencebooks.Fragment;

import static com.google.common.reflect.Reflection.getPackageName;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itnation.computersciencebooks.Adapter.SubAdapter;
import com.itnation.computersciencebooks.ModelClass.SubModel;
import com.itnation.computersciencebooks.R;


import java.util.ArrayList;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    SubAdapter subAdapter;
    ArrayList<SubModel>subModelArrayList;


    DatabaseReference databaseReference;

    LottieAnimationView anim_loading;
    NestedScrollView rootLay;
    ImageView rateUs;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView=view.findViewById(R.id.subRecycler);
        rootLay = view.findViewById(R.id.rootLay);
        anim_loading = view.findViewById(R.id.anim_loading);
        rateUs=view.findViewById(R.id.rateUs);





        subList();





        rateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getActivity().getPackageName()));
                startActivity(rateIntent);
            }
        });


        return view;
    }



    private void subList() {


        databaseReference = FirebaseDatabase.getInstance().getReference("SubList");
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));

        subModelArrayList= new ArrayList<>();

        subAdapter = new SubAdapter(getActivity(),subModelArrayList);
        recyclerView.setAdapter(subAdapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){


                    SubModel subModel = dataSnapshot.getValue(SubModel.class);
                    subModelArrayList.add(subModel);

                    rootLay.setVisibility(View.VISIBLE);
                    anim_loading.setVisibility(View.GONE);


                }

                subAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }



}