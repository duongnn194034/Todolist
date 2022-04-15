package com.example.Todolist.Service;

import com.example.Todolist.Model.Todolist;
import com.example.Todolist.Repository.TodolistException;

import java.util.List;

public interface TodolistService {
    Todolist addJob(String title, String description, long score) throws TodolistException;

    Todolist modifyJob(String id, String title, String description, long score, boolean check) throws TodolistException;

    void deleteJob(String id) throws TodolistException;

    Todolist findJob(String id) throws TodolistException;

    boolean existJob(String id) throws TodolistException;

    List<Todolist> getJobList(long min, long max, boolean check) throws TodolistException;
}
