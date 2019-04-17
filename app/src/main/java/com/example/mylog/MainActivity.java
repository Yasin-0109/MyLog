package com.example.mylog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Log> logs = new ArrayList<>();
        logs.add(new Log(R.drawable.ic_android, "Android project", "28/04/2019"));
        logs.add(new Log(R.drawable.ic_assignment, "Hand-in-4", "02/05/2019"));
        logs.add(new Log(R.drawable.ic_computer_black, "SEP4", "10/05/2019"));
        logs.add(new Log(R.drawable.ic_android, "Android project", "28/04/2019"));
        logs.add(new Log(R.drawable.ic_assignment, "Hand-in-4", "02/05/2019"));
        logs.add(new Log(R.drawable.ic_computer_black, "SEP4", "10/05/2019"));
        logs.add(new Log(R.drawable.ic_android, "Android project", "28/04/2019"));
        logs.add(new Log(R.drawable.ic_assignment, "Hand-in-4", "02/05/2019"));
        logs.add(new Log(R.drawable.ic_computer_black, "SEP4", "10/05/2019"));
        logs.add(new Log(R.drawable.ic_android, "Android project", "28/04/2019"));
        logs.add(new Log(R.drawable.ic_assignment, "Hand-in-4", "02/05/2019"));
        logs.add(new Log(R.drawable.ic_computer_black, "SEP4", "10/05/2019"));

        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new MyAdapter(logs);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


}
