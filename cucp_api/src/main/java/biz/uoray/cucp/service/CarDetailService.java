package biz.uoray.cucp.service;

import biz.uoray.cucp.entity.*;
import biz.uoray.cucp.exception.CucpNotFoundException;
import biz.uoray.cucp.repository.CarDetailRepository;
import biz.uoray.cucp.repository.GradeRepository;
import biz.uoray.cucp.repository.StoreRepository;
import biz.uoray.cucp.request.RequestCarDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CarDetailService {

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    CarDetailRepository carDetailRepository;

    /**
     * 有効な車種詳細一覧を取得する
     *
     * @return 車種詳細リスト(ページング付)
     */
    public Page<CarDetail> getAll(Pageable pageable) {
        // TODO Entityで対応できればここのロジックは不要
        Page<CarDetail> carDetails = carDetailRepository.findActive(pageable);
        carDetails.forEach(carDetail -> {
            List<Price> priceList = carDetail.getPriceList();
            priceList.removeIf(price -> price.getDeletedAt() != null);
            carDetail.setPriceList(priceList);
        });
        return carDetails;
    }

    /**
     * 車種詳細を１件登録する
     *
     * @param requestCarDetail リクエスト
     */
    @Transactional
    public CarDetail createCarDetail(RequestCarDetail requestCarDetail) {

        // マスタ存在確認
        Grade grade = Optional.ofNullable(gradeRepository.findActiveById(requestCarDetail.getGradeId()))
                .orElseThrow(() -> new CucpNotFoundException("errors.GradeNotFound"));
        Store store = Optional.ofNullable(storeRepository.findActiveById(requestCarDetail.getStoreId()))
                .orElseThrow(() -> new CucpNotFoundException("errors.StoreNotFound"));

        // レコード作成
        CarDetail carDetail = new CarDetail();
        carDetail.setGrade(grade);
        carDetail.setStore(store);
        carDetail.setColor(requestCarDetail.getColor());
        carDetail.setDistance(requestCarDetail.getDistance());
        carDetail.setMission(requestCarDetail.getMission());
        carDetail.setModelYear(requestCarDetail.getModelYear());
        carDetail.setUrl(requestCarDetail.getUrl());
        carDetail.setNote(requestCarDetail.getNote());

        return carDetailRepository.save(carDetail);
    }

    /**
     * 車種詳細を１件更新する
     *
     * @param requestCarDetail リクエスト
     */
    public CarDetail updateCarDetail(RequestCarDetail requestCarDetail) {

        // マスタ存在確認
        Grade grade = Optional.ofNullable(gradeRepository.findActiveById(requestCarDetail.getGradeId()))
                .orElseThrow(() -> new CucpNotFoundException("errors.GradeNotFound"));
        Store store = Optional.ofNullable(storeRepository.findActiveById(requestCarDetail.getStoreId()))
                .orElseThrow(() -> new CucpNotFoundException("errors.StoreNotFound"));

        // 詳細存在確認
        CarDetail carDetail = Optional.ofNullable(carDetailRepository.findActiveById(requestCarDetail.getDetailId()))
                .orElseThrow(() -> new CucpNotFoundException("errors.DetailNotFound"));

        // レコード編集
        carDetail.setGrade(grade);
        carDetail.setStore(store);
        carDetail.setColor(requestCarDetail.getColor());
        carDetail.setDistance(requestCarDetail.getDistance());
        carDetail.setMission(requestCarDetail.getMission());
        carDetail.setModelYear(requestCarDetail.getModelYear());
        carDetail.setUrl(requestCarDetail.getUrl());
        carDetail.setNote(requestCarDetail.getNote());

        return carDetailRepository.save(carDetail);
    }

    /**
     * 車種詳細を１件削除する
     *
     * @param detailId 車種詳細ID
     */
    public void deleteCarDetail(Integer detailId) {
        CarDetail carDetail = Optional.ofNullable(carDetailRepository.findActiveById(detailId))
                .orElseThrow(() -> new CucpNotFoundException("errors.DetailNotFound"));
        carDetail.setDeletedAt(new Date());
        carDetailRepository.save(carDetail);
    }

    /**
     * 指定された車種詳細に成約フラグを立てる
     *
     * @param detailId 車種詳細ID
     */
    public CarDetail updateSoldFlag(Integer detailId) {
        CarDetail carDetail = Optional.ofNullable(carDetailRepository.findActiveById(detailId))
                .orElseThrow(() -> new CucpNotFoundException("errors.DetailNotFound"));
        carDetail.setSoldFlag(true);
        return carDetailRepository.save(carDetail);
    }
}
