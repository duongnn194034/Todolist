package com.example.Todolist.Service;

import com.example.Todolist.Model.Todolist;

import java.util.List;

public interface TodolistService {
    Todolist addJob(String title, String description, long score);
    Todolist modifyJob(String id, String title, String description, long score);
    void deleteJob(String id);
    Todolist findJob(String id);
    boolean existJob(String id);

    List<Todolist> getJobList(long min, long max, boolean check);
}
