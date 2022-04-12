package com.example.Todolist.Repository;

import com.example.Todolist.Model.Todolist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class TodolistRepositoryImpl implements TodolistRepository {
    @Override
    public Todolist save(Todolist job) {
        return mongoTemplate.save(job, "Todolist");
    }

    @Override
    public Todolist findById(String ID) {
        return mongoTemplate.findById(ID, Todolist.class);
    }



    @Override
    public Todolist findByIdAndModify(String id, Update update) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findAndModify(query, update, Todolist.class);
    }


    @Override
    public void deleteById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.findAndRemove(query, Todolist.class);
    }



    @Override
    public Todolist findOne(Criteria criteria) {
        Query query = new Query();
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query, Todolist.class);
    }

    @Override
    public boolean existsById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, Todolist.class);
    }

    @Autowired
    private MongoTemplate mongoTemplate;

}