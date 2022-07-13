package com.stockkarte.controller;

import com.stockkarte.exception.ResourceNotFoundException;
import com.stockkarte.models.Record;
import com.stockkarte.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RecordController {

    @Autowired
    public RecordRepository recordRepository;

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
    public Record createRecord(@Valid @RequestBody Record record, @PathVariable(value = "id") Long hiveId) {
        System.out.println(hiveId);
        return recordRepository.save(record);
    }

    @PutMapping("/record")
    public void updateRecord(@Valid @RequestBody Record record) throws ResourceNotFoundException {
        Long recordId = record.getId();
        Record recordFromDb = recordRepository.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found with followind id :: " + recordId));
        recordFromDb.setName(record.getName());
        recordFromDb.setTask(record.getTask());
        recordFromDb.setTemperature(record.getTemperature());
        recordFromDb.setWeather(record.getWeather());
        //recordFromDb.setI


    }
}
