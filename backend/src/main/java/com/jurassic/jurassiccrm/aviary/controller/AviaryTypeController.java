package com.jurassic.jurassiccrm.aviary.controller;

import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.common.controller.SimpleEntityController;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityInputTO;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/aviary/type")
@Api(tags = "aviaryType")
public class AviaryTypeController extends SimpleEntityController<AviaryType> {

    @Autowired
    public AviaryTypeController(AviaryTypeRepository aviaryTypeRepository) {
        super(aviaryTypeRepository, AviaryType.class);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "Creates", nickname = "createAviary")
    public ResponseEntity<SimpleEntityOutputTO> createEntity(@RequestBody @Valid SimpleEntityInputTO inputTO) {
        return super.createEntity(inputTO);
    }

    @Override
    @GetMapping
    @ApiOperation(value = "Get all", nickname = "getAllAviaries")
    public ResponseEntity<List<SimpleEntityOutputTO>> getAllEntities() {
        return super.getAllEntities();
    }

    @Override
    @PutMapping("/{id}")
    @ApiOperation(value = "Update by id", nickname = "updateAviary")
    public ResponseEntity<SimpleEntityOutputTO> updateEntity(@PathVariable Long id,
                                                             @RequestBody @Valid SimpleEntityInputTO inputTO) {
        return super.updateEntity(id, inputTO);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete by id", nickname = "deleteAviary")
    public ResponseEntity<SimpleEntityOutputTO> deleteEntity(@PathVariable Long id) {
        return super.deleteEntity(id);
    }
}
