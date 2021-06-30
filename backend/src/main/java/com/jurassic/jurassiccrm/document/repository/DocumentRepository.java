package com.jurassic.jurassiccrm.document.repository;

import com.jurassic.jurassiccrm.accesscontroll.entity.Role;
import com.jurassic.jurassiccrm.document.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface DocumentRepository extends
        JpaRepository<Document, Long>,
        CrudRepository<Document, Long> {

    Set<DocumentMeta> findAllBy();

    Set<DocumentMeta> findDistinctDocumentByRoles(Role role);

}
