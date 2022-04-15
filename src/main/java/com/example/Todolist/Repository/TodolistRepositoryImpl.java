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
            throw new TodolistException("Error in add!");
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
    public List<Todolist> findCustom(int limit, long min, long max, boolean check) throws TodolistException {
        try {
            Query query = new Query();
            Criteria criteria = new Criteria();
            criteria.andOperator(Criteria.where("score").gte(min),
                    Criteria.where("score").lte(max),
                    Criteria.where("check").is(check));
            query.addCriteria(criteria)
                    .with(PageRequest.of(0, limit))
                    .with(Sort.by(Sort.Direction.DESC, "score"));
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
