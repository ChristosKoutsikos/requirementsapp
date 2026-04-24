package com.softeng.requirementsapp.generator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.softeng.requirementsapp.domain.CRCCard;
import com.softeng.requirementsapp.generator.strategy.ClassDiagramGeneratorStrategy;

@Component
public class NomnomlClassDiagramGenerator implements ClassDiagramGeneratorStrategy {

    @Override
    public String generate(List<CRCCard> crcCards) {
        StringBuilder sb = new StringBuilder();

        for (CRCCard card : crcCards) {
            sb.append("[")
              .append(card.getClassName());

            if (card.getResponsibilities() != null && !card.getResponsibilities().isBlank()) {
                sb.append("|").append(card.getResponsibilities());
            }

            sb.append("]\n");
        }

        return sb.toString();
    }
}