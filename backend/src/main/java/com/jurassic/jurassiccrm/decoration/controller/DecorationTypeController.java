package com.jurassic.jurassiccrm.decoration.controller;

import com.jurassic.jurassiccrm.common.controller.SimpleEntityController;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityInputTO;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.decoration.dao.DecorationTypeRepository;
import com.jurassic.jurassiccrm.decoration.model.DecorationType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/decoration/type")
@Api(tags = "decorationType")
public class DecorationTypeController extends SimpleEntityController<DecorationType> {

    @Autowired
    public DecorationTypeController(DecorationTypeRepository decorationTypeRepository) {
        super(decorationTypeRepository, DecorationType.class);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "Creates", nickname = "createDecoration")
    public ResponseEntity<SimpleEntityOutputTO> createEntity(@RequestBody @Valid SimpleEntityInputTO inputTO) {
        return super.createEntity(inputTO);
    }

    @Override
    @GetMapping
    @ApiOperation(value = "Get all", nickname = "getAllDecorations")
    public ResponseEntity<List<SimpleEntityOutputTO>> getAllEntities() {
        return super.getAllEntities();
    }

    @Override
    @PutMapping("/{id}")
    @ApiOperation(value = "Update by id", nickname = "updateDecoration")
    public ResponseEntity<SimpleEntityOutputTO> updateEntity(@PathVariable Long id,
                                                             @RequestBody @Valid SimpleEntityInputTO inputTO) {
        return super.updateEntity(id, inputTO);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete by id", nickname = "deleteDecoration")
    public ResponseEntity<SimpleEntityOutputTO> deleteEntity(@PathVariable Long id) {
        return super.deleteEntity(id);
    }
}
