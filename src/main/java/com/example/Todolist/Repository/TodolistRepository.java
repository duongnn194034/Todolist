package com.example.Todolist.Repository;

import com.example.Todolist.Model.Todolist;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface TodolistRepository {
    Todolist save(Todolist job) throws TodolistException;

    Todolist findOne(Criteria criteria) throws TodolistException;

    List<Todolist> find(Query query) throws TodolistException;

    Todolist findOneAndModify(Criteria criteria, Update update) throws TodolistException;

    void deleteOne(Criteria criteria);
}
