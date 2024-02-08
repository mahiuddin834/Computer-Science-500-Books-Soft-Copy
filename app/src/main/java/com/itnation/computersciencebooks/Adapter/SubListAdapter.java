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

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.itnation.computersciencebooks.ModelClass.SubListModel;
import com.itnation.computersciencebooks.R;
import com.itnation.computersciencebooks.ReadingActivity;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class SubListAdapter extends RecyclerView.Adapter<SubListAdapter.ViewHolder> {

    Context context;
    ArrayList<SubListModel>subListModelArrayList;

    public SubListAdapter(Context context, ArrayList<SubListModel> subListModelArrayList) {
        this.context = context;
        this.subListModelArrayList = subListModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.course_book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SubListModel subListModel=subListModelArrayList.get(position);

        String bookLink = subListModel.getBookLink();
        String bookName = subListModel.getBookName();
        String bookDes = subListModel.getBookDes();
        String coverImg = subListModel.getCoverImg();


        holder.bookName.setText(bookName);
        holder.bookDes.setText(bookDes);

        Glide.with(context).load(coverImg).placeholder(R.drawable.ic_logo).into(holder.bookImg);

        /*

                 Picasso.get().load(coverImg).placeholder(R.drawable.ic_logo).into(holder.bookImg);

         */





        holder.readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(context, ReadingActivity.class);
                intent.putExtra("bookLink",bookLink);
                intent.putExtra("bookName",bookName);
                context.startActivity(intent);



            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(context, ReadingActivity.class);
                intent.putExtra("bookLink",bookLink);
                intent.putExtra("bookName",bookName);
                context.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return subListModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView bookName, bookDes;
        MaterialButton readButton;
        ImageView bookImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            bookName=itemView.findViewById(R.id.bookName);
            bookDes=itemView.findViewById(R.id.bookDes);
            readButton=itemView.findViewById(R.id.readButton);
            bookImg=itemView.findViewById(R.id.bookImg);
        }
    }
}
