package com.softeng.requirementsapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.softeng.requirementsapp.domain.Project;
import com.softeng.requirementsapp.domain.User;
import com.softeng.requirementsapp.repositories.ProjectRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> findProjectsByOwner(User owner) {
        return projectRepository.findByOwner(owner);
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}