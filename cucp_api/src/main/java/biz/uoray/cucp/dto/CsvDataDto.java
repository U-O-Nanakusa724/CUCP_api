package biz.uoray.cucp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CsvDataDto {

    private String carName;
    private String grade;
    private String mission;
    private String colorLabel;
    private Double price;
    private Date modelYear;
    private String distance;
    private String storeName;
    private String note;
    private String url;
}
