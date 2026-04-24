package com.softeng.requirementsapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softeng.requirementsapp.domain.CRCCard;
import com.softeng.requirementsapp.domain.Project;

public interface CRCCardRepository extends JpaRepository<CRCCard, Long> {

    List<CRCCard> findByProject(Project project);
}