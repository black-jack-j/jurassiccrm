package com.jurassic.jurassiccrm.aviary.dao;

import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AviaryTypeRepository extends JpaRepository<AviaryType, Long>, CrudRepository<AviaryType, Long> {
}
