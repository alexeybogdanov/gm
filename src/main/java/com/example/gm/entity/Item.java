package com.example.gm.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @CsvBindByName
    private String code;

    @Column
    @CsvBindByName
    private String source;

    @Column
    @CsvBindByName
    private String codeListCode;

    @Column
    @CsvBindByName
    private String displayValue;

    @Column
    @CsvBindByName
    private String longDescription;

    @Column
    @CsvBindByName
    private String fromDate;

    @Column
    @CsvBindByName
    private String toDate;

    @Column
    @CsvBindByName
    private Integer sortingPriority;
}
