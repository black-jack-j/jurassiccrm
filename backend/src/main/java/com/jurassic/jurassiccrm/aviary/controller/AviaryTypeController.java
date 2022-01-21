package com.jurassic.jurassiccrm.aviary.controller;

import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.common.controller.SimpleEntityController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/aviary/type")
@Api(tags = "aviaryType")
public class AviaryTypeController extends SimpleEntityController<AviaryType> {

    @Autowired
    public AviaryTypeController(AviaryTypeRepository aviaryTypeRepository) {
        super(aviaryTypeRepository, AviaryType.class);
    }
}
