package biz.uoray.cucp.dto;

import biz.uoray.cucp.entity.*;
import lombok.Data;

import java.util.List;

@Data
public class CsvResultDto {

    List<CsvDataDto> csvDataDtoList;
    List<Car> newCarList;
    List<Grade> newGradeList;
    List<Store> newStoreList;
    List<Color> newColorList;
    List<CarDetail> newCarDetailList;
}
