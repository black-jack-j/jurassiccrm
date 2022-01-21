package com.jurassic.jurassiccrm.dinosaur.controller;

import com.jurassic.jurassiccrm.common.controller.SimpleEntityController;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityInputTO;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/dinosaur/type")
@Api(tags = "dinosaurType")
public class DinosaurTypeController extends SimpleEntityController<DinosaurType> {

    @Autowired
    public DinosaurTypeController(DinosaurTypeRepository dinosaurTypeRepository) {
        super(dinosaurTypeRepository, DinosaurType.class);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "Creates", nickname = "createDinosaur")
    public ResponseEntity<SimpleEntityOutputTO> createEntity(@RequestBody @Valid SimpleEntityInputTO inputTO) {
        return super.createEntity(inputTO);
    }

    @Override
    @GetMapping
    @ApiOperation(value = "Get all", nickname = "getAllDinosaurs")
    public ResponseEntity<List<SimpleEntityOutputTO>> getAllEntities() {
        return super.getAllEntities();
    }

    @Override
    @PutMapping("/{id}")
    @ApiOperation(value = "Update by id", nickname = "updateDinosaur")
    public ResponseEntity<SimpleEntityOutputTO> updateEntity(@PathVariable Long id,
                                                             @RequestBody @Valid SimpleEntityInputTO inputTO) {
        return super.updateEntity(id, inputTO);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete by id", nickname = "deleteDinosaur")
    public ResponseEntity<SimpleEntityOutputTO> deleteEntity(@PathVariable Long id) {
        return super.deleteEntity(id);
    }
}
