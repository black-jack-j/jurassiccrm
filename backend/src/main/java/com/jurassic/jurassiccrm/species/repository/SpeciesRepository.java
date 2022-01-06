package com.jurassic.jurassiccrm.species.repository;

import com.jurassic.jurassiccrm.species.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpeciesRepository extends
        CrudRepository<Species, Long>,
        JpaRepository<Species, Long> {

    Optional<Species> findByName(String name);
}
