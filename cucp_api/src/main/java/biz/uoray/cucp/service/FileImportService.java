package biz.uoray.cucp.service;

import biz.uoray.cucp.constant.Constants;
import biz.uoray.cucp.dto.CsvDataDto;
import biz.uoray.cucp.entity.Car;
import biz.uoray.cucp.entity.CarDetail;
import biz.uoray.cucp.entity.Grade;
import biz.uoray.cucp.entity.Store;
import biz.uoray.cucp.exception.CucpSystemException;
import biz.uoray.cucp.repository.CarRepository;
import biz.uoray.cucp.repository.ColorRepository;
import biz.uoray.cucp.repository.GradeRepository;
import biz.uoray.cucp.repository.StoreRepository;
import biz.uoray.cucp.response.ResponseReadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FileImportService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    ColorRepository colorRepository;

    @Transactional
    public ResponseReadResult readCsvAndReturnDataList(MultipartFile file) {

        List<CsvDataDto> csvDataDtoList = createDto(file);

        List<Car> newCarList = createNewCars(csvDataDtoList);
        List<Grade> newGradeList = createNewGrades(csvDataDtoList);
        List<Store> newStoreList = createNewStores(csvDataDtoList);
        List<CarDetail> newCarDetailList = createNewDetails(csvDataDtoList);

        return new ResponseReadResult(newCarList, newGradeList, newStoreList, newCarDetailList);
    }

    /**
     * CSVを読み込みDTOに格納する
     *
     * @param file CSVファイル
     * @return DTOリスト
     */
    @Transactional
    private List<CsvDataDto> createDto(MultipartFile file) {
        // CSVを読み込み、DTOにセットする
        final int COLUMN_COUNT = 9;
        List<CsvDataDto> csvDataDtoList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                // カンマ分割
                final String[] split = line.split(",");

                // １行目だけ除外
                if (split[0].equals("car_name")) {
                    continue;
                }

                CsvDataDto csvDataDto = new CsvDataDto();

                for (int index = 0; index < COLUMN_COUNT; index++) {
                    // indexから操作対象を決定
                    Columns column = Columns.getByIndex(index);
                    switch (column) {
                        case CAR:
                            csvDataDto.setCarName(split[index]);
                            break;
                        case MISSION:
                            csvDataDto.setMission(split[index]);
                            break;
                        case COLOR:
                            csvDataDto.setColorLabel(split[index]);
                            break;
                        case PRICE:
                            csvDataDto.setPrice(Double.parseDouble(split[index]));
                            break;
                        case MODEL_YEAR:
                            String localDateString = String.format("%s/01/01", split[index]);
                            LocalDate localDate = LocalDate.parse(localDateString,
                                    DateTimeFormatter.ofPattern(Constants.SIMPLE_DATE_FORMAT));
                            csvDataDto.setModelYear(localDate);
                            break;
                        case DISTANCE:
                            csvDataDto.setDistance(split[index]);
                            break;
                        case STORE_NAME:
                            csvDataDto.setStoreName(split[index]);
                            break;
                        case NOTE:
                            // TODO note項目からgrade切り出しOK,他のデータは現在破棄
                            String[] grade = split[index].split(" ");
                            csvDataDto.setGrade(String.format("%s %s",grade[0],grade[1]));
                            break;
                        case URL:
                            csvDataDto.setUrl(split[index]);
                            break;
                        case NOT_MATCHED:
                        default:
                            break;
                    }
                }
                csvDataDtoList.add(csvDataDto);
            }
            return csvDataDtoList;
        } catch (IOException e) {
            throw new RuntimeException("ファイルが読み込めません", e);
        }
    }

    /**
     * DTOの車種名から既存チェック、なければ新規登録し追加リストに加える
     *
     * @param csvDtoList 読み込んだCSVリスト
     * @return 新規登録車種リスト
     */
    @Transactional
    private List<Car> createNewCars(List<CsvDataDto> csvDtoList) {
        // 受け取ったList<DTO>に対し、車種の既存チェック、なかった場合は新規に追加して新規リストに格納
        List<Car> newCarList = new ArrayList<>();

        csvDtoList.forEach(csvDataDto -> {
            Car car = carRepository.findActiveByName(csvDataDto.getCarName()).orElse(null);

            if (car == null) {
                Car newCar = new Car();
                newCar.setName(csvDataDto.getCarName());
                newCarList.add(carRepository.save(newCar));
            }
        });
        return newCarList;
    }

    @Transactional
    private List<Grade> createNewGrades(List<CsvDataDto> csvDtoList) {
        // 受け取ったList<DTO>に対し、グレードの既存チェック、なかった場合は新規に追加して新規リストに格納
        List<Grade> newGradeList = new ArrayList<>();

        csvDtoList.forEach(csvDataDto -> {
            Grade grade = gradeRepository.findActiveByGradeAndCarName(csvDataDto.getGrade(), csvDataDto.getCarName()).orElse(null);

            if (grade == null) {
                Grade newGrade = new Grade();
                Car car = carRepository.findActiveByName(csvDataDto.getCarName()).orElseThrow(() -> new CucpSystemException(""));
                newGrade.setCar(car);
                newGrade.setGrade(csvDataDto.getGrade());
                newGradeList.add(gradeRepository.save(newGrade));
            }
        });
        return newGradeList;
    }

    /**
     * DTOの販売店名から既存チェック、なければ新規登録し追加リストに加える
     *
     * @param csvDtoList 読み込んだCSVリスト
     * @return 新規登録販売店リスト
     */
    @Transactional
    private List<Store> createNewStores(List<CsvDataDto> csvDtoList) {
        // 受け取ったList<DTO>に対し、車種の既存チェック、なかった場合は新規に追加して新規リストに格納
        List<Store> newStoreList = new ArrayList<>();

        csvDtoList.forEach(csvDataDto -> {
            Store store = storeRepository.findActiveByName(csvDataDto.getStoreName()).orElse(null);

            if (store == null) {
                Store newStore = new Store();
                newStore.setName(csvDataDto.getStoreName());
                newStoreList.add(storeRepository.save(newStore));
            }
        });
        return newStoreList;
    }

    @Transactional
    private List<CarDetail> createNewDetails(List<CsvDataDto> csvDtoList) {

        List<CarDetail> newCarDetailList = new ArrayList<>();
        // TODO 未着手
        // 既存チェック(グレード、年式、距離、色の４つをWHEREに入れて取得)を行い、
        // A. CarDetailが存在する(Nullじゃなかった)→それを用いてPriceエンティティを作成、登録(価格だけ登録)
        // B. 存在しない→CarDetailとPriceを作成
        // C. ２件以上検索に引っかかった場合→どちらの車種に取り込むか選択できるようにする
        // それをリストに入れてコントローラに返す
        return newCarDetailList;
    }
//
//    createCarDetail(Request request) {
//        // フロントで確認・手直しされたデータを元にCarDetail作成しsave、
//        // そのCarDetailを元にPriceをsaveという流れ
//    }

    /**
     * CSVの並び順を管理するEnum.CSVの順番が変わったらここを変更する.
     */
    private enum Columns {

        CAR(0),
        MISSION(1),
        COLOR(2),
        PRICE(3),
        MODEL_YEAR(4),
        DISTANCE(5),
        STORE_NAME(6),
        NOTE(7),
        URL(8),
        NOT_MATCHED(9999);

        private final int index;

        Columns(int index) {
            this.index = index;
        }

        int getIndex() {
            return this.index;
        }

        public static Columns getByIndex(int index) {
            return Arrays.stream(Columns.values())
                    .filter(column -> column.getIndex() == index)
                    .findFirst()
                    .orElse(NOT_MATCHED);
        }
    }
}
