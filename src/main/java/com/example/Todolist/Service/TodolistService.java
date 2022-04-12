package com.example.Todolist.Service;

import com.example.Todolist.Model.Todolist;
import com.example.Todolist.Repository.TodolistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//
@Service
public class TodolistService {
    @Autowired
    private final TodolistRepository todolistRepository;

    public TodolistService(TodolistRepository todolistRepository) {
        this.todolistRepository = todolistRepository;
    }

    public Todolist addJob(String title, String description, long score) {
        Todolist job = new Todolist(title, description, score);
        this.todolistRepository.save(job);
        return job;
    }

    public Todolist modifyJob(String id, String title, String description, long score) {
        Todolist job = new Todolist(this.todolistRepository.findById(id).get());
        job.setId(id);
        job.setDescription(description);
        job.setTitle(title);
        job.setScore(score);
        this.todolistRepository.deleteById(id);
        this.todolistRepository.save(job);
        return job;
    }

    public void deleteJob(String id) {
        this.todolistRepository.deleteById(id);
    }
}
