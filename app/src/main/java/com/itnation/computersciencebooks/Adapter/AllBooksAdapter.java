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
import com.itnation.computersciencebooks.ModelClass.AllBooks;
import com.itnation.computersciencebooks.R;
import com.itnation.computersciencebooks.ReadingActivity;


import java.util.ArrayList;

public class AllBooksAdapter extends RecyclerView.Adapter<AllBooksAdapter.ViewHolder> {

    Context context;
    ArrayList<AllBooks>allBooksArrayList;

    public AllBooksAdapter(Context context, ArrayList<AllBooks> allBooksArrayList) {
        this.context = context;
        this.allBooksArrayList = allBooksArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.course_book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        AllBooks allBooks = allBooksArrayList.get(position);


        String bookLink = allBooks.getBookLink();
        String bookName = allBooks.getBookName();
        String bookDes = allBooks.getBookDes();
        String coverImg = allBooks.getCoverImg();


        holder.bookName.setText(bookName);
        holder.bookDes.setText(bookDes);


        Glide.with(context).load(coverImg).placeholder(R.drawable.ic_logo).into(holder.bookImg);



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
        return allBooksArrayList.size();
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
