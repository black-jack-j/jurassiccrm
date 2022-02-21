package com.jurassic.jurassiccrm.dinosaur.service;

import com.jurassic.jurassiccrm.aviary.dto.RevisionEntryTO;
import com.jurassic.jurassiccrm.aviary.dto.RevisionEntryTOBuilder;
import com.jurassic.jurassiccrm.document.dao.DinosaurPassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DinosaurService {

    private final DinosaurPassportRepository dinosaurRepository;
    private final RevisionEntryTOBuilder revisionEntryTOBuilder;

    @Autowired
    public DinosaurService(DinosaurPassportRepository dinosaurRepository,
                           RevisionEntryTOBuilder revisionEntryTOBuilder) {
        this.dinosaurRepository = dinosaurRepository;
        this.revisionEntryTOBuilder = revisionEntryTOBuilder;
    }

    public List<RevisionEntryTO> getNextDinosaurRevisions() {
        return dinosaurRepository.findAll().stream()
                .flatMap(revisionEntryTOBuilder::getNextRevisionEntries)
                .collect(Collectors.toList());
    }

}
