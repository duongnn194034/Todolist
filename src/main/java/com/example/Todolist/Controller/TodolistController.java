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
        Pageable pageable = PageRequest.of(0, limit);
        String html = "";
        List<Todolist> list = this.todolistService.getJobList(min, max, check);
        html += "<ul>";
        for(Todolist index : list)
            html += "<li>" + index.toString() + "</li>";
        html += "</ul>";
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

    @RequestMapping("/find/{id}")
    public Todolist findJ(@PathVariable String id) {
        return this.todolistService.findJob(id);
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
