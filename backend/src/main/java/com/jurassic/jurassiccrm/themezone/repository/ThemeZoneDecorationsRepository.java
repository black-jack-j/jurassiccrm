package com.jurassic.jurassiccrm.themezone.repository;

import com.jurassic.jurassiccrm.themezone.entity.ThemeZoneDecorations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ThemeZoneDecorationsRepository extends
        CrudRepository<ThemeZoneDecorations, Long>,
        JpaRepository<ThemeZoneDecorations, Long> {
}
