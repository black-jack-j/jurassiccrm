package com.jurassic.jurassiccrm.aviary.repository;

import com.jurassic.jurassiccrm.document.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AviaryBuildingTaskRepository extends
        JpaRepository<Document, Long>,
        CrudRepository<Document, Long> {
}
