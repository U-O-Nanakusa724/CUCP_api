package biz.uoray.cucp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CsvDataDto {

    private String carName;
    private String grade;
    private String mission;
    private String colorLabel;
    private double price;
    private LocalDate modelYear;
    private String distance;
    private String storeName;
    private String note;
    private String url;
}
