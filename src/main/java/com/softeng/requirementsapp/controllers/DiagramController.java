package com.softeng.requirementsapp.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.softeng.requirementsapp.domain.CRCCard;
import com.softeng.requirementsapp.domain.Project;
import com.softeng.requirementsapp.domain.UseCase;
import com.softeng.requirementsapp.enums.DiagramTool;
import com.softeng.requirementsapp.generator.factory.ClassDiagramGeneratorFactory;
import com.softeng.requirementsapp.generator.factory.UseCaseDiagramGeneratorFactory;
import com.softeng.requirementsapp.services.CRCCardService;
import com.softeng.requirementsapp.services.ProjectService;
import com.softeng.requirementsapp.services.UseCaseService;

@Controller
public class DiagramController {

    private final ProjectService projectService;
    private final UseCaseService useCaseService;
    private final CRCCardService crcCardService;
    private final UseCaseDiagramGeneratorFactory useCaseFactory;
    private final ClassDiagramGeneratorFactory classFactory;

    public DiagramController(ProjectService projectService,
                             UseCaseService useCaseService,
                             CRCCardService crcCardService,
                             UseCaseDiagramGeneratorFactory useCaseFactory,
                             ClassDiagramGeneratorFactory classFactory) {
        this.projectService = projectService;
        this.useCaseService = useCaseService;
        this.crcCardService = crcCardService;
        this.useCaseFactory = useCaseFactory;
        this.classFactory = classFactory;
    }

    @GetMapping("/projects/{projectId}/diagrams/usecases/{tool}")
    public String generateUseCaseDiagram(@PathVariable Long projectId,
                                         @PathVariable DiagramTool tool,
                                         Model model) {
        Project project = projectService.findById(projectId);
        List<UseCase> useCases = useCaseService.findByProject(project);

        String script = useCaseFactory.create(tool).generate(useCases);

        model.addAttribute("diagramType", "Use Case Diagram");
        model.addAttribute("tool", tool);
        model.addAttribute("script", script);
        model.addAttribute("project", project);

        return "diagram-result";
    }

    @GetMapping("/projects/{projectId}/diagrams/classes/{tool}")
    public String generateClassDiagram(@PathVariable Long projectId,
                                       @PathVariable DiagramTool tool,
                                       Model model) {
        Project project = projectService.findById(projectId);
        List<CRCCard> cards = crcCardService.findByProject(project);

        String script = classFactory.create(tool).generate(cards);

        model.addAttribute("diagramType", "Class Diagram");
        model.addAttribute("tool", tool);
        model.addAttribute("script", script);
        model.addAttribute("project", project);

        return "diagram-result";
    }
}