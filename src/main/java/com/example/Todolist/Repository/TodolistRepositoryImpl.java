package com.example.Todolist.Repository;

import com.example.Todolist.Model.Todolist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import java.util.List;

@Repository
public class TodolistRepositoryImpl implements TodolistRepository {
    @Override
    public Todolist save(Todolist job) throws TodolistException {
        try {
            return this.mongoTemplate.save(job, "Todolist");
        } catch (TodolistException tex) {
            throw new TodolistException("Error in adding!");
        }
    }

    @Override
    public Todolist findOneAndModify(Criteria criteria, Update update) throws TodolistException {
        try {
            Query query = new Query();
            query.addCriteria(criteria);
            return this.mongoTemplate.findAndModify(query, update, Todolist.class);
        } catch (TodolistException tex) {
            throw new TodolistException("Error in modifying!");
        }
    }

    @Override
    public void deleteOne(Criteria criteria) {
        try {
            Query query = new Query(criteria);
            this.mongoTemplate.findAndRemove(query, Todolist.class);
        } catch (TodolistException tex) {
            throw new TodolistException("Error in deleting!");
        }
    }

    @Override
    public Todolist findOne(Criteria criteria) throws TodolistException {
        try {
            Query query = new Query(criteria);
            return this.mongoTemplate.findOne(query, Todolist.class);
        } catch (TodolistException tex) {
            throw new TodolistException("Error in findOne!");
        }
    }

    @Override
    public List<Todolist> find(Query query) throws TodolistException {
        try {
            return this.mongoTemplate.find(query, Todolist.class);
        } catch (TodolistException tex) {
            throw new TodolistException("Error in finding!");
        }
    }


    @Autowired
    private MongoTemplate mongoTemplate;

}
