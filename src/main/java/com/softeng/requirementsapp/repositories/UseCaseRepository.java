package com.softeng.requirementsapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softeng.requirementsapp.domain.Project;
import com.softeng.requirementsapp.domain.UseCase;

public interface UseCaseRepository extends JpaRepository<UseCase, Long> {

    List<UseCase> findByProject(Project project);
}