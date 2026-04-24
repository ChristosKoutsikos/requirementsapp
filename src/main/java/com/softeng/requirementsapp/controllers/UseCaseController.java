package com.softeng.requirementsapp.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.softeng.requirementsapp.domain.Actor;
import com.softeng.requirementsapp.domain.Project;
import com.softeng.requirementsapp.domain.UseCase;
import com.softeng.requirementsapp.services.ActorService;
import com.softeng.requirementsapp.services.ProjectService;
import com.softeng.requirementsapp.services.UseCaseService;

@Controller
public class UseCaseController {

    private final UseCaseService useCaseService;
    private final ProjectService projectService;
    private final ActorService actorService;

    public UseCaseController(UseCaseService useCaseService,
                             ProjectService projectService,
                             ActorService actorService) {
        this.useCaseService = useCaseService;
        this.projectService = projectService;
        this.actorService = actorService;
    }

    @GetMapping("/projects/{projectId}/usecases")
    public String listUseCases(@PathVariable Long projectId, Model model) {
        Project project = projectService.findById(projectId);

        List<UseCase> useCases = useCaseService.findByProject(project);
        List<Actor> actors = actorService.findAll();

        model.addAttribute("project", project);
        model.addAttribute("useCases", useCases);
        model.addAttribute("actors", actors);

        return "usecases";
    }

    @GetMapping("/projects/{projectId}/usecases/new")
    public String createUseCaseForm(@PathVariable Long projectId, Model model) {
        Project project = projectService.findById(projectId);

        UseCase useCase = new UseCase();
        useCase.setProject(project);

        model.addAttribute("project", project);
        model.addAttribute("useCase", useCase);

        return "create-usecase";
    }

    @PostMapping("/projects/{projectId}/usecases")
    public String saveUseCase(@PathVariable Long projectId,
                              @ModelAttribute UseCase useCase) {

        Project project = projectService.findById(projectId);
        useCase.setProject(project);

        useCaseService.saveUseCase(useCase);

        return "redirect:/projects/" + projectId + "/usecases";
    }

    @PostMapping("/usecases/{id}/add-actor")
    public String addActorToUseCase(@PathVariable Long id,
                                   @RequestParam String actorName) {

        UseCase useCase = useCaseService.findById(id);

        Actor actor = new Actor();
        actor.setName(actorName);
        actorService.saveActor(actor);

        useCase.getActors().add(actor);
        useCaseService.saveUseCase(useCase);

        Long projectId = useCase.getProject().getId();

        return "redirect:/projects/" + projectId + "/usecases";
    }

    @GetMapping("/usecases/delete/{id}")
    public String deleteUseCase(@PathVariable Long id) {
        UseCase useCase = useCaseService.findById(id);
        Long projectId = useCase.getProject().getId();

        useCaseService.deleteUseCase(id);

        return "redirect:/projects/" + projectId + "/usecases";
    }
}