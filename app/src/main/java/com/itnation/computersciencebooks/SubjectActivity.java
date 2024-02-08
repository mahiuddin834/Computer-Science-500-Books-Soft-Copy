package com.itnation.computersciencebooks;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itnation.computersciencebooks.Adapter.SubListAdapter;
import com.itnation.computersciencebooks.ModelClass.SubListModel;


import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity {


    public static String subName ="";
    ImageButton backBtn;
    RecyclerView subListRecycler;
    TextView subNameTxtView;

    SubListAdapter subListAdapter;
    ArrayList<SubListModel>subListModelArrayList;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        backBtn=findViewById(R.id.backBtn);
        subListRecycler=findViewById(R.id.subListRecycler);
        subNameTxtView=findViewById(R.id.subNameTxtView);

        Window window = SubjectActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.primary));



        subNameTxtView.setText(subName);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });



        databaseReference = FirebaseDatabase.getInstance().getReference(subName);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SubjectActivity.this);
        subListRecycler.setLayoutManager(linearLayoutManager);

        subListModelArrayList = new ArrayList<>();
        subListAdapter = new SubListAdapter(SubjectActivity.this, subListModelArrayList);
        subListRecycler.setAdapter(subListAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){


                    SubListModel subListModel = dataSnapshot.getValue(SubListModel.class);
                    subListModelArrayList.add(subListModel);


                }

                subListAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(SubjectActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });





    }



}

