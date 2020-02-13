package com.example.rafayet.todolist;


public class TaskView {
    private int id;
    private int categoryId;
    private String name;
    private String description;
    private int date;
    private String status;
    private String categoryName;

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
