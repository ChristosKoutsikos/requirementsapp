package com.softeng.requirementsapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.softeng.requirementsapp.domain.Actor;
import com.softeng.requirementsapp.repositories.ActorRepository;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    public Actor findById(Long id) {
        return actorRepository.findById(id).orElse(null);
    }
}