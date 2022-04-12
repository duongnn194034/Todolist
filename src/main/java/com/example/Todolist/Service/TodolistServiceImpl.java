package com.example.Todolist.Service;

import com.example.Todolist.Model.Todolist;
import com.example.Todolist.Repository.TodolistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class TodolistServiceImpl implements TodolistService{
    @Autowired
    private final TodolistRepository todolistRepository;

    public TodolistServiceImpl(TodolistRepository todolistRepository) {
        this.todolistRepository = todolistRepository;
    }

    public Todolist addJob(String title, String description, long score) {
        Todolist job = new Todolist(title, description, score);
        this.todolistRepository.save(job);
        return job;
    }

    public Todolist modifyJob(String id, String title, String description, long score) {
        Update update = new Update();
        update.set("title", title);
        update.set("description", description);
        return this.todolistRepository.findByIdAndModify(id, update);
    }

    public void deleteJob(String id) {
        this.todolistRepository.deleteById(id);
    }

    public Todolist findJob(String id) {
        return this.todolistRepository.findById(id);
    }

    @Override
    public boolean existJob(String id) {
        return this.todolistRepository.existsById(id);
    }
}
