//package biz.uoray.api.common.repository.custom;
//
//import biz.uoray.common.entity.Car;
//import biz.uoray.common.entity.CarDetail;
//import biz.uoray.common.entity.Store;
//
//import java.util.List;
//
//public interface CarDetailRepositoryCustom {
//
//    /**
//     * carsの一覧を取得
//     *
//     * @return
//     */
//    public List<Car> findAll();
//
//    /**
//     * car_detailsの一覧を取得
//     *
//     * @param carId
//     * @return
//     */
//    public List<CarDetail> findCarDetailByCarId(int carId);
//
//    /**
//     * 選択されたcar_detail_idのcar_detailsを取得
//     *
//     * @param carDetailId
//     * @return
//     */
//    public CarDetail findCarDetailByCarDetailId(int carDetailId);
//
//    /**
//     * 選択されたcar_idのcarsを取得する
//     *
//     * @param carId
//     * @return
//     */
//    public Car findCarByCarId(int carId);
//
//    /**
//     * storesの一覧を取得
//     *
//     * @return
//     */
//    public List<Store> findAllStore();
//
//    /**
//     * car_detailの新規登録
//     *
//     *
//     */
////	public void save(CarDetailEntity carDetailEntity);
//}
