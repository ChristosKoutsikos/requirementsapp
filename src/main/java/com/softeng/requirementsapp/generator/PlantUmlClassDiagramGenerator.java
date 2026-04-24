package com.softeng.requirementsapp.generator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.softeng.requirementsapp.domain.CRCCard;
import com.softeng.requirementsapp.generator.strategy.ClassDiagramGeneratorStrategy;

@Component
public class PlantUmlClassDiagramGenerator implements ClassDiagramGeneratorStrategy {

    @Override
    public String generate(List<CRCCard> crcCards) {
        StringBuilder sb = new StringBuilder();
        sb.append("@startuml\n");

        for (CRCCard card : crcCards) {
            sb.append("class ").append(card.getClassName()).append(" {\n");
            if (card.getResponsibilities() != null && !card.getResponsibilities().isBlank()) {
                sb.append("  .. Responsibilities ..\n");
                sb.append("  ").append(card.getResponsibilities().replace(",", "\n  ")).append("\n");
            }
            sb.append("}\n");
        }

        sb.append("@enduml");
        return sb.toString();
    }
}