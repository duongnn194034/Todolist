package com.example.Todolist.Controller;

import com.example.Todolist.Model.Todolist;
import com.example.Todolist.Service.TodolistService;
import com.example.Todolist.Service.TodolistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodolistController {
    @Autowired
    private TodolistService todolistService;

    @GetMapping("/add/{title}/{description}/{score}")
    public Todolist add(@PathVariable String title, @PathVariable String description, @PathVariable long score) {
        return this.todolistService.addJob(title, description, score);
    }

    @GetMapping("/exist/{id}") //Testing-code route
    public boolean foo(@PathVariable String id) {
        return this.todolistService.existJob(id);
    }

    @GetMapping("/")
    public String index() {
        return "Home page";
    }


}
