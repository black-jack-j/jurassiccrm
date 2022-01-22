package com.jurassic.jurassiccrm.common.controller;

import com.jurassic.jurassiccrm.common.dto.SimpleEntityInputTO;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.common.model.SimpleEntity;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleEntityController<T extends SimpleEntity> {

    Logger log = LoggerFactory.getLogger(SimpleEntityController.class);
    private final JpaRepository<T, Long> repository;
    private final Class<T> type;

    public SimpleEntityController(JpaRepository<T, Long> repository, Class<T> type) {
        this.repository = repository;
        this.type = type;
    }

    public ResponseEntity<SimpleEntityOutputTO> createEntity(SimpleEntityInputTO inputTO) {
        try {
            val saved = repository.saveAndFlush(inputTO.toEntity(type));
            return ResponseEntity.ok(SimpleEntityOutputTO.fromEntity(saved));
        } catch (Throwable t) {
            log.warn(Arrays.toString(t.getStackTrace()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<List<SimpleEntityOutputTO>> getAllEntities() {
        try {
            val entities = repository.findAll();
            val dtoList = entities.stream().map(SimpleEntityOutputTO::fromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        } catch (Throwable t) {
            log.warn(Arrays.toString(t.getStackTrace()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<SimpleEntityOutputTO> updateEntity(Long id,
                                                             SimpleEntityInputTO inputTO) {
        val foundEntity = repository.findById(id);
        if (!foundEntity.isPresent()) return ResponseEntity.badRequest().build();
        val entity = foundEntity.get();
        entity.setName(inputTO.getName());
        val saved = repository.saveAndFlush(entity);
        return ResponseEntity.ok(SimpleEntityOutputTO.fromEntity(saved));
    }

    public ResponseEntity<SimpleEntityOutputTO> deleteEntity(Long id) {
        val foundEntity = repository.findById(id);
        if (!foundEntity.isPresent()) return ResponseEntity.badRequest().build();
        val entity = foundEntity.get();
        repository.delete(entity);
        return ResponseEntity.ok(SimpleEntityOutputTO.fromEntity(entity));
    }
}