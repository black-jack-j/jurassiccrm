package com.jurassic.jurassiccrm.decoration.controller;

import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.common.controller.SimpleEntityController;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityInputTO;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.decoration.dao.DecorationTypeRepository;
import com.jurassic.jurassiccrm.decoration.model.DecorationType;
import com.jurassic.jurassiccrm.logging.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/decoration/type")
@Api(tags = "decorationType")
public class DecorationTypeController extends SimpleEntityController<DecorationType> {

    @Autowired
    public DecorationTypeController(DecorationTypeRepository decorationTypeRepository, LogService logService) {
        super(decorationTypeRepository, DecorationType.class, logService);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "createDecoration", nickname = "createDecoration")
    public ResponseEntity<SimpleEntityOutputTO> createEntity(@RequestBody @Valid SimpleEntityInputTO inputTO,
                                                             @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        return super.createEntity(inputTO, userDetails);
    }

    @Override
    @GetMapping
    @ApiOperation(value = "getAllDecorations", nickname = "getAllDecorations")
    public ResponseEntity<List<SimpleEntityOutputTO>> getAllEntities() {
        return super.getAllEntities();
    }

    @Override
    @PutMapping("/{id}")
    @ApiOperation(value = "updateDecoration", nickname = "updateDecoration")
    public ResponseEntity<SimpleEntityOutputTO> updateEntity(@PathVariable Long id,
                                                             @RequestBody @Valid SimpleEntityInputTO inputTO,
                                                             @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        return super.updateEntity(id, inputTO, userDetails);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteDecoration", nickname = "deleteDecoration")
    public ResponseEntity<SimpleEntityOutputTO> deleteEntity(@PathVariable Long id,
                                                             @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        return super.deleteEntity(id, userDetails);
    }
}
