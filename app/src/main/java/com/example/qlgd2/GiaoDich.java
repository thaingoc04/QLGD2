package com.example.qlgd2;

import java.io.Serializable;

public class GiaoDich implements Serializable {
    private int id;
    private String content;
    private String date;
    private int type;
    private String name;
    private int cost;

    public GiaoDich(int id, String content, String date, int type, String name, int cost) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.type = type;
        this.name = name;
        this.cost = cost;
    }

    public GiaoDich(String content, String date, int type, String name, int cost) {
        this.content = content;
        this.date = date;
        this.type = type;
        this.name = name;
        this.cost = cost;
    }

    public GiaoDich() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
