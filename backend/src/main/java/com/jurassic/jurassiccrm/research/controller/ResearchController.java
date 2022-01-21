package com.jurassic.jurassiccrm.research.controller;

import com.jurassic.jurassiccrm.common.controller.SimpleEntityController;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityInputTO;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.research.dao.ResearchRepository;
import com.jurassic.jurassiccrm.research.model.Research;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "research")
@RequestMapping(path = "/api/research")
public class ResearchController extends SimpleEntityController<Research> {

    @Autowired
    public ResearchController(ResearchRepository researchRepository) {
        super(researchRepository, Research.class);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "Creates", nickname = "createResearch")
    public ResponseEntity<SimpleEntityOutputTO> createEntity(@RequestBody @Valid SimpleEntityInputTO inputTO) {
        return super.createEntity(inputTO);
    }

    @Override
    @GetMapping
    @ApiOperation(value = "Get all", nickname = "getAllResearches")
    public ResponseEntity<List<SimpleEntityOutputTO>> getAllEntities() {
        return super.getAllEntities();
    }

    @Override
    @PutMapping("/{id}")
    @ApiOperation(value = "Update by id", nickname = "updateResearch")
    public ResponseEntity<SimpleEntityOutputTO> updateEntity(@PathVariable Long id,
                                                             @RequestBody @Valid SimpleEntityInputTO inputTO) {
        return super.updateEntity(id, inputTO);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete by id", nickname = "deleteResearch")
    public ResponseEntity<SimpleEntityOutputTO> deleteEntity(@PathVariable Long id) {
        return super.deleteEntity(id);
    }
}
