package biz.uoray.cucp.service;

import biz.uoray.cucp.entity.CarDetail;
import biz.uoray.cucp.entity.Price;
import biz.uoray.cucp.repository.CarDetailRepository;
import biz.uoray.cucp.repository.PriceRepository;
import biz.uoray.cucp.request.RequestPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PriceService {

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    CarDetailRepository carDetailRepository;

    /**
     * 価格販売情報を1件登録する
     *
     * @param
     */
    public Price createPrice(RequestPrice requestPrice) {
        CarDetail carDetail = carDetailRepository.getOne(requestPrice.getRequestCarDetail().getId());
        return priceRepository.save(new Price(carDetail, requestPrice.getPrice(), requestPrice.getDate()));
    }

    /**
     * 価格販売情報を1件更新する
     *
     * @param
     */
    public Price updatePrice(RequestPrice requestPrice) {
        Price price = priceRepository.getOne(requestPrice.getId());
        if (price != null) {
            price.setPrice(requestPrice.getPrice());
            price.setDate(requestPrice.getDate());
        }
        return priceRepository.save(price);
    }

    /**
     * 対象の価格情報の削除日を設定する
     *
     * @param priceId
     */
    public void deletePrice(Integer priceId) {
        Price price = priceRepository.getOne(priceId);
        if (price != null) {
            price.setDeletedAt(new Date());
            priceRepository.save(price);
        }
    }
}
