package com.softeng.requirementsapp.generator.factory;

import org.springframework.stereotype.Component;

import com.softeng.requirementsapp.enums.DiagramTool;
import com.softeng.requirementsapp.generator.NomnomlUseCaseGenerator;
import com.softeng.requirementsapp.generator.PlantUmlUseCaseGenerator;
import com.softeng.requirementsapp.generator.strategy.UseCaseDiagramGeneratorStrategy;

@Component
public class UseCaseDiagramGeneratorFactory {

    private final PlantUmlUseCaseGenerator plantUmlUseCaseGenerator;
    private final NomnomlUseCaseGenerator nomnomlUseCaseGenerator;

    public UseCaseDiagramGeneratorFactory(PlantUmlUseCaseGenerator plantUmlUseCaseGenerator,
                                          NomnomlUseCaseGenerator nomnomlUseCaseGenerator) {
        this.plantUmlUseCaseGenerator = plantUmlUseCaseGenerator;
        this.nomnomlUseCaseGenerator = nomnomlUseCaseGenerator;
    }

    public UseCaseDiagramGeneratorStrategy create(DiagramTool tool) {
        return switch (tool) {
            case PLANTUML -> plantUmlUseCaseGenerator;
            case NOMNOML -> nomnomlUseCaseGenerator;
        };
    }
}