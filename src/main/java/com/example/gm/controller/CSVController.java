package com.example.gm.controller;

import com.example.gm.entity.Item;
import com.example.gm.service.CSVService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/csv")
public class CSVController {

    private final CSVService csvService;

    public CSVController(CSVService csvService) {
        this.csvService = csvService;
    }

    @PostMapping("/items")
    public ResponseEntity<List<Item>> uploadCSVFile(@RequestParam("file") MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Select a CSV file to upload and ensure it is not empty.");
        }

        List<Item> items = csvService.saveAll(file);
        return new ResponseEntity<>(items, HttpStatus.CREATED);
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> fetchAllItems() {

            List<Item> items = csvService.fetchAll();
            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/items/{code}")
    public ResponseEntity<Item> fetchByCode(@PathVariable String code) {

        Item item = csvService.fetchByCode(code).orElseThrow(
                () -> new RuntimeException(String.format("Item with code '%s' not found.", code)));

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("/items")
    public ResponseEntity<List<Item>> deleteAllItems() {

        csvService.deleteAll();

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
