package com.itnation.computersciencebooks;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.itnation.computersciencebooks.Fragment.BooksFragment;
import com.itnation.computersciencebooks.Fragment.HomeFragment;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {


    SmoothBottomBar bottomBar;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bottomBar= findViewById(R.id.bottomBar);
        frameLayout= findViewById(R.id.frameLayout);

        //toggleColorStatusBarIcons(MainActivity.this);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new HomeFragment());
        fragmentTransaction.commit();

        bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (i){

                    case 0:

                        fragmentTransaction.replace(R.id.frameLayout, new HomeFragment());

                        break;
                    case 1:

                        fragmentTransaction.replace(R.id.frameLayout, new BooksFragment());

                        break;


                }
                fragmentTransaction.commit();
                return false;
            }
        });
    }

    public static void toggleColorStatusBarIcons(Activity activity){

        if (Build.VERSION.SDK_INT>=24){


            View decor = activity.getWindow().getDecorView();
            if (decor.getSystemUiVisibility() != View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR){
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }else {
                decor.setSystemUiVisibility(0);
            }





        }



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}