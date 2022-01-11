package com.jurassic.jurassiccrm.aviary.repository;

import com.jurassic.jurassiccrm.aviary.entity.AviaryPassport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AviaryPassportRepository extends
        CrudRepository<AviaryPassport, Long>,
        JpaRepository<AviaryPassport, Long>{
    boolean existsByName(String name);
}
