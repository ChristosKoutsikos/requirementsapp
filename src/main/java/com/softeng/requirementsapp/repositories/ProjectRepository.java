package com.softeng.requirementsapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softeng.requirementsapp.domain.Project;
import com.softeng.requirementsapp.domain.User;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByOwner(User owner);
}