package com.example.Todolist.Controller;

import com.example.Todolist.Model.Todolist;
import com.example.Todolist.Service.TodolistService;
import com.example.Todolist.Service.TodolistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodolistController {
    @Autowired
    private TodolistService todolistService;

    @RequestMapping("/add/{title}/{description}/{score}")
    public Todolist add(@PathVariable String title, @PathVariable String description, @PathVariable long score) {
        return this.todolistService.addJob(title, description, score);
    }

    @RequestMapping("/exist/{id}")
    public boolean foo(@PathVariable String id) {
        return this.todolistService.existJob(id);
    }

    @RequestMapping("/show/{limit}/{min}/{max}/{check}")
    @ResponseBody
    public String func(Model model,
                               @PathVariable int limit,
                               @PathVariable long min,
                               @PathVariable long max,
                               @PathVariable boolean check) {
        String html = "";
        List<Todolist> list = this.todolistService.getJobList(limit, min, max, check);
        html += "<table style=\"border:1px solid black;\">";
        html += "<tr>";
        html += "<th style=\"border:1px solid black;\">ID</th>";
        html += "<th style=\"border:1px solid black;\">Title</th>";
        html += "<th style=\"border:1px solid black;\">Description</th>";
        html += "<th style=\"border:1px solid black;\">Create time</th>";
        html += "<th style=\"border:1px solid black;\">Last modified</th>";
        html += "<th style=\"border:1px solid black;\">Score</th>";
        html += "<th style=\"border:1px solid black;\">Check</th>";
        html += "</tr>";
        for(Todolist index : list)
            html += "<tr>" + "<td style=\"border:1px solid black;\">" + index.getId() + "</td>"
                    + "<td style=\"border:1px solid black;\">" + index.getTitle() + "</td>"
                    + "<td style=\"border:1px solid black;\">" + index.getDescription() + "</td>"
                    + "<td style=\"border:1px solid black;\">" + index.getCreateTime() + "</td>"
                    + "<td style=\"border:1px solid black;\">" + index.getLastModified() + "</td>"
                    + "<td style=\"border:1px solid black;\">" + index.getScore() + "</td>"
                    + "<td style=\"border:1px solid black;\">" + index.getCheck() + "</td>"
                    + "</tr>";
        html += "</table>";
        return html;
    }

    @RequestMapping("/modify/{id}/{title}/{description}/{score}/{check}")
    public Todolist function(@PathVariable String id,
                             @PathVariable String title,
                             @PathVariable String description,
                             @PathVariable long score,
                             @PathVariable boolean check) {
        return this.todolistService.modifyJob(id, title, description, score, check);
    }

    @RequestMapping("/delete/{id}")
    public String del(@PathVariable String id) {
        this.todolistService.deleteJob(id);
        return String.format("%s has been deleted", id);
    }

    @RequestMapping("/test")
    public Todolist test() {
        return new Todolist("This is a test.", "Anything", 5);
    }

    @GetMapping("/")
    public String index() {
        return "<h1>Home Page</h1>";
    }


}
