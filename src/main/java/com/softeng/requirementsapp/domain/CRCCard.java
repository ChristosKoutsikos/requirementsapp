package com.softeng.requirementsapp.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "crc_cards")
public class CRCCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String className;

    @Column(length = 2000)
    private String responsibilities;

    @Column(length = 2000)
    private String collaborations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToMany
    @JoinTable(
        name = "crc_card_use_cases",
        joinColumns = @JoinColumn(name = "crc_card_id"),
        inverseJoinColumns = @JoinColumn(name = "use_case_id")
    )
    private List<UseCase> useCases = new ArrayList<>();

    public CRCCard() {
    }

    public Long getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getCollaborations() {
        return collaborations;
    }

    public void setCollaborations(String collaborations) {
        this.collaborations = collaborations;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<UseCase> getUseCases() {
        return useCases;
    }

    public void setUseCases(List<UseCase> useCases) {
        this.useCases = useCases;
    }

    public void addUseCase(UseCase useCase) {
        if (!this.useCases.contains(useCase)) {
            this.useCases.add(useCase);
        }
    }
}