package com.jurassic.jurassiccrm.research.controller;

import com.jurassic.jurassiccrm.common.controller.SimpleEntityController;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityInputTO;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.research.dao.ResearchRepository;
import com.jurassic.jurassiccrm.research.model.Research;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "research")
@RequestMapping(path = "/api/research")
public class ResearchController extends SimpleEntityController<Research> {

    @Autowired
    public ResearchController(ResearchRepository researchRepository) {
        super(researchRepository, Research.class);
    }

    @Override
    @PostMapping
    @Operation(operationId = "createResearch")
    public ResponseEntity<SimpleEntityOutputTO> createEntity(@RequestBody @Valid SimpleEntityInputTO inputTO) {
        return super.createEntity(inputTO);
    }

    @Override
    @GetMapping
    @Operation(operationId = "getAllResearches")
    public ResponseEntity<List<SimpleEntityOutputTO>> getAllEntities() {
        return super.getAllEntities();
    }

    @Override
    @PutMapping("/{id}")
    @Operation(operationId = "updateResearch")
    public ResponseEntity<SimpleEntityOutputTO> updateEntity(@PathVariable Long id,
                                                             @RequestBody @Valid SimpleEntityInputTO inputTO) {
        return super.updateEntity(id, inputTO);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(operationId = "deleteResearch")
    public ResponseEntity<SimpleEntityOutputTO> deleteEntity(@PathVariable Long id) {
        return super.deleteEntity(id);
    }
}
