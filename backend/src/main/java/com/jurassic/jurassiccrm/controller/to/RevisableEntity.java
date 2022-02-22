package com.jurassic.jurassiccrm.controller.to;

import java.time.Instant;

public interface RevisableEntity {

    Long getId();

    String getName();

    Instant getNextRevisionDate();

    Integer getRevisionPeriod();

}
