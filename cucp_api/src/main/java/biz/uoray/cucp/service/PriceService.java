package biz.uoray.cucp.service;

import biz.uoray.cucp.entity.CarDetail;
import biz.uoray.cucp.entity.Price;
import biz.uoray.cucp.exception.CucpNotFoundException;
import biz.uoray.cucp.repository.CarDetailRepository;
import biz.uoray.cucp.repository.PriceRepository;
import biz.uoray.cucp.request.RequestPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PriceService {

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    CarDetailRepository carDetailRepository;

    /**
     * 価格販売情報を1件登録する
     *
     * @param requestPrice リクエスト
     */
    public Price createPrice(RequestPrice requestPrice) {
        CarDetail carDetail = Optional.ofNullable(carDetailRepository.findActiveById(requestPrice.getDetailId()))
                .orElseThrow(() -> new CucpNotFoundException("errors.DetailNotFound"));
        Price price = new Price();
        price.setCarDetail(carDetail);
        price.setPrice(requestPrice.getPrice());
        price.setDate(requestPrice.getDate());
        return priceRepository.save(price);
    }

    /**
     * 価格販売情報を1件更新する
     *
     * @param requestPrice リクエスト
     */
    public Price updatePrice(RequestPrice requestPrice) {
        Price price = Optional.ofNullable(priceRepository.findActiveById(requestPrice.getPriceId()))
                .orElseThrow(() -> new CucpNotFoundException("errors.PriceNotFound"));
        price.setPrice(requestPrice.getPrice());
        price.setDate(requestPrice.getDate());
        return priceRepository.save(price);
    }

    /**
     * 対象の価格情報の削除日を設定する
     *
     * @param priceId 価格ID
     */
    public void deletePrice(Integer priceId) {
        Price price = Optional.ofNullable(priceRepository.findActiveById(priceId))
                .orElseThrow(() -> new CucpNotFoundException("errors.PriceNotFound"));
        price.setDeletedAt(new Date());
        priceRepository.save(price);
    }
}
