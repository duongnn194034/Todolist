package com.example.Todolist.Repository;

import com.example.Todolist.Model.Todolist;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TodolistRepository {
    Todolist save(Todolist job);
    Todolist findById(String ID);
    Todolist findByIdAndModify(String id, Update update);

    void deleteById(String id);

    List<Todolist> findCustom(long min, long max, boolean check);

    boolean existsById(String id);


//    List<Todojob> findByScoreBetween(long from, long to, Pageable pageable);
//    List<Todojob> findByCheckAndByScoreBetween(boolean check, long from, long to, Pageable pageable);
}
