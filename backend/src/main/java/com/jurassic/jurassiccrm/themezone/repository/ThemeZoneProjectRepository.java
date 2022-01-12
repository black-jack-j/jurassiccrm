package com.jurassic.jurassiccrm.themezone.repository;

import com.jurassic.jurassiccrm.themezone.model.ThemeZoneProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeZoneProjectRepository extends
        CrudRepository<ThemeZoneProject, Long>,
        JpaRepository<ThemeZoneProject, Long> {
    boolean existsByName(String name);
}
