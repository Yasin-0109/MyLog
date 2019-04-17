package com.example.mylog;

import java.util.Date;

public class Log {

    private int image;
    private String task;
    private String deadline;

    public Log(int image, String task, String deadline)
    {
        this.image = image;
        this.task = task;
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
