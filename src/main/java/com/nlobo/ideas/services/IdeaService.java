package com.nlobo.ideas.services;

import com.nlobo.ideas.models.Idea;
import com.nlobo.ideas.models.User;
import com.nlobo.ideas.repositories.IdeaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdeaService {
    private final IdeaRepository ideaRepository;
    public IdeaService(IdeaRepository ideaRepository) { this.ideaRepository = ideaRepository; }

    public List<Idea> findAll() { return this.ideaRepository.findAll(); }

    public Idea create(Idea idea) { return ideaRepository.save(idea); }

    public Idea findIdeaById(Long id) {
        Optional<Idea> optionalIdea = ideaRepository.findById(id);
        if (optionalIdea.isPresent()) {
            return optionalIdea.get();
        } else {
            return null;
        }
    }

    public Idea update(Idea idea) { return ideaRepository.save(idea); }

    public void delete(Idea idea) { ideaRepository.delete(idea); }

    public void addLike(Idea idea, User user) {
        idea.getLikes().add(user);
        ideaRepository.save(idea);
    }

    public void removeLike(Idea idea, User user) {
        idea.getLikes().remove(user);
        ideaRepository.save(idea);
    }

    public List<Idea> getAllByLikesCountDesc() { return ideaRepository.findAllByOrderByLikesCountDesc(); }

    public List<Idea> getAllByLikesCountAsc() { return ideaRepository.findAllByOrderByLikesCountAsc(); }
}
