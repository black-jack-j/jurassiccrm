package com.jurassic.jurassiccrm.document.dao;

import com.jurassic.jurassiccrm.document.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends
        JpaRepository<Document, Long>,
        CrudRepository<Document, Long> {
    boolean existsByName(String name);
}
