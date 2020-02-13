package com.example.rafayet.todolist;


public class Category {
    private int id;
    private String name;

    static final String COL_ID = "id";
    static final String COL_NAME = "name";

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category() {


    }

    @Override
    public String toString() {
        return this.name;
    }
}
