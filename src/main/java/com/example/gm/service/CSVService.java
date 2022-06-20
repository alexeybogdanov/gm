package com.example.gm.service;

import com.example.gm.entity.Item;
import com.example.gm.repository.CSVRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

@Service
public class CSVService {

    private final CSVRepository csvRepository;

    public CSVService(CSVRepository csvRepository) {
        this.csvRepository = csvRepository;
    }

    public List<Item> saveAll(MultipartFile file) {
        List<Item> items = csvToItem(file);
        return csvRepository.saveAll(items);
    }

    public List<Item> fetchAll(){
        return csvRepository.findAll();
    }

    public Optional<Item> fetchByCode(String code){
        return csvRepository.findById(code);
    }

    public void deleteAll(){
        csvRepository.deleteAll();
    }

    public static List<Item> csvToItem(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<Item> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Item.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();

        } catch (IOException e) {
            throw new RuntimeException("An error occurred while processing the CSV file: " + e.getMessage());
        }
    }

}