package com.example.Todolist.Controller;

import com.example.Todolist.Model.Todolist;
import com.example.Todolist.Service.TodolistService;
import com.example.Todolist.Service.TodolistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodolistController {
    @Autowired
    private TodolistService todolistService;

    @RequestMapping("/add/{title}/{description}/{score}") //Testing API
    public Todolist add(@PathVariable String title, @PathVariable String description, @PathVariable long score) {
        return this.todolistService.addJob(title, description, score);
    }

    @RequestMapping("/exist/{id}") //Testing-code route
    public boolean foo(@PathVariable String id) {
        return this.todolistService.existJob(id);
    }

    @RequestMapping("/show/{limit}/{min}/{max}/{check}")
    public List<Todolist> func(Model model,
                               @PathVariable int limit,
                               @PathVariable long min,
                               @PathVariable long max,
                               @PathVariable boolean check) {
        Pageable pageable = PageRequest.of(0, limit);
        return this.todolistService.getJobList(min, max, check);
    }

    @GetMapping("/")
    public String index() {
        return "Home page";
    }


}
