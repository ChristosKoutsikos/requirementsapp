package com.softeng.requirementsapp.generator.strategy;

import java.util.List;

import com.softeng.requirementsapp.domain.UseCase;

public interface UseCaseDiagramGeneratorStrategy {

    String generate(List<UseCase> useCases);
}