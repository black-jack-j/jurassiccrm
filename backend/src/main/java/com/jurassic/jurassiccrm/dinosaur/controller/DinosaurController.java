package com.jurassic.jurassiccrm.dinosaur.controller;

import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.aviary.dto.RevisionEntryTO;
import com.jurassic.jurassiccrm.common.controller.SimpleEntityController;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityInputTO;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.dinosaur.DinosaurStatus;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.dinosaur.service.DinosaurService;
import com.jurassic.jurassiccrm.logging.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api/dinosaur")
@Api(tags = "dinosaur")
public class DinosaurController extends SimpleEntityController<DinosaurType> {

    private final DinosaurService dinosaurService;

    @Autowired
    public DinosaurController(DinosaurTypeRepository dinosaurTypeRepository,
                              LogService logService, DinosaurService dinosaurService) {
        super(dinosaurTypeRepository, DinosaurType.class, logService);
        this.dinosaurService = dinosaurService;
    }

    @GetMapping("/revision")
    @ApiOperation(value = "upcoming revisions")
    public ResponseEntity<List<RevisionEntryTO>> getUpcomingRevisions() {
        return ResponseEntity.ok(dinosaurService.getNextDinosaurRevisions());
    }

    @Override
    @PostMapping("/type")
    @ApiOperation(value = "createDinosaurType", nickname = "createDinosaurType")
    public ResponseEntity<SimpleEntityOutputTO> createEntity(@RequestBody @Valid SimpleEntityInputTO inputTO,
                                                             @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        return super.createEntity(inputTO, userDetails);
    }

    @Override
    @GetMapping("/type")
    @ApiOperation(value = "getAllDinosaurTypes", nickname = "getAllDinosaurTypes")
    public ResponseEntity<List<SimpleEntityOutputTO>> getAllEntities() {
        return super.getAllEntities();
    }

    @Override
    @PutMapping("/type/{id}")
    @ApiOperation(value = "updateDinosaurType", nickname = "updateDinosaurType")
    public ResponseEntity<SimpleEntityOutputTO> updateEntity(@PathVariable Long id,
                                                             @RequestBody @Valid SimpleEntityInputTO inputTO,
                                                             @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        return super.updateEntity(id, inputTO, userDetails);
    }

    @Override
    @DeleteMapping("/type/{id}")
    @ApiOperation(value = "deleteDinosaurType", nickname = "deleteDinosaurType")
    public ResponseEntity<SimpleEntityOutputTO> deleteEntity(@PathVariable Long id,
                                                             @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        return super.deleteEntity(id, userDetails);
    }

    @GetMapping("/status")
    @ApiOperation(value = "getAllDinosaurStatuses", nickname = "getAllDinosaurStatuses")
    public ResponseEntity<List<DinosaurStatus>> getAllDinosaurStatuses() {
        return ResponseEntity.ok(Arrays.asList(DinosaurStatus.values()));
    }
}
