package com.softeng.requirementsapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softeng.requirementsapp.domain.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}