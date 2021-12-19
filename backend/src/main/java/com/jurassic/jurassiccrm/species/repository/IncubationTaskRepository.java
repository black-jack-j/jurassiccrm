package com.jurassic.jurassiccrm.species.repository;

import com.jurassic.jurassiccrm.document.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IncubationTaskRepository extends
        JpaRepository<Document, Long>,
        CrudRepository<Document, Long> {
}
