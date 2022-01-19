package com.jurassic.jurassiccrm.decoration.controller;

import com.jurassic.jurassiccrm.common.controller.SimpleEntityController;
import com.jurassic.jurassiccrm.decoration.dao.DecorationTypeRepository;
import com.jurassic.jurassiccrm.decoration.model.DecorationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/decoration/type")
public class DecorationTypeController extends SimpleEntityController<DecorationType> {

    @Autowired
    public DecorationTypeController(DecorationTypeRepository decorationTypeRepository) {
        super(decorationTypeRepository, DecorationType.class);
    }
}
