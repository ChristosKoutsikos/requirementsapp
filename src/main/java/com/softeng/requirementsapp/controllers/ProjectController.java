package com.softeng.requirementsapp.controllers;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.softeng.requirementsapp.domain.Project;
import com.softeng.requirementsapp.domain.User;
import com.softeng.requirementsapp.services.ProjectService;

@Controller
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public String viewProjects(@AuthenticationPrincipal User user, Model model) {
        List<Project> projects = projectService.findProjectsByOwner(user);
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/projects/new")
    public String createProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "create-project";
    }

    @PostMapping("/projects")
    public String createProject(@ModelAttribute Project project,
                                @AuthenticationPrincipal User user) {

        project.setOwner(user);
        projectService.saveProject(project);

        return "redirect:/projects";
    }
    @GetMapping("/projects/edit/{id}")
    public String editProjectForm(@PathVariable Long id, Model model) {
        Project project = projectService.findById(id);
        model.addAttribute("project", project);
        return "edit-project";
    }

    @PostMapping("/projects/update/{id}")
    public String updateProject(@PathVariable Long id,
                                @ModelAttribute Project project) {

        Project existing = projectService.findById(id);
        existing.setTitle(project.getTitle());
        existing.setDescription(project.getDescription());

        projectService.saveProject(existing);

        return "redirect:/projects";
    }
    @GetMapping("/projects/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "redirect:/projects";
    }
}