package biz.uoray.cucp.service;

import biz.uoray.cucp.entity.CarDetail;
import biz.uoray.cucp.repository.CarDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CarDetailService {

    @Autowired
    CarDetailRepository carDetailRepository;

    /**
     * 有効な車種一覧を取得する
     *
     * @return
     */
    public Page<CarDetail> getAll(Pageable pageable) {
        return carDetailRepository.findAll(pageable);
    }

}
