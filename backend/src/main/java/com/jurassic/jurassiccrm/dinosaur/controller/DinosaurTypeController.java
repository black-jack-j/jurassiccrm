package com.jurassic.jurassiccrm.dinosaur.controller;

import com.jurassic.jurassiccrm.common.controller.SimpleEntityController;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/dinosaur/type")
public class DinosaurTypeController extends SimpleEntityController<DinosaurType> {

    @Autowired
    public DinosaurTypeController(DinosaurTypeRepository dinosaurTypeRepository) {
        super(dinosaurTypeRepository, DinosaurType.class);
    }
}
