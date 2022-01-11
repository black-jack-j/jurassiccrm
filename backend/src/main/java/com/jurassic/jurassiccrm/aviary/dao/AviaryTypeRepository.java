package com.jurassic.jurassiccrm.aviary.dao;

import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface AviaryTypeRepository extends Repository<AviaryType, Long>, CrudRepository<AviaryType, Long> {
}
