package com.jurassic.jurassiccrm.decoration.dao;

import com.jurassic.jurassiccrm.decoration.model.DecorationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DecorationTypeRepository extends JpaRepository<DecorationType, Long>, CrudRepository<DecorationType, Long> {
    Optional<DecorationType> findByName(String name);
}
