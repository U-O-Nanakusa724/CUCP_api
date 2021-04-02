package biz.uoray.cucp.service;

import biz.uoray.cucp.entity.Car;
import biz.uoray.cucp.entity.Grade;
import biz.uoray.cucp.exception.CucpNotFoundException;
import biz.uoray.cucp.repository.CarRepository;
import biz.uoray.cucp.repository.GradeRepository;
import biz.uoray.cucp.request.RequestGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GradeService {

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    CarRepository carRepository;

    /**
     * 有効なグレード一覧を取得する
     *
     * @param pageable ページング
     * @return グレードリスト(ページング付)
     */
    public Page<Grade> getAll(Pageable pageable) {
        return gradeRepository.findActive(pageable);
    }

    /**
     * グレードを１件登録する
     *
     * @param requestGrade リクエスト
     */
    public Grade createGrade(RequestGrade requestGrade) {
        Car car = Optional.ofNullable(carRepository.findActiveById(requestGrade.getCarId()))
                .orElseThrow(() -> new CucpNotFoundException("CarNotFound"));
        Grade grade = new Grade();
        grade.setCar(car);
        grade.setGrade(requestGrade.getGrade());
        return gradeRepository.save(grade);
    }

    /**
     * グレードを１件編集する
     *
     * @param requestGrade リクエスト
     */
    public Grade updateGrade(RequestGrade requestGrade) {
        Car car = Optional.ofNullable(carRepository.findActiveById(requestGrade.getCarId()))
                .orElseThrow(() -> new CucpNotFoundException("CarNotFound"));
        Grade grade = Optional.ofNullable(gradeRepository.findActiveById(requestGrade.getGradeId()))
                .orElseThrow(() -> new CucpNotFoundException("GradeNotFound"));
        grade.setCar(car);
        grade.setGrade(requestGrade.getGrade());
        return gradeRepository.save(grade);
    }

    /**
     * 対象のグレードの削除日を設定する
     *
     * @param gradeId グレードID
     */
    public void deleteGrade(Integer gradeId) {
        Grade grade = Optional.ofNullable(gradeRepository.findActiveById(gradeId))
                .orElseThrow(() -> new CucpNotFoundException("GradeNotFound"));
        grade.setDeletedAt(new Date());
        gradeRepository.save(grade);
    }
}
