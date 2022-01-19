package com.jurassic.jurassiccrm.research.controller;

import com.jurassic.jurassiccrm.common.controller.SimpleEntityController;
import com.jurassic.jurassiccrm.research.dao.ResearchRepository;
import com.jurassic.jurassiccrm.research.model.Research;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/research")
public class ResearchController extends SimpleEntityController<Research> {

    @Autowired
    public ResearchController(ResearchRepository researchRepository) {
        super(researchRepository, Research.class);
    }
}
