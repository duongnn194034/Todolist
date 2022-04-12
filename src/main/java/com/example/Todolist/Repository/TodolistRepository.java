package com.example.Todolist.Repository;

import com.example.Todolist.Model.Todolist;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TodolistRepository {
    Todolist save(Todolist job);
    Todolist findById(String ID);
    Todolist findByIdAndModify(String id, Update update);

    void deleteById(String id);
    Todolist findOne(Criteria criteria);

    boolean existsById(String id);


//    List<Todojob> findByScoreBetween(long from, long to, Pageable pageable);
//    List<Todojob> findByCheckAndByScoreBetween(boolean check, long from, long to, Pageable pageable);
}
