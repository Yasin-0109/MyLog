package com.example.mylog;

import java.util.Date;

public class Log {

    private int image;
    private String task;
    private String deadline;
    private String description;

    public Log(int image, String task, String deadline, String description)
    {
        this.image = image;
        this.task = task;
        this.deadline = deadline;
        this.description = description;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

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
