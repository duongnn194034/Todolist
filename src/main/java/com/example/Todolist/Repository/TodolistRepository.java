package com.example.Todolist.Repository;

import com.example.Todolist.Model.Todolist;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface TodolistRepository {
    Todolist save(Todolist job) throws TodolistException;
    Todolist findById(String ID) throws TodolistException;
    Todolist findByIdAndModify(String id, Update update) throws TodolistException;

    void deleteById(String id) throws TodolistException;

    List<Todolist> findCustom(long min, long max, boolean check) throws TodolistException;

    boolean existsById(String id) throws TodolistException;


//    List<Todojob> findByScoreBetween(long from, long to, Pageable pageable);
//    List<Todojob> findByCheckAndByScoreBetween(boolean check, long from, long to, Pageable pageable);
}
