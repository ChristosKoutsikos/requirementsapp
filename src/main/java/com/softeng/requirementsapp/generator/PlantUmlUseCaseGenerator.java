package com.softeng.requirementsapp.generator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.softeng.requirementsapp.domain.Actor;
import com.softeng.requirementsapp.domain.UseCase;
import com.softeng.requirementsapp.generator.strategy.UseCaseDiagramGeneratorStrategy;

@Component
public class PlantUmlUseCaseGenerator implements UseCaseDiagramGeneratorStrategy {

    @Override
    public String generate(List<UseCase> useCases) {
        StringBuilder sb = new StringBuilder();
        sb.append("@startuml\n");

        for (UseCase useCase : useCases) {
            sb.append("usecase \"").append(useCase.getTitle()).append("\" as UC").append(useCase.getId()).append("\n");

            for (Actor actor : useCase.getActors()) {
                sb.append("actor \"").append(actor.getName()).append("\" as A").append(actor.getId()).append("\n");
                sb.append("A").append(actor.getId()).append(" --> UC").append(useCase.getId()).append("\n");
            }
        }

        sb.append("@enduml");
        return sb.toString();
    }
}