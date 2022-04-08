package com.example.Todolist.Service;

import com.example.Todolist.Model.Todojob;
import com.example.Todolist.Repository.TodojobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class TodojobService {
    @Autowired
    private final TodojobRepository todojobRepository;

    public TodojobService(TodojobRepository todojobRepository) {
        this.todojobRepository = todojobRepository;
    }

    public Todojob addJob(String title, String description, long score) {
        Todojob job = new Todojob(title, description, score);
        this.todojobRepository.save(job);
        return job;
    }

    public Todojob modifyJob(String id, String title, String description, long score) {
        Todojob job = new Todojob(this.todojobRepository.findById(id).get());
        job.setId(id);
        job.setDescription(description);
        job.setTitle(title);
        job.setScore(score);
        this.todojobRepository.deleteById(id);
        this.todojobRepository.save(job);
        return job;
    }

    public void deleteJob(String id) {
        this.todojobRepository.deleteById(id);
    }

//    public List<Todojob> showJobs(int limit, long minScore, long maxScore, @RequestParam(required = false) Boolean check) {
//        Pageable pageable = PageRequest.of(0, limit);
//        if(check == null)
//            return this.todojobRepository.findByScoreBetween(minScore, maxScore, pageable);
//        else
//            return this.todojobRepository.findByCheckAndByScoreBetween(check, minScore, maxScore, pageable);
//
//    }
}
