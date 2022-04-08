package com.example.Todolist.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
import java.util.Date;


@Document(collection = "Todojob")
public class Todojob {

    @Id
    private String id;
    private String title;
    private String description;
    private Date createTime;
    private Date lastModified;
    private long score;
    private int check; // 1 = da lam, 0 = chua lam

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public Todojob() {
        Calendar calendar = Calendar.getInstance();
        this.createTime = calendar.getTime();
        this.lastModified = this.createTime;
    }

    public Todojob(String title, String description, long score) {
        this.title = title;
        this.description = description;
        this.score = score;
        Calendar calendar = Calendar.getInstance();
        this.createTime = calendar.getTime();
        this.lastModified = this.createTime;
    }

    public Todojob(Todojob job) {
        this.title = job.getTitle();
        this.description = job.getDescription();
        this.score = job.getScore();
        Calendar calendar = Calendar.getInstance();
        this.createTime = calendar.getTime();
        this.lastModified = this.createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        Calendar calendar = Calendar.getInstance();
        this.lastModified = calendar.getTime();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        Calendar calendar = Calendar.getInstance();
        this.lastModified = calendar.getTime();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        Calendar calendar = Calendar.getInstance();
        this.lastModified = calendar.getTime();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
        Calendar calendar = Calendar.getInstance();
        this.lastModified = calendar.getTime();
    }
}
