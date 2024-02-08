package com.itnation.computersciencebooks.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.itnation.computersciencebooks.ModelClass.SubModel;
import com.itnation.computersciencebooks.R;
import com.itnation.computersciencebooks.SubjectActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SubAdapter extends RecyclerView.Adapter<SubAdapter.ViewHolder> {

    Context context;
    ArrayList<SubModel>subModelArrayList;

    public SubAdapter(Context context, ArrayList<SubModel> subModelArrayList) {
        this.context = context;
        this.subModelArrayList = subModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.sub_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SubModel subModel=subModelArrayList.get(position);

        String subName = subModel.getSubName();
        String subImgLink = subModel.getImgLink();


        holder.subNameTxt.setText(subName);
        Picasso.get()
                .load(subImgLink)
                .resize(150,150)
                .centerCrop()
                .into(holder.subIcon);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SubjectActivity.subName=subName;

                Intent intent= new Intent(context, SubjectActivity.class);
                context.startActivity(intent);

            }
        });




    }

    @Override
    public int getItemCount() {
        return subModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView subIcon;
        TextView subNameTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subIcon=itemView.findViewById(R.id.subIcon);
            subNameTxt=itemView.findViewById(R.id.subNameTxt);
        }
    }
}
