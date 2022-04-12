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
    public List<Todolist> findCustom(long min, long max, boolean check) {
        Query query = new Query();
        query.addCriteria(Criteria.where("score").gte(min));
        query.addCriteria(Criteria.where("score").lte(max));
        query.addCriteria(Criteria.where("check").is(check));
        return mongoTemplate.find(query, Todolist.class);
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
