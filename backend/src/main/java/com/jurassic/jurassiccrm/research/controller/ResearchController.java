package com.jurassic.jurassiccrm.research.controller;

import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.common.controller.SimpleEntityController;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityInputTO;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.document.dto.input.ResearchDataInputTO;
import com.jurassic.jurassiccrm.logging.service.LogService;
import com.jurassic.jurassiccrm.research.dao.ResearchRepository;
import com.jurassic.jurassiccrm.research.model.Research;
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
@Api(tags = "research")
@RequestMapping(path = "/api/research")
public class ResearchController extends SimpleEntityController<Research> {

    @Autowired
    public ResearchController(ResearchRepository researchRepository, LogService logService) {
        super(researchRepository, Research.class, logService);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "createResearch", nickname = "createResearch")
    public ResponseEntity<SimpleEntityOutputTO> createEntity(@RequestBody @Valid SimpleEntityInputTO inputTO,
                                                             @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        return super.createEntity(inputTO, userDetails);
    }

    @GetMapping(value = "/RESEARCH_DATA")
    @ApiOperation(value = "getResearchData", nickname = "getResearchData")
    public ResponseEntity<ResearchDataInputTO> getResearchData() throws IllegalAccessException {
        throw new IllegalAccessException("not implemented");
    }

    @Override
    @GetMapping
    @ApiOperation(value = "getAllResearches", nickname = "getAllResearches")
    public ResponseEntity<List<SimpleEntityOutputTO>> getAllEntities() {
        return super.getAllEntities();
    }

    @Override
    @PutMapping("/{id}")
    @ApiOperation(value = "updateResearch", nickname = "updateResearch")
    public ResponseEntity<SimpleEntityOutputTO> updateEntity(@PathVariable Long id,
                                                             @RequestBody @Valid SimpleEntityInputTO inputTO,
                                                             @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        return super.updateEntity(id, inputTO, userDetails);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteResearch", nickname = "deleteResearch")
    public ResponseEntity<SimpleEntityOutputTO> deleteEntity(@PathVariable Long id,
                                                             @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        return super.deleteEntity(id, userDetails);
    }
}
