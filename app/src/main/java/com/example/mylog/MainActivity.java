package com.example.mylog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylog.MyAdapter.onItemClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements onItemClickListener {
    private ArrayList<Log> logs;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton saveButton;
    private FloatingActionButton refreshButton;


    static final String REQUEST_URL = "https://favqs.com/api/qotd";
    private TextView Quote;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveButton = findViewById(R.id.save);
        refreshButton = findViewById(R.id.refresh);

        //set textview for quote
        Quote = findViewById(R.id.qoute);
        GetBooksAsync task = new GetBooksAsync();
        task.execute(REQUEST_URL);

        // loadData and build recyclerview
        loadData();
        buildRecyclerView();

        //Checks in a user is logged in
        firebaseAuth = firebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, Login.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();


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


    // builds the recyclerview
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

    //set the menu items to the menubar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    //Sets the menu items onclick functionality
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

            case R.id.action_item3:
                firebaseAuth.signOut();
                finish();
                Intent intent3 = new Intent(this, Login.class);
                this.startActivity(intent3);
                break;

            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }

    @Override
    public void onDeleteClick(int position) {
        removeItem(position);
    }

    // removes item from recyclerview based on position
    public void removeItem(int position) {
        logs.remove(position);
        adapter.notifyItemRemoved(position);
    }





    //when clicking on a recyclerview this function open a deatalied activity with more info
    @Override
    public void onItemClick(int position) {
        Intent descriptionIntent = new Intent(this, LogDescription.class);
        Log clickedItem = logs.get(position);

        descriptionIntent.putExtra("Task", clickedItem.getTask());
        descriptionIntent.putExtra("Deadline", clickedItem.getDeadline());
        descriptionIntent.putExtra("Description", clickedItem.getDescription());

        startActivity(descriptionIntent);
    }


    //inserts a new item based on info from savelog activity
    public void refresh()
    {
        Intent intent = getIntent();
        String Task = intent.getStringExtra("saveTask");
        String Deadline = intent.getStringExtra("saveDeadline");
        String Description = intent.getStringExtra("saveDescription");

        logs.add(new Log(R.drawable.ic_assignment, Task, Deadline, Description));
        adapter.notifyItemInserted(logs.size());

    }

    // saves the recyclerview using shared preferences
    private void saveData() {
        Toast.makeText(this, "Log saved successfully", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(logs);
        editor.putString("task list", json);
        editor.apply();
    }

    // Loads the saved recyclerview using shared preferences
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

    //makes http get request to server
    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null)
            return jsonResponse;

        HttpURLConnection urlConnection = null;
        InputStream is = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                is = urlConnection.getInputStream();
                jsonResponse = readFromStream(is);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
            if (is != null)
                is.close();
        }
        return jsonResponse;
    }

    //reads the JSON response to a String
    private String readFromStream(InputStream is) throws IOException {
        StringBuilder output = new StringBuilder();
        if (is != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(is, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    //Checks if there is a JSON response and converts it into a String, and displays the String in the app
    private class GetBooksAsync extends AsyncTask<String, Void, String> {

        //Checks if there is a JSON response
        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            String jsonResponse = "";
            try {
                url = new URL(strings[0]);
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResponse;
        }

        //Converts it into a String, and displays the String in the app
        @Override
        protected void onPostExecute(String s) {
            JSONObject root = null;
            try {
                root = new JSONObject(s);
                JSONObject slip = root.getJSONObject("quote");
                String author = slip.getString("author");
                String quote = slip.getString("body");
                Quote.setText(author + ": " + quote);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }




























}
