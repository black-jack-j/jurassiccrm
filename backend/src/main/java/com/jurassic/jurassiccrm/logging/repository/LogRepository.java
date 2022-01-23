package com.jurassic.jurassiccrm.logging.repository;

import com.jurassic.jurassiccrm.logging.model.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<LogEntry, Long>, JpaRepository<LogEntry, Long> {
}
