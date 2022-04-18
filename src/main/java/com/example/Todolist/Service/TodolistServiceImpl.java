package com.example.Todolist.Service;

import com.example.Todolist.Model.Todolist;
import com.example.Todolist.Repository.TodolistException;
import com.example.Todolist.Repository.TodolistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class TodolistServiceImpl implements TodolistService{
    @Autowired
    private final TodolistRepository todolistRepository;

    @Autowired
    public TodolistServiceImpl(TodolistRepository todolistRepository) {
        this.todolistRepository = todolistRepository;
    }

    @Override
    public Todolist addJob(String title, String description, long score, boolean check) throws TodolistException {
        Todolist job = new Todolist(title, description, score, check);
        return this.todolistRepository.save(job);
    }

    //Modify job by ID
    @Override
    public Todolist modifyJob(String id, String title, String description, long score, boolean check) throws TodolistException {
        Update update = new Update();
        update.set("title", title);
        update.set("description", description);
        update.set("score", score);
        update.set("check", check);
        Calendar calendar = Calendar.getInstance();
        update.set("lastModified", calendar.getTime());

        Criteria criteria = new Criteria("id");
        criteria.is(id);

        return this.todolistRepository.findOneAndModify(criteria, update);
    }

    @Override
    public void deleteJob(String id) throws TodolistException {
        Criteria criteria = new Criteria("id");
        criteria.is(id);
        this.todolistRepository.deleteOne(criteria);
    }

    @Override
    public List<Todolist> getJobList(int limit, long min, long max, boolean check) throws TodolistException {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("score").gte(min),
                Criteria.where("score").lte(max),
                Criteria.where("check").is(check));
        query.addCriteria(criteria)
                .with(PageRequest.of(0, limit))
                .with(Sort.by(Sort.Direction.DESC, "score"));
        return this.todolistRepository.find(query);
    }
}
