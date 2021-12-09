package com.basestation.basestation.controllers;

import com.basestation.basestation.dao.MobileStationDao;
import com.basestation.basestation.model.MobileStation;
import com.basestation.basestation.service.Reports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mobile-station")
public class MobileStationController {

    @Autowired
    private MobileStationDao mobileStationDao;

    @PostMapping
    public ResponseEntity create(@RequestBody MobileStation mobileStation) {
        try {
            mobileStationDao.create(
                    mobileStation.getLastKnownX(),
                    mobileStation.getLastKnownY()
            );
            return ResponseEntity.badRequest().body("Mobile station saved.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Mobile station not saved.");
        }
    }

    @GetMapping("/getall")
    public List<MobileStation> getAllMobileStation() {
        return mobileStationDao.findAll();
    }

    @GetMapping("/location/{uuid}")
    public List<Reports> mobileStationLocation(@PathVariable("uuid") int id) {
        return mobileStationDao.mobileStationPositionReport(mobileStationDao.findById(id));
    }

}
