package com.example.Todolist.Repository;

import com.example.Todolist.Model.Todolist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodolistRepositoryImpl implements TodolistRepository {
    @Override
    public Todolist save(Todolist job) throws TodolistException {
        try {
            return mongoTemplate.save(job, "Todolist");
        } catch (TodolistException tex) {
            throw new TodolistException("Error in add!");
        }
    }

    @Override
    public Todolist findById(String ID) throws TodolistException {
        try {
            return mongoTemplate.findById(ID, Todolist.class);
        } catch (TodolistException tex) {
            throw new TodolistException("Error in find!");
        }
    }



    @Override
    public Todolist findByIdAndModify(String id, Update update) throws TodolistException {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(id));
            return mongoTemplate.findAndModify(query, update, Todolist.class);
        } catch (TodolistException tex) {
            throw new TodolistException("Error in modify!");
        }
    }


    @Override
    public void deleteById(String id) throws TodolistException {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(id));
            mongoTemplate.findAndRemove(query, Todolist.class);
        } catch (TodolistException tex) {
            throw new TodolistException("Error in delete!");
        }
    }

    @Override
    public List<Todolist> findCustom(long min, long max, boolean check) throws TodolistException {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("score").gte(min));
            query.addCriteria(Criteria.where("score").lte(max));
            query.addCriteria(Criteria.where("check").is(check));
            return mongoTemplate.find(query, Todolist.class);
        } catch (TodolistException tex) {
            throw new TodolistException("Error in findCustom!");
        }
    }


    @Override
    public boolean existsById(String id) throws TodolistException {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(id));
            return mongoTemplate.exists(query, Todolist.class);
        } catch (TodolistException tex) {
            throw new TodolistException("Error in exists!");
        }
    }

    @Autowired
    private MongoTemplate mongoTemplate;

}
