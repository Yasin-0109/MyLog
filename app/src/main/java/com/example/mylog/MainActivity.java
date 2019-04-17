package com.example.mylog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_item1:
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_item2:
                Intent intent2 = new Intent(MainActivity.this, SaveLog.class);
                startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


}
