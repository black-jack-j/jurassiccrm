package com.jurassic.jurassiccrm.dinosaur.dao;

import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface DinosaurTypeRepository extends Repository<DinosaurType, Long>, CrudRepository<DinosaurType, Long> {
}
