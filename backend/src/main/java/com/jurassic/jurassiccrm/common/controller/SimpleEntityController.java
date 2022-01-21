package com.jurassic.jurassiccrm.common.controller;

import com.jurassic.jurassiccrm.common.dto.SimpleEntityInputTO;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.common.model.SimpleEntity;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    @ApiOperation(value = "Creates", nickname = "create")
    public ResponseEntity<SimpleEntityOutputTO> createEntity(@RequestBody @Valid SimpleEntityInputTO inputTO) {
        try {
            val saved = repository.saveAndFlush(inputTO.toEntity(type));
            return ResponseEntity.ok(SimpleEntityOutputTO.fromEntity(saved));
        } catch (Throwable t) {
            log.warn(Arrays.toString(t.getStackTrace()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    @ApiOperation(value = "Get all", nickname = "getAll")
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

    @PutMapping("/{id}")
    @ApiOperation(value = "Update by id", nickname = "update")
    public ResponseEntity<SimpleEntityOutputTO> updateEntity(@PathVariable Long id,
                                                             @RequestBody @Valid SimpleEntityInputTO inputTO) {
        val foundEntity = repository.findById(id);
        if (!foundEntity.isPresent()) return ResponseEntity.badRequest().build();
        val entity = foundEntity.get();
        entity.setName(inputTO.getName());
        val saved = repository.saveAndFlush(entity);
        return ResponseEntity.ok(SimpleEntityOutputTO.fromEntity(saved));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete by id", nickname = "delete")
    public ResponseEntity<SimpleEntityOutputTO> deleteEntity(@PathVariable Long id) {
        val foundEntity = repository.findById(id);
        if (!foundEntity.isPresent()) return ResponseEntity.badRequest().build();
        val entity = foundEntity.get();
        repository.delete(entity);
        return ResponseEntity.ok(SimpleEntityOutputTO.fromEntity(entity));
    }
}
