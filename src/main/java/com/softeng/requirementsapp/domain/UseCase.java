package com.softeng.requirementsapp.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "use_cases")
public class UseCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String preconditions;

    @Column(length = 3000)
    private String mainFlow;

    @Column(length = 2000)
    private String alternativeFlow;

    @Column(length = 1000)
    private String postconditions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToMany
    @JoinTable(
        name = "usecase_actors",
        joinColumns = @JoinColumn(name = "usecase_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors = new ArrayList<>();

    public UseCase() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreconditions() {
        return preconditions;
    }

    public void setPreconditions(String preconditions) {
        this.preconditions = preconditions;
    }

    public String getMainFlow() {
        return mainFlow;
    }

    public void setMainFlow(String mainFlow) {
        this.mainFlow = mainFlow;
    }

    public String getAlternativeFlow() {
        return alternativeFlow;
    }

    public void setAlternativeFlow(String alternativeFlow) {
        this.alternativeFlow = alternativeFlow;
    }

    public String getPostconditions() {
        return postconditions;
    }

    public void setPostconditions(String postconditions) {
        this.postconditions = postconditions;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}