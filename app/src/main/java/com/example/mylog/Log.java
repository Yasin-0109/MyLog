package com.example.mylog;

import java.util.Date;

public class Log {

    private String task;
    private Date deadline;
    private String status;

    public Log(String task, Date deadling, String status)
    {
        this.task = task;
        this.deadline = deadline;
        this.status = status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
