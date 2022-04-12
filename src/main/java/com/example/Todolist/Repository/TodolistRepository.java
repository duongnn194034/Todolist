package com.example.Todolist.Repository;

import com.example.Todolist.Model.Todolist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
//
@Repository
public interface TodolistRepository extends MongoRepository<Todolist, String> {
    Todolist save(Todolist job);
    Optional<Todolist> findById(String ID);
    void deleteById(String id);

//    List<Todojob> findByScoreBetween(long from, long to, Pageable pageable);
//    List<Todojob> findByCheckAndByScoreBetween(boolean check, long from, long to, Pageable pageable);
}
