package com.jurassic.jurassiccrm.aviary.controller;

import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.common.controller.SimpleEntityController;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityInputTO;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.logging.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/aviary/type")
@Api(tags = "aviary")
public class AviaryTypeController extends SimpleEntityController<AviaryType> {

    @Autowired
    public AviaryTypeController(AviaryTypeRepository aviaryTypeRepository, LogService logService) {
        super(aviaryTypeRepository, AviaryType.class, logService);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "createAviary", nickname = "createAviaryType")
    public ResponseEntity<SimpleEntityOutputTO> createEntity(@RequestBody @Valid SimpleEntityInputTO inputTO,
                                                             @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        return super.createEntity(inputTO, userDetails);
    }

    @Override
    @GetMapping
    @ApiOperation(value = "getAllAviaries", nickname = "getAllAviaryTypes")
    public ResponseEntity<List<SimpleEntityOutputTO>> getAllEntities() {
        return super.getAllEntities();
    }

    @Override
    @PutMapping("/{id}")
    @ApiOperation(value = "updateAviary", nickname = "updateAviaryType")
    public ResponseEntity<SimpleEntityOutputTO> updateEntity(@PathVariable Long id,
                                                             @RequestBody @Valid SimpleEntityInputTO inputTO,
                                                             @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        return super.updateEntity(id, inputTO, userDetails);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteAviary", nickname = "deleteAviaryType")
    public ResponseEntity<SimpleEntityOutputTO> deleteEntity(@PathVariable Long id,
                                                             @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        return super.deleteEntity(id, userDetails);
    }
}
