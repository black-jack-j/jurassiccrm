package com.jurassic.jurassiccrm.species.repository;

import com.jurassic.jurassiccrm.species.entity.DinosaurPassport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DinosaurPassportRepository extends
        CrudRepository<DinosaurPassport, Long>,
        JpaRepository<DinosaurPassport, Long> {
}
