//package biz.uoray.api.service;
//
//import biz.uoray.api.form.PriceForm;
//import biz.uoray.common.entity.CarDetail;
//import biz.uoray.common.entity.Price;
//import biz.uoray.common.repository.CarDetailRepository;
//import biz.uoray.common.repository.PriceRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class PriceService {
//
//    @Autowired
//    PriceRepository priceRepository;
//
//    @Autowired
//    CarDetailRepository carDetailRepository;
//
//    /**
//     * 車種販売情報を1件登録
//     *
//     * @param
//     */
//    public void createPrice(PriceForm priceForm) {
//        CarDetail carDetail = carDetailRepository.getOne(priceForm.getPriceId());
//        priceRepository.save(new Price(carDetail, priceForm.getPrice(), priceForm.getDate()));
//    }
//
//    /**
//     * 車種販売情報を1件IDで取得する
//     *
//     * @param
//     */
//    public Price getActivePriceById(int id) {
//        return priceRepository.getOne(id);
//    }
//
//    /**
//     * 車種販売情報を1件更新する
//     *
//     * @param
//     */
//    public void updatePrice(PriceForm priceForm) {
//        Price price = priceRepository.getOne(priceForm.getPriceId());
//        if (price != null) {
//            price.setPrice(priceForm.getPrice());
//            price.setDate(priceForm.getDate());
//            priceRepository.save(price);
//        }
//    }
//
//    /**
//     * 車種販売情報を１件削除する
//     *
//     * @param id
//     */
//    public void deletePrice(int id) {
//        Price price = priceRepository.getOne(id);
//        if (price != null) {
//            price.setDeletedAt(new Date());
//            priceRepository.save(price);
//        }
//    }
//}
