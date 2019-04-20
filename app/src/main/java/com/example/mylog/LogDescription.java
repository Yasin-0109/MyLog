package com.example.mylog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class LogDescription extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_description);

        Intent intent = getIntent();
        String Task = intent.getStringExtra("Task");
        String Deadline = intent.getStringExtra("Deadline");
        String Description = intent.getStringExtra("Description");

        ImageView imageView = findViewById(R.id.imageView3);
        TextView task = findViewById(R.id.Task);
        TextView deadline = findViewById(R.id.Deadline);
        TextView description = findViewById(R.id.Description);
        task.setText(Task);
        deadline.setText(Deadline);
        description.setText(Description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
