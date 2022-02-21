package com.jurassic.jurassiccrm.aviary.dto;

import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.controller.to.RevisableEntity;
import com.jurassic.jurassiccrm.document.model.AviaryPassport;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class RevisionEntryTOBuilder {

    @Value("${crm.aviary.revision.num:2}")
    private Integer revisionNum = 2;

    public Stream<RevisionEntryTO> getNextRevisionEntries(RevisableEntity revisableEntity) {
        val entity = new SimpleEntityOutputTO(revisableEntity.getId(), revisableEntity.getName());
        List<Instant> nextRevisions = new ArrayList<>();
        final Instant baseDate = revisableEntity.getNextRevisionDate();
        nextRevisions.add(baseDate);
        for (int i = 1; i < revisionNum; i++) {
            nextRevisions.add(baseDate.plus(i * revisableEntity.getRevisionPeriod(), ChronoUnit.DAYS));
        }

        return nextRevisions.stream().map(revisionDate -> new RevisionEntryTO(entity, revisionDate));
    }

}
