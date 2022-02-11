package com.jurassic.jurassiccrm.aviary.dto;

import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.document.model.AviaryPassport;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class AviaryRevisionEntryTOBuilder {

    @Value("${crm.aviary.revision.num:2}")
    private Integer revisionNum = 2;

    public Stream<AviaryRevisionEntryTO> getNextRevisionEntries(AviaryPassport passport) {
        val aviaryTO = new SimpleEntityOutputTO(passport.getId(), passport.getName());
        return passport.getNextRevisionDates(revisionNum).stream()
                .map(revisionDate -> new AviaryRevisionEntryTO(aviaryTO, revisionDate));
    }

}
