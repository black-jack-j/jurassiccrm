package com.jurassic.jurassiccrm.document.dao;

import com.jurassic.jurassiccrm.document.model.ThemeZoneProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ThemeZoneProjectRepository extends
        CrudRepository<ThemeZoneProject, Long>,
        JpaRepository<ThemeZoneProject, Long> {
    boolean existsByName(String name);
}
