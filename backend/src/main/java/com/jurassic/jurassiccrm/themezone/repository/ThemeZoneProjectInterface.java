package com.jurassic.jurassiccrm.themezone.repository;

import com.jurassic.jurassiccrm.themezone.entity.ThemeZoneProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ThemeZoneProjectInterface extends
        CrudRepository<ThemeZoneProject, Long>,
        JpaRepository<ThemeZoneProject, Long> {
}
