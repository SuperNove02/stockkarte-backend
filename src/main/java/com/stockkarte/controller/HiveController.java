package com.stockkarte.controller;

import com.stockkarte.exception.ResourceNotFoundException;
import com.stockkarte.models.Hive;
import com.stockkarte.repository.HiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class HiveController {

    @Autowired
    private HiveRepository hiveRepository;

    @GetMapping("/hives")
    public List<Hive> getAllHives() {
        return hiveRepository.findAll();
    }

    @GetMapping("/hive/{id}")
    public ResponseEntity<Hive> getHiveById (@PathVariable(value="id") Long hiveId) throws ResourceNotFoundException {
        Hive hive = hiveRepository.findById(hiveId)
                .orElseThrow(()-> new ResourceNotFoundException("Hive not found with following id :: " + hiveId));
        return ResponseEntity.ok().body(hive);
    }

    @PostMapping("/hive")
    public Hive createHive(@Valid @RequestBody Hive hive) {
        return hiveRepository.save(hive);
    }

    @DeleteMapping("/hive/{id}")
    public Map<String, Boolean> deleteHive(@PathVariable(value="id") Long hiveId) throws ResourceNotFoundException{
        Hive hive = hiveRepository.findById(hiveId)
                .orElseThrow(() -> new ResourceNotFoundException("Hive not found with following id :: " + hiveId));
        hiveRepository.delete(hive);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @GetMapping("/hiveall/{id}")
    public String getAllRecordsForId (@PathVariable(value="id")long hiveId){
        Hive hive = hiveRepository.findById(hiveId).orElse();
        return "Hallo";
    }

    @PutMapping("/hive")
    public Hive updateHive(@Valid @RequestBody Hive hive) throws ResourceNotFoundException {
        Hive hiveFromDb = hiveRepository.findById(hive.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Hive not found with following id :: " + hive.getId()));
        hiveFromDb.setName(hive.getName());
        hiveFromDb.setSystem(hive.getSystem());
        hiveRepository.save(hiveFromDb);
        return hiveFromDb;
    }
}
