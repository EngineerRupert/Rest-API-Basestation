package com.basestation.basestation.controllers;

import com.basestation.basestation.dao.BaseStationDao;
import com.basestation.basestation.model.BaseStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/base-station")
public class BaseStationController {

    @Autowired
    private BaseStationDao baseStationDao;

    @PostMapping
    public ResponseEntity createBaseStation(@RequestBody BaseStation baseStation) {
        try {
            baseStationDao.create(baseStation);
            return ResponseEntity.badRequest().body("Base station saved.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Base station not saved.");
        }
    }

    @GetMapping("/getall")
    public List<BaseStation> getAllMobileStation() {
        return baseStationDao.findAll();
    }

}
