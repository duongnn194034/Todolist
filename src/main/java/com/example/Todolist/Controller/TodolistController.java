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

    @RequestMapping("/add/{title}/{description}/{score}") //Hoạt động
    public Todolist add(@PathVariable String title, @PathVariable String description, @PathVariable long score) {
        return this.todolistService.addJob(title, description, score);
    }

    @RequestMapping("/exist/{id}") //Hoạt động
    public boolean foo(@PathVariable String id) {
        return this.todolistService.existJob(id);
    }

    @RequestMapping("/show/{limit}/{min}/{max}/{check}") //Không hoạt động
    public List<Todolist> func(Model model,
                               @PathVariable int limit,
                               @PathVariable long min,
                               @PathVariable long max,
                               @PathVariable boolean check) {
        Pageable pageable = PageRequest.of(0, limit);
        return this.todolistService.getJobList(min, max, check);
    }

    @RequestMapping("/modify/{id}/{title}/{description}/{score}/{check}") //Không hiển thị giao diện (lỗi 500) nhưng vẫn thao tác database
    public Todolist function(@PathVariable String id,
                             @PathVariable String title,
                             @PathVariable String description,
                             @PathVariable long score,
                             @PathVariable boolean check) {
        return this.todolistService.modifyJob(id, title, description, score, check);
    }

    @RequestMapping("/delete/{id}") //Hoạt động
    public String del(@PathVariable String id) {
        this.todolistService.deleteJob(id);
        return String.format("%s has been deleted", id);
    }

    @RequestMapping("/find/{id}") //Không hoạt động trong trường hợp tìm thấy
    @ResponseBody
    public Todolist findJ (@PathVariable String id) {
        return this.todolistService.findJob(id);
    }

    @GetMapping("/")
    public String index() {
        return "<h1>Home Page</h1>";
    }


}
