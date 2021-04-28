package biz.uoray.cucp.service;

import biz.uoray.cucp.constant.Constants;
import biz.uoray.cucp.dto.CsvDataDto;
import biz.uoray.cucp.dto.CsvResultDto;
import biz.uoray.cucp.entity.*;
import biz.uoray.cucp.exception.CucpBadRequestException;
import biz.uoray.cucp.exception.CucpSystemException;
import biz.uoray.cucp.repository.*;
import biz.uoray.cucp.request.RequestCsvResult;
import biz.uoray.cucp.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

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

    @Autowired
    CarDetailRepository carDetailRepository;

    @Autowired
    PriceRepository priceRepository;

    /**
     * 各マスタデータを先に保存する.
     *
     * @param file CSV
     * @return 読み込んで生成したDTOと各マスタリスト
     */
    @Transactional
    public CsvResultDto readCsvAndReturnDataList(MultipartFile file) {

        // 読み込んだ結果を格納するリスト
        CsvResultDto csvResultDto = new CsvResultDto();

        // CSVをテータ化したDTOリスト
        List<CsvDataDto> csvDataDtoList = createDto(file);

        csvResultDto.setCsvDataDtoList(csvDataDtoList);
        csvResultDto.setNewCarList(createNewCars(csvDataDtoList));
        csvResultDto.setNewGradeList(createNewGrades(csvDataDtoList));
        csvResultDto.setNewStoreList(createNewStores(csvDataDtoList));
        csvResultDto.setNewColorList(createNewColors(csvDataDtoList));

        return csvResultDto;
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
                            csvDataDto.setColorLabel(split[index].trim());
                            break;
                        case PRICE:
                            csvDataDto.setPrice(Double.parseDouble(split[index]));
                            break;
                        case MODEL_YEAR:
                            String localDateString = String.format("%s/01/01 00:00:00", split[index]);
                            LocalDateTime localDateTime = LocalDateTime.parse(localDateString,
                                    DateTimeFormatter.ofPattern(Constants.SIMPLE_DATETIME_FORMAT));
                            ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, Constants.JST_ZONE_ID);
                            csvDataDto.setModelYear(Date.from(zonedDateTime.toInstant()));
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
                            if (grade.length > 1) {
                                csvDataDto.setGrade(String.format("%s %s", grade[0], grade[1]));
                            } else {
                                csvDataDto.setGrade(grade[0]);
                            }
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

    /**
     * DTOのグレード名から既存チェック、なければ新規登録し追加リストに加える
     *
     * @param csvDtoList 読み込んだCSVリスト
     * @return 新規登録グレードリスト
     */
    @Transactional
    private List<Grade> createNewGrades(List<CsvDataDto> csvDtoList) {
        // 受け取ったList<DTO>に対し、グレードの既存チェック、なかった場合は新規に追加して新規リストに格納
        List<Grade> newGradeList = new ArrayList<>();

        csvDtoList.forEach(csvDataDto -> {
            Grade grade = gradeRepository.findActiveByGradeAndCarName(csvDataDto.getGrade(), csvDataDto.getCarName()).orElse(null);

            if (grade == null) {
                grade = new Grade();
                // システムの処理順番的にここで車種が見つからないのはシステムエラー
                Car car = carRepository.findActiveByName(csvDataDto.getCarName())
                        .orElseThrow(() -> new CucpSystemException(""));
                grade.setCar(car);
                grade.setGrade(csvDataDto.getGrade());
                newGradeList.add(gradeRepository.save(grade));
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
        // 受け取ったList<DTO>に対し、販売店の既存チェック、なかった場合は新規に追加して新規リストに格納
        List<Store> newStoreList = new ArrayList<>();

        csvDtoList.forEach(csvDataDto -> {
            Store store = storeRepository.findActiveByName(csvDataDto.getStoreName()).orElse(null);

            if (store == null) {
                store = new Store();
                store.setName(csvDataDto.getStoreName());
                newStoreList.add(storeRepository.save(store));
            }
        });
        return newStoreList;
    }

    /**
     * DTOの色名から既存チェック、なければ新規登録し追加リストに加える
     *
     * @param csvDtoList 読み込んだCSVリスト
     * @return 新規登録販売店リスト
     */
    @Transactional
    private List<Color> createNewColors(List<CsvDataDto> csvDtoList) {
        // 受け取ったList<DTO>に対し、色の既存チェック、なかった場合は新規に追加して新規リストに格納
        List<Color> newColorList = new ArrayList<>();

        csvDtoList.forEach(csvDataDto -> {
            Color color = colorRepository.findActiveByLabel(csvDataDto.getColorLabel()).orElse(null);

            if (color == null) {
                color = new Color();
                color.setLabel(csvDataDto.getColorLabel());
                newColorList.add(colorRepository.save(color));
            }
        });
        return newColorList;
    }

    public CsvResultDto createNewDetails(CsvResultDto csvResultDto) {

        List<CarDetail> newCarDetailList = new ArrayList<>();
        csvResultDto.getCsvDataDtoList().forEach(csvDataDto -> {
            // 各マスタ取得、この時点でマスタがない場合システム的におかしいのでエラーをスロー
            Grade grade = gradeRepository.findActiveByGradeAndCarName(csvDataDto.getGrade(), csvDataDto.getCarName())
                    .orElseThrow(() -> new CucpSystemException("errors.SystemError"));
            Store store = storeRepository.findActiveByName(csvDataDto.getStoreName())
                    .orElseThrow(() -> new CucpSystemException("errors.SystemError"));
            Color color = colorRepository.findActiveByLabel(csvDataDto.getColorLabel())
                    .orElseThrow(() -> new CucpSystemException("errors.SystemError"));

            List<CarDetail> targetDetailList = carDetailRepository.findTarget(grade, store, color, csvDataDto.getModelYear(), csvDataDto.getDistance());
            CarDetail carDetail;
            Price price = new Price();
            if (targetDetailList.size() == 1) {
                // 検索結果が１件だけである場合
                carDetail = targetDetailList.get(0);
                price.setPrice(csvDataDto.getPrice());
                price.setDate(new Date());
                carDetail.getPriceList().add(price);
            } else if (targetDetailList.size() == 0) {
                // 検索結果が０件である場合
                carDetail = new CarDetail();
                carDetail.setGrade(grade);
                carDetail.setStore(store);
                carDetail.setColor(color);
                carDetail.setDistance(Optional.ofNullable(csvDataDto.getDistance()).orElse(null));
                carDetail.setMission(Optional.ofNullable(csvDataDto.getMission()).orElse(null));
                carDetail.setModelYear(Optional.ofNullable(csvDataDto.getModelYear()).orElse(null));
                carDetail.setUrl(Optional.ofNullable(csvDataDto.getUrl()).orElse(null));
                carDetail.setNote(Optional.ofNullable(csvDataDto.getNote()).orElse(null));

                price.setPrice(csvDataDto.getPrice());
                price.setDate(new Date());
                List<Price> priceList = new ArrayList<>();
                priceList.add(price);
                carDetail.setPriceList(priceList);
            } else {
                // ２件以上引っかかった場合はロジックでは判定不可能なので警告
                throw new CucpBadRequestException("errors.csv.DetailIdentification");
            }
            newCarDetailList.add(carDetail);
        });

        csvResultDto.setNewCarDetailList(newCarDetailList);
        return csvResultDto;
    }

    /**
     * 受け取った編集済み車種詳細を登録/更新し、価格データを登録する.
     *
     * @param requestCsvResult 編集済みリクエスト
     * @return 登録成功詳細
     */
    public List<CarDetail> saveNewDetails(RequestCsvResult requestCsvResult) {

        // 全Priceリストを用意
        List<Price> activePriceList = priceRepository.getActiveAll();

        return requestCsvResult.getCarDetails()
                .stream()
                .map(requestCarDetail -> {

                    // 各マスタ取得、この時点でマスタがない場合システム的におかしいのでエラーをスロー
                    Grade grade = Optional.ofNullable(gradeRepository.findActiveById(requestCarDetail.getGradeId()))
                            .orElseThrow(() -> new CucpSystemException("errors.SystemError"));
                    Store store = Optional.ofNullable(storeRepository.findActiveById(requestCarDetail.getStoreId()))
                            .orElseThrow(() -> new CucpSystemException("errors.SystemError"));
                    Color color = Optional.ofNullable(colorRepository.findActiveById(requestCarDetail.getColorId()))
                            .orElseThrow(() -> new CucpSystemException("errors.SystemError"));

                    // Detail、既存のものがあれば取得、無ければ新規作成
                    CarDetail carDetail = Optional.ofNullable(carDetailRepository.findActiveById(requestCarDetail.getDetailId()))
                            .orElse(new CarDetail());

                    carDetail.setId(requestCarDetail.getDetailId() == null ? null : requestCarDetail.getDetailId());
                    carDetail.setGrade(grade);
                    carDetail.setStore(store);
                    carDetail.setColor(color);
                    carDetail.setDistance(requestCarDetail.getDistance());
                    carDetail.setMission(requestCarDetail.getMission());
                    carDetail.setModelYear(requestCarDetail.getModelYear());
                    carDetail.setUrl(requestCarDetail.getUrl());
                    carDetail.setNote(requestCarDetail.getNote());

                    // 保存して新規作成分のDetailのIDを決定
                    carDetail = carDetailRepository.save(carDetail);

                    Price price = activePriceList.stream()
                            .filter(price1 ->
                                    price1.getCarDetail().getId().equals(requestCarDetail.getDetailId())
                                            && DateUtil.convertDateToString(price1.getDate(), Constants.SIMPLE_DATE_FORMAT)
                                            .equals(DateUtil.convertDateToString(requestCarDetail.getLastDate(), Constants.SIMPLE_DATE_FORMAT)))
                            .findFirst()
                            .orElse(new Price());

                    price.setCarDetail(carDetail);
                    price.setPrice(requestCarDetail.getLastPrice());
                    price.setDate(requestCarDetail.getLastDate());
                    priceRepository.save(price);

                    return carDetail;
                })
                .collect(Collectors.toList());
    }

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
