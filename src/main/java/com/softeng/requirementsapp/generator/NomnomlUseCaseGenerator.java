package com.softeng.requirementsapp.generator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.softeng.requirementsapp.domain.Actor;
import com.softeng.requirementsapp.domain.UseCase;
import com.softeng.requirementsapp.generator.strategy.UseCaseDiagramGeneratorStrategy;

@Component
public class NomnomlUseCaseGenerator implements UseCaseDiagramGeneratorStrategy {

    @Override
    public String generate(List<UseCase> useCases) {
        StringBuilder sb = new StringBuilder();

        for (UseCase useCase : useCases) {
            sb.append("[UseCase: ").append(useCase.getTitle()).append("]\n");

            for (Actor actor : useCase.getActors()) {
                sb.append("[Actor: ").append(actor.getName()).append("] -> [UseCase: ")
                  .append(useCase.getTitle()).append("]\n");
            }
        }

        return sb.toString();
    }
}