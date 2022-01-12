package com.jurassic.jurassiccrm.document.dao;

import com.jurassic.jurassiccrm.document.model.DinosaurPassport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DinosaurPassportRepository extends
        CrudRepository<DinosaurPassport, Long>,
        JpaRepository<DinosaurPassport, Long> {
    boolean existsByName(String name);
}
