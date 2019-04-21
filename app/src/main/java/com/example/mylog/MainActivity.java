package com.example.mylog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.mylog.MyAdapter.onItemClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements onItemClickListener {
    private ArrayList<Log> logs;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton saveButton;
    private FloatingActionButton refreshButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveButton = findViewById(R.id.save);
        refreshButton = findViewById(R.id.refresh);

        createLog();
        loadData();
        buildRecyclerView();


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
    }


    public void createLog() {
        logs = new ArrayList<>();
    }

    public void buildRecyclerView()
    {
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new MyAdapter(logs);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(MainActivity.this);

    }
    public void removeItem(int position) {
        logs.remove(position);
        adapter.notifyItemRemoved(position);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_item1:
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                break;

            case R.id.action_item2:
                Intent intent2 = new Intent(this, SaveLog.class);
                this.startActivity(intent2);
                break;

            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }


    @Override
    public void onItemClick(int position) {
        Intent descriptionIntent = new Intent(this, LogDescription.class);
        Log clickedItem = logs.get(position);

        descriptionIntent.putExtra("Task", clickedItem.getTask());
        descriptionIntent.putExtra("Deadline", clickedItem.getDeadline());
        descriptionIntent.putExtra("Description", clickedItem.getDescription());

        startActivity(descriptionIntent);
    }

    @Override
    public void onDeleteClick(int position) {
        removeItem(position);
    }

    public void refresh()
    {
        Intent intent = getIntent();
        String Task = intent.getStringExtra("saveTask");
        String Deadline = intent.getStringExtra("saveDeadline");
        String Description = intent.getStringExtra("saveDescription");

        logs.add(new Log(R.drawable.ic_assignment, Task, Deadline, Description));
        adapter.notifyItemInserted(logs.size());

    }



    private void saveData() {
        Toast.makeText(this, "Log saved successfully", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(logs);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Log>>() {}.getType();
        logs = gson.fromJson(json, type);

        if (logs == null) {
            logs = new ArrayList<>();
        }
    }




}
