package com.jurassic.jurassiccrm.dinosaur.dao;

import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface DinosaurTypeRepository extends Repository<DinosaurType, Long>, CrudRepository<DinosaurType, Long> {
    Optional<DinosaurType> findByName(String name);
}
