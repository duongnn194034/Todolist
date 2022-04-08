package com.example.Todolist.Controller;

import com.example.Todolist.Model.Todojob;
import com.example.Todolist.Service.TodojobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TodojobController {
    @Autowired
    private TodojobService todojobService;

    @GetMapping("/show/{limit}/{minscore}/{maxscore}")
    public List<Todojob> show(@PathVariable int limit, @PathVariable long minscore, @PathVariable long maxscore) {
        return this.todojobService.showJobs(limit, minscore, maxscore, 2);
    }


}
