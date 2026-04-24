package com.softeng.requirementsapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.softeng.requirementsapp.domain.Project;
import com.softeng.requirementsapp.domain.UseCase;
import com.softeng.requirementsapp.repositories.UseCaseRepository;

@Service
public class UseCaseService {

    private final UseCaseRepository useCaseRepository;

    public UseCaseService(UseCaseRepository useCaseRepository) {
        this.useCaseRepository = useCaseRepository;
    }

    public UseCase saveUseCase(UseCase useCase) {
        return useCaseRepository.save(useCase);
    }

    public List<UseCase> findByProject(Project project) {
        return useCaseRepository.findByProject(project);
    }

    public UseCase findById(Long id) {
        return useCaseRepository.findById(id).orElse(null);
    }

    public void deleteUseCase(Long id) {
        useCaseRepository.deleteById(id);
    }
}