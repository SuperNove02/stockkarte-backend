package com.stockkarte.controller;

import com.stockkarte.exception.ResourceNotFoundException;
import com.stockkarte.models.Hive;
import com.stockkarte.models.Record;
import com.stockkarte.repository.HiveRepository;
import com.stockkarte.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class RecordController {

    @Autowired
    public RecordRepository recordRepository;

    @Autowired
    private HiveRepository hiveRepository;

    @GetMapping("/records")
    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    @GetMapping("/record/{id}")
    public Record getRecordById(@PathVariable(value="id") long recordId) throws ResourceNotFoundException {
        Record record = recordRepository.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("Hive not found with following id :: " + recordId));
        return record;
    }

    @PostMapping("/record/{id}")
    public Record createRecord(@Valid @RequestBody Record record, @PathVariable(value = "id") Long hiveId) throws  ResourceNotFoundException{
        Hive hive = hiveRepository.findById(hiveId)
                .orElseThrow(() -> new ResourceNotFoundException("Hive nof found with followind id :: " + hiveId));
        hive.addRecord(record);
        record.setHive(hive);
        return recordRepository.save(record);
    }

    @PutMapping("/record")
    public Record updateRecord(@Valid @RequestBody Record record) throws ResourceNotFoundException {
        Long recordId = record.getId();
        Record recordFromDb = recordRepository.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found with following id :: " + recordId));
        recordFromDb.setName(record.getName());
        recordFromDb.setTask(record.getTask());
        recordFromDb.setTemperature(record.getTemperature());
        recordFromDb.setWeather(record.getWeather());
        return recordRepository.save(recordFromDb);
    }

    @DeleteMapping("/record")
    public Map<String, Boolean> deleteRecord(@PathVariable(value="id") long recordId) throws ResourceNotFoundException {
        Record record = recordRepository.findById(recordId)
                        .orElseThrow(() -> new ResourceNotFoundException("Record not found with following id :: " + recordId));
        recordRepository.delete(record);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
