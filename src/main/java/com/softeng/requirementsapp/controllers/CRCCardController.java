package com.softeng.requirementsapp.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.softeng.requirementsapp.domain.CRCCard;
import com.softeng.requirementsapp.domain.Project;
import com.softeng.requirementsapp.domain.UseCase;
import com.softeng.requirementsapp.services.CRCCardService;
import com.softeng.requirementsapp.services.ProjectService;
import com.softeng.requirementsapp.services.UseCaseService;

@Controller
public class CRCCardController {

    private final CRCCardService crcCardService;
    private final ProjectService projectService;
    private final UseCaseService useCaseService;

    public CRCCardController(CRCCardService crcCardService,
                             ProjectService projectService,
                             UseCaseService useCaseService) {
        this.crcCardService = crcCardService;
        this.projectService = projectService;
        this.useCaseService = useCaseService;
    }

    @GetMapping("/projects/{projectId}/crc-cards")
    public String listCards(@PathVariable Long projectId, Model model) {
        Project project = projectService.findById(projectId);

        List<CRCCard> cards = crcCardService.findByProject(project);
        List<UseCase> useCases = useCaseService.findByProject(project);

        model.addAttribute("project", project);
        model.addAttribute("cards", cards);
        model.addAttribute("useCases", useCases);

        return "crc-cards";
    }

    @GetMapping("/projects/{projectId}/crc-cards/new")
    public String createCardForm(@PathVariable Long projectId, Model model) {
        Project project = projectService.findById(projectId);

        CRCCard crcCard = new CRCCard();
        crcCard.setProject(project);

        model.addAttribute("project", project);
        model.addAttribute("crcCard", crcCard);

        return "create-crccard";
    }

    @PostMapping("/projects/{projectId}/crc-cards")
    public String saveCard(@PathVariable Long projectId,
                           @ModelAttribute CRCCard crcCard) {

        Project project = projectService.findById(projectId);
        crcCard.setProject(project);

        crcCardService.saveCard(crcCard);

        return "redirect:/projects/" + projectId + "/crc-cards";
    }

    @PostMapping("/crc-cards/{cardId}/link-usecase")
    public String linkUseCaseToCard(@PathVariable Long cardId,
                                    @RequestParam Long useCaseId) {

        CRCCard crcCard = crcCardService.findById(cardId);
        UseCase useCase = useCaseService.findById(useCaseId);

        crcCard.addUseCase(useCase);
        crcCardService.saveCard(crcCard);

        Long projectId = crcCard.getProject().getId();

        return "redirect:/projects/" + projectId + "/crc-cards";
    }

    @GetMapping("/crc-cards/delete/{id}")
    public String deleteCard(@PathVariable Long id) {
        CRCCard crcCard = crcCardService.findById(id);
        Long projectId = crcCard.getProject().getId();

        crcCardService.deleteCard(id);

        return "redirect:/projects/" + projectId + "/crc-cards";
    }
}