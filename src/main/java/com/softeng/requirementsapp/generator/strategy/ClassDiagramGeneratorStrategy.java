package com.softeng.requirementsapp.generator.strategy;

import java.util.List;

import com.softeng.requirementsapp.domain.CRCCard;

public interface ClassDiagramGeneratorStrategy {

    String generate(List<CRCCard> crcCards);
}