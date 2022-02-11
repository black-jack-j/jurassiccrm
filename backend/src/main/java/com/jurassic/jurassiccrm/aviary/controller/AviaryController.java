package com.jurassic.jurassiccrm.aviary.controller;

import com.jurassic.jurassiccrm.aviary.dto.AviaryRevisionEntryTO;
import com.jurassic.jurassiccrm.aviary.service.AviaryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "aviary", tags = "aviary")
@RestController
@RequestMapping("/api/aviary")
public class AviaryController {

    private final AviaryService aviaryService;

    @Autowired
    public AviaryController(AviaryService aviaryService) {
        this.aviaryService = aviaryService;
    }


    @GetMapping("/revision")
    public ResponseEntity<List<AviaryRevisionEntryTO>> getNextAviaryRevisions() {
        return ResponseEntity.ok(
                aviaryService.getNextAviaryRevisions()
        );
    }

}
