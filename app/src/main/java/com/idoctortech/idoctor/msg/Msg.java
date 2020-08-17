package com.idoctortech.idoctor.msg;

public class Msg {
    int from;
    int to;
    int count;
    String img;
    String Name;

    public Msg() {
    }

    public Msg(int from, int to, int count, String img, String name) {
        this.from = from;
        this.to = to;
        this.count = count;
        this.img = img;
        Name = name;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
