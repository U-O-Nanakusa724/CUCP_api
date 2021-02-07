//package biz.uoray.api.service;
//
//import biz.uoray.common.entity.Store;
//import biz.uoray.common.repository.StoreRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class StoreService {
//
//    @Autowired
//    StoreRepository storeRepository;
//
//    /**
//     * 有効な販売店一覧を取得する
//     *
//     * @return
//     */
//    public List<Store> getActiveStoreList() {
//
//        return storeRepository.findAll().stream()
//                .filter(storeEntity -> storeEntity.getDeletedAt() == null)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * 有効な販売店を１件IDで取得する
//     *
//     * @param id
//     * @return
//     */
//    public Store getActiveStoreById(Integer id) {
//        return storeRepository.getOne(id);
//    }
//
//    /**
//     * 販売店を１件登録する
//     *
//     * @param name
//     */
//    public void createStore(String name) {
//        storeRepository.save(new Store(name));
//    }
//
//    /**
//     * 販売店を１件更新する
//     *
//     * @param id
//     * @param name
//     */
//    public void updateStore(Integer id, String name) {
//        Store store = storeRepository.getOne(id);
//        if (store != null) {
//            store.setName(name);
//            storeRepository.save(store);
//        }
//    }
//
//    /**
//     * 対象の販売店の削除日を設定する
//     *
//     * @param id
//     */
//    public void deleteStore(Integer id) {
//        Store store = storeRepository.getOne(id);
//        if (store != null) {
//            store.setDeletedAt(new Date());
//            storeRepository.save(store);
//        }
//    }
//
//    /**
//     * 販売店名から対象の販売店ID取得
//     *
//     * @param name
//     * @return
//     */
//    public int getStoreIdByName(String name) {
//        return storeRepository.findByName(name).getId();
//    }
//
//    /**
//     * ページング用の店舗一覧を取得する
//     *
//     * @param pageable
//     * @return
//     */
//    public Page<Store> getAll(Pageable pageable) {
//        return storeRepository.findAll(pageable);
//    }
//}
