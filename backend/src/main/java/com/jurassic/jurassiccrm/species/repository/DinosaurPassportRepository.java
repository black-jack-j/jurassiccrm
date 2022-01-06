package com.jurassic.jurassiccrm.species.repository;

import com.jurassic.jurassiccrm.species.entity.DinosaurPassport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DinosaurPassportRepository extends
        CrudRepository<DinosaurPassport, Long>,
        JpaRepository<DinosaurPassport, Long> {
}
