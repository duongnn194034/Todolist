package com.example.Todolist.Repository;

import com.example.Todolist.Model.Todojob;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodojobRepository extends MongoRepository<Todojob, String> {
    Todojob save(Todojob job);
    Optional<Todojob> findById(String ID);
    void deleteById(String id);

    List<Todojob> findByScoreBetween(long from, long to, Pageable pageable);
    List<Todojob> findByCheckAndByScoreBetween(int check, long from, long to, Pageable pageable);
}
