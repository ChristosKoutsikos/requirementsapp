package com.softeng.requirementsapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.softeng.requirementsapp.domain.CRCCard;
import com.softeng.requirementsapp.domain.Project;
import com.softeng.requirementsapp.repositories.CRCCardRepository;

@Service
public class CRCCardService {

    private final CRCCardRepository crcCardRepository;

    public CRCCardService(CRCCardRepository crcCardRepository) {
        this.crcCardRepository = crcCardRepository;
    }

    public CRCCard saveCard(CRCCard crcCard) {
        return crcCardRepository.save(crcCard);
    }

    public List<CRCCard> findByProject(Project project) {
        return crcCardRepository.findByProject(project);
    }

    public CRCCard findById(Long id) {
        return crcCardRepository.findById(id).orElse(null);
    }

    public void deleteCard(Long id) {
        crcCardRepository.deleteById(id);
    }
}