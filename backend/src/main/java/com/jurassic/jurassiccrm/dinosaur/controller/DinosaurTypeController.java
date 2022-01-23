package com.jurassic.jurassiccrm.dinosaur.controller;

import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.common.controller.SimpleEntityController;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityInputTO;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.logging.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/dinosaur/type")
@Tag(name = "dinosaurType")
public class DinosaurTypeController extends SimpleEntityController<DinosaurType> {

    @Autowired
    public DinosaurTypeController(DinosaurTypeRepository dinosaurTypeRepository, LogService logService) {
        super(dinosaurTypeRepository, DinosaurType.class, logService);
    }

    @Override
    @PostMapping
    @Operation(operationId = "createDinosaur")
    public ResponseEntity<SimpleEntityOutputTO> createEntity(@RequestBody @Valid SimpleEntityInputTO inputTO,
                                                             @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        return super.createEntity(inputTO, userDetails);
    }

    @Override
    @GetMapping
    @Operation(operationId = "getAllDinosaurs")
    public ResponseEntity<List<SimpleEntityOutputTO>> getAllEntities() {
        return super.getAllEntities();
    }

    @Override
    @PutMapping("/{id}")
    @Operation(operationId = "updateDinosaur")
    public ResponseEntity<SimpleEntityOutputTO> updateEntity(@PathVariable Long id,
                                                             @RequestBody @Valid SimpleEntityInputTO inputTO,
                                                             @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        return super.updateEntity(id, inputTO, userDetails);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(operationId = "deleteDinosaur")
    public ResponseEntity<SimpleEntityOutputTO> deleteEntity(@PathVariable Long id,
                                                             @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        return super.deleteEntity(id, userDetails);
    }
}
