package com.jurassic.jurassiccrm.aviary.service;

import com.jurassic.jurassiccrm.aviary.dto.RevisionEntryTO;
import com.jurassic.jurassiccrm.aviary.dto.RevisionEntryTOBuilder;
import com.jurassic.jurassiccrm.document.dao.AviaryPassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AviaryService {

    private final AviaryPassportRepository aviaryRepository;
    private final RevisionEntryTOBuilder revisionEntryTOBuilder;

    @Autowired
    public AviaryService(AviaryPassportRepository aviaryRepository,
                         RevisionEntryTOBuilder revisionEntryTOBuilder) {
        this.aviaryRepository = aviaryRepository;
        this.revisionEntryTOBuilder = revisionEntryTOBuilder;
    }

    public List<RevisionEntryTO> getNextAviaryRevisions() {
        return aviaryRepository.findAll().stream()
                .flatMap(revisionEntryTOBuilder::getNextRevisionEntries)
                .collect(Collectors.toList());
    }


}
