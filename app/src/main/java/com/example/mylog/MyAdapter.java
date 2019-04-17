package com.example.mylog;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Log> Mlogs;

    public static class ViewHolder extends  RecyclerView.ViewHolder
    {
        public ImageView imageView;
        public TextView textView;
        public TextView textView2;

        public ViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
        }
    }

    public MyAdapter(ArrayList<Log> logs) {
        Mlogs = logs;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.log_item, viewGroup, false);
        ViewHolder viewholder = new ViewHolder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewholder, int i) {
        Log currentLog = Mlogs.get(i);

        viewholder.imageView.setImageResource(currentLog.getImage());
        viewholder.textView.setText(currentLog.getTask());
        viewholder.textView2.setText(currentLog.getDeadline());
    }

    @Override
    public int getItemCount() {
        return Mlogs.size();
    }
}
