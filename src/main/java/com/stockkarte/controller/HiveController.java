package com.stockkarte.controller;

import com.stockkarte.exception.ResourceNotFoundException;
import com.stockkarte.models.Hive;
import com.stockkarte.models.Record;
import com.stockkarte.models.User;
import com.stockkarte.repository.HiveRepository;
import com.stockkarte.repository.UserRepository;
import org.hibernate.ResourceClosedException;
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
    @Autowired
    private UserRepository userRepository;

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

    @PostMapping("/hive/{id}")
    public Hive createHive(@Valid @RequestBody Hive hive, @PathVariable(value = "id") Long userId) throws ResourceNotFoundException{
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with following id :: " + userId));
        hive.setUser(user);
        user.addHive(hive);
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
    public Hive getAllRecordsForId (@PathVariable(value="id")long hiveId) throws  ResourceNotFoundException{
        Hive hive = hiveRepository.findById(hiveId)
                .orElseThrow(() -> new ResourceNotFoundException("Hive not found with following id :: " + hiveId));
        return hive;
    }

    @PostMapping("/hive/record/{id}")
    public String createRecordForHiveId (@PathVariable(value="id") long hiveId, @Valid @RequestBody Record record) throws ResourceNotFoundException{
        Hive hive = hiveRepository.findById(hiveId)
                .orElseThrow(() -> new ResourceNotFoundException("Hive not found with following id :: "  + hiveId));
        hive.addRecord(record);
        return "true";
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
