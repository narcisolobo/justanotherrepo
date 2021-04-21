package com.nlobo.ideas.repositories;

import com.nlobo.ideas.models.Idea;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long> {
    List<Idea> findAll();
    List<Idea> findAllByOrderByLikesCountDesc();
    List<Idea> findAllByOrderByLikesCountAsc();
}
