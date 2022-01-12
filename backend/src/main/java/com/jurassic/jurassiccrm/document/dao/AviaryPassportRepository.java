package com.jurassic.jurassiccrm.document.dao;

import com.jurassic.jurassiccrm.document.model.AviaryPassport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AviaryPassportRepository extends JpaRepository<AviaryPassport, Long>, CrudRepository<AviaryPassport, Long> {
    boolean existsByName(String name);
}
