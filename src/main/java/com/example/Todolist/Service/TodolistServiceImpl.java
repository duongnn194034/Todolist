package com.example.Todolist.Service;

import com.example.Todolist.Model.Todolist;
import com.example.Todolist.Repository.TodolistException;
import com.example.Todolist.Repository.TodolistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class TodolistServiceImpl implements TodolistService{
    @Autowired
    private final TodolistRepository todolistRepository;

    @Autowired
    public TodolistServiceImpl(TodolistRepository todolistRepository) {
        this.todolistRepository = todolistRepository;
    }

    @Override
    public Todolist addJob(String title, String description, long score, boolean check) throws TodolistException {
        Todolist job = new Todolist(title, description, score, check);
        return this.todolistRepository.save(job);
    }

    @Override
    public Todolist modifyJob(String id, String title, String description, long score, boolean check) throws TodolistException {
        Update update = new Update();
        update.set("title", title);
        update.set("description", description);
        update.set("score", score);
        update.set("check", check);
        Calendar calendar = Calendar.getInstance();
        update.set("lastModified", calendar.getTime());
        return this.todolistRepository.findByIdAndModify(id, update);
    }

    @Override
    public void deleteJob(String id) throws TodolistException {
        this.todolistRepository.deleteById(id);
    }

    @Override
    public boolean existJob(String id) throws TodolistException {
        return this.todolistRepository.existsById(id);
    }

    @Override
    public List<Todolist> getJobList(int limit, long min, long max, boolean check) throws TodolistException {
        return this.todolistRepository.findCustom(limit, min, max, check);
    }
}
