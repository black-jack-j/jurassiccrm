package com.jurassic.jurassiccrm.aviary.dao;

import com.jurassic.jurassiccrm.aviary.model.AviaryPassport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AviaryPassportRepository extends JpaRepository<AviaryPassport, Long>, CrudRepository<AviaryPassport, Long> {

    AviaryPassport findByCode(Long l);

}
