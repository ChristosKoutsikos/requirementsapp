package com.softeng.requirementsapp.generator.factory;

import org.springframework.stereotype.Component;

import com.softeng.requirementsapp.enums.DiagramTool;
import com.softeng.requirementsapp.generator.NomnomlClassDiagramGenerator;
import com.softeng.requirementsapp.generator.PlantUmlClassDiagramGenerator;
import com.softeng.requirementsapp.generator.strategy.ClassDiagramGeneratorStrategy;

@Component
public class ClassDiagramGeneratorFactory {

    private final PlantUmlClassDiagramGenerator plantUmlClassDiagramGenerator;
    private final NomnomlClassDiagramGenerator nomnomlClassDiagramGenerator;

    public ClassDiagramGeneratorFactory(PlantUmlClassDiagramGenerator plantUmlClassDiagramGenerator,
                                        NomnomlClassDiagramGenerator nomnomlClassDiagramGenerator) {
        this.plantUmlClassDiagramGenerator = plantUmlClassDiagramGenerator;
        this.nomnomlClassDiagramGenerator = nomnomlClassDiagramGenerator;
    }

    public ClassDiagramGeneratorStrategy create(DiagramTool tool) {
        return switch (tool) {
            case PLANTUML -> plantUmlClassDiagramGenerator;
            case NOMNOML -> nomnomlClassDiagramGenerator;
        };
    }
}