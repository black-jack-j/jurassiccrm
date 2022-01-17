package com.jurassic.jurassiccrm.dinosaur.dao;

import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DinosaurTypeRepository extends JpaRepository<DinosaurType, Long>, CrudRepository<DinosaurType, Long> {
    Optional<DinosaurType> findByName(String name);
}
