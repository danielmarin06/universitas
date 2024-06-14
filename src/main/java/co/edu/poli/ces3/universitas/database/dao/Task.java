package co.edu.poli.ces3.universitas.database.dao;

import java.util.Date;

public class Task {
    private String name;
    private int priority;
    private String status;
    private Date createdAt;

    public Task(String name, int priority, String status) {
        this.name = name;
        this.priority = priority;
        this.status = status;
        this.createdAt = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
