package com.example.Todolist.Controller;

import com.example.Todolist.Model.Todolist;
import com.example.Todolist.Service.TodolistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodolistController {
    @Autowired
    private TodolistService todolistService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Todolist add(@RequestParam String title,
                        @RequestParam String description,
                        @RequestParam long score,
                        @RequestParam(name = "check", required = false, defaultValue = "false") boolean check) {
        return this.todolistService.addJob(title, description, score, check);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Todolist> find(Model model,
                               @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
                               @RequestParam(name = "min", required = false, defaultValue = "1") long min,
                               @RequestParam(name = "max", required = false, defaultValue = "100") long max,
                               @RequestParam(name = "check", required = false, defaultValue = "false") boolean check) {
        return this.todolistService.getJobList(limit, min, max, check);
    }

    @RequestMapping(value = "/list", method = RequestMethod.PUT)
    public Todolist modify(@RequestParam(name = "id") String id,
                             @RequestParam(name = "title") String title,
                             @RequestParam(name = "description") String description,
                             @RequestParam(name = "score") long score,
                             @RequestParam(name = "check", defaultValue = "false") boolean check) {
        return this.todolistService.modifyJob(id, title, description, score, check);
    }

    @RequestMapping(value = "/list",method = RequestMethod.DELETE)
    public String del(@RequestParam(name = "id") String id) {
        this.todolistService.deleteJob(id);
        return String.format("%s has been deleted", id);
    }

    @GetMapping("/")
    public String index() {
        return "<h1>Home Page</h1>";
    }


}
