package com.example.rafayet.todolist;

public class Task {
    private int id;
    private int categoryId;
    private String name;
    private String description;
    private int date;
    private String status;

    static final String COL_ID = "id";
    static final String COL_CATEGORYID = "categoryId";
    static final String COL_NAME = "name";
    static final String COL_DESCRIPTION = "description";
    static final String COL_DATE = "date";
    static final String COL_STATUS = "status";

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static String getColStatus() {

        return COL_STATUS;
    }

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryID(int categoryID) {
        this.categoryId = categoryID;
    }

    public String getName() {
        return name;
    }

    public Task() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public Task(int categoryId, String name, String description, int date, String status) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.date = date;
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

}
