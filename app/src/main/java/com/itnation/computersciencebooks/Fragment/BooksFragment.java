package com.itnation.computersciencebooks.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.itnation.computersciencebooks.Adapter.AllBooksAdapter;
import com.itnation.computersciencebooks.ModelClass.AllBooks;
import com.itnation.computersciencebooks.R;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;


public class BooksFragment extends Fragment {


    MaterialSearchBar searchBar;
    RecyclerView allBooks;
    LottieAnimationView anim_loading;

    DatabaseReference databaseReference;
    AllBooksAdapter allBooksAdapter;
    ArrayList<AllBooks>allBooksArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_books, container, false);

        searchBar=view.findViewById(R.id.searchBar);
        allBooks=view.findViewById(R.id.allBooks);
        anim_loading=view.findViewById(R.id.anim_loading);


        searchBar.getText().toString();





        databaseReference = FirebaseDatabase.getInstance().getReference("All Books");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        allBooks.setLayoutManager(linearLayoutManager);

        allBooksArrayList = new ArrayList<>();
        allBooksAdapter = new AllBooksAdapter(getContext(), allBooksArrayList);
        allBooks.setAdapter(allBooksAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){


                    AllBooks allBooks = dataSnapshot.getValue(AllBooks.class);
                    allBooksArrayList.add(allBooks);


                }

                allBooksAdapter.notifyDataSetChanged();
                anim_loading.setVisibility(View.GONE);
                allBooks.setVisibility(View.VISIBLE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });








        return view;
    }
}