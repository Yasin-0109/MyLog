package com.example.mylog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SaveLog extends AppCompatActivity {

    private ImageView image;
    private EditText task, deadline, description;
    private TextView header;
    private Button saveButton;
    private RecyclerView.Adapter adapter;
    private ArrayList<Log> logs = new ArrayList<Log>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_log);

        image = (ImageView)findViewById(R.id.imageView2);
        task = (EditText)findViewById(R.id.tasksave);
        deadline = (EditText)findViewById(R.id.deadlinesave);
        description = (EditText)findViewById(R.id.descriptionsave);
        header = (TextView)findViewById(R.id.descriptionheader);
        saveButton = (Button)findViewById(R.id.saveButton);
        header.setText("Enter description");

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLog();
            }
        });

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

    public void saveLog()
    {
        Toast.makeText(this, "Log saved successfully", Toast.LENGTH_SHORT).show();
        Intent saveIntent = new Intent(this, MainActivity.class);

        saveIntent.putExtra("saveTask", task.getText().toString());
        saveIntent.putExtra("saveDeadline", deadline.getText().toString());
        saveIntent.putExtra("saveDescription", description.getText().toString());

        startActivity(saveIntent);
    }

}
