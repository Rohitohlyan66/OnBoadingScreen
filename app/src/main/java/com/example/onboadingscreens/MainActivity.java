package com.example.onboadingscreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayout dotsLayout;
    SliderAdapter adapter;
    List<Data> data;
    Button getStarted;
    TextView skip;
    ViewPager2 viewPager2;
    ImageView[] dots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Removing Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);





        //Views
        dotsLayout = findViewById(R.id.dots_layout);
        getStarted = findViewById(R.id.start_btn);
        skip = findViewById(R.id.skip);
        viewPager2 = findViewById(R.id.view_pager_2);


        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Skipped", Toast.LENGTH_SHORT).show();
            }
        });
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Started", Toast.LENGTH_SHORT).show();
            }
        });


        //Adding data to list
        data = new ArrayList<>();
        data.add(new Data("Manage Goals", getResources().getString(R.string.string_one), R.drawable.goals));
        data.add(new Data("Set a Schedule", getResources().getString(R.string.string_two), R.drawable.schedule));
        data.add(new Data("Do the list for easily", getResources().getString(R.string.string_three), R.drawable.list));


        //Attaching adapter
        adapter = new SliderAdapter(data);
        viewPager2.setAdapter(adapter);


        //Creating dots
        dots = new ImageView[3];
        createDots();
        selectedDots(0);


        //Page change listener
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                selectedDots(position);

                if (position == 0) {
                    skip.setVisibility(View.VISIBLE);
                } else {
                    skip.setVisibility(View.INVISIBLE);
                }


                if (position == 2) {
                    getStarted.setVisibility(View.VISIBLE);
                    getStarted.setEnabled(true);
                } else {
                    getStarted.setVisibility(View.INVISIBLE);
                    getStarted.setEnabled(false);
                }
            }
        });

    }

    private void selectedDots(int position) {
        for (int i = 0; i < dots.length; i++) {
            if (i == position) {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.selected_dot));
            } else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.unselected_dot));
            }
        }
    }

    private void createDots() {
        for (int i = 0; i < dots.length; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(6, 0, 6, 0);

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.unselected_dot));
            dots[i].setLayoutParams(params);
            dotsLayout.addView(dots[i]);
        }
    }
}