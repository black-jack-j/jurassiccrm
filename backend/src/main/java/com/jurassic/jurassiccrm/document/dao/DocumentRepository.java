package com.jurassic.jurassiccrm.document.dao;

import com.jurassic.jurassiccrm.document.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends
        JpaRepository<Document, Long>,
        CrudRepository<Document, Long> {
}
