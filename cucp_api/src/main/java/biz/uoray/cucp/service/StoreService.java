package biz.uoray.cucp.service;

import java.util.Date;
import java.util.Optional;

import biz.uoray.cucp.exception.CucpNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import biz.uoray.cucp.entity.Store;
import biz.uoray.cucp.repository.StoreRepository;
import biz.uoray.cucp.request.RequestStore;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    /**
     * 有効な販売店一覧を取得する
     *
     * @param pageable ページング
     * @return 販売店リスト(ページング付)
     */
    public Page<Store> getAll(Pageable pageable) {
        return storeRepository.findActive(pageable);
    }

    /**
     * 販売店を１台登録する
     *
     * @param requestStore リクエスト
     */
    public Store createStore(RequestStore requestStore) {
        Store store = new Store();
        store.setName(requestStore.getName());
        return storeRepository.save(store);
    }

    /**
     * 販売店を１件更新する
     *
     * @param requestStore リクエスト
     */
    public Store updateStore(RequestStore requestStore) {
        Store store = Optional.ofNullable(storeRepository.findActiveById(requestStore.getStoreId()))
                .orElseThrow(() -> new CucpNotFoundException("errors.StoreNotFound"));
        store.setName(requestStore.getName());
        return storeRepository.save(store);
    }

    /**
     * 対象の販売店の削除日を設定する
     *
     * @param storeId 販売店ID
     */
    public void deleteStore(Integer storeId) {
        Store store = Optional.ofNullable(storeRepository.findActiveById(storeId))
                .orElseThrow(() -> new CucpNotFoundException("errors.StoreNotFound"));
        store.setDeletedAt(new Date());
        storeRepository.save(store);
    }

    /**
     * キーワードを用いて検索した結果をページングで返す
     *
     * @param pageable ページング
     * @param keyword  検索ワード
     */
    public Page<Store> searchStore(Pageable pageable, String keyword) {
        return storeRepository.searchByName(pageable, keyword);
    }
}
