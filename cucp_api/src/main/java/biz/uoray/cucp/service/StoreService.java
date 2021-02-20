package biz.uoray.cucp.service;

import java.util.Date;

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
     * @param pageable
     * @return
     */
    public Page<Store> getAll(Pageable pageable) {
        return storeRepository.findActive(pageable);
    }

    /**
     * 販売店を１台登録する
     *
     * @param requestStore
     * @return
     */
    public Store createStore(RequestStore requestStore) {
    	return storeRepository.save(new Store(requestStore.getName()));
    }

    /**
     * 販売店を１件更新する
     *
     * @param requestStore
     * @return
     */
    public Store updateStore(RequestStore requestStore) {
    	Store store = storeRepository.getOne(requestStore.getId());
    	if(store != null) {
    		store.setName(requestStore.getName());
    	}
    	return storeRepository.save(store);
    }

    /**
     * 対象の販売店の削除日を設定する
     *
     * @param storeId
     */
    public void deleteStore(Integer storeId) {
    	Store store = storeRepository.getOne(storeId);
    	if(store != null) {
    		store.setDeletedAt(new Date());
    		storeRepository.save(store);
    	}
    }

    /**
     * キーワードを用いて検索した結果をページングで返す
     *
     * @param pageable
     * @param select
     * @param keyword
     * @return
     */
    public Page<Store> searchStore(Pageable pageable, String select, String keyword){
    	if(select.equals("name")) {
    		return storeRepository.searchByName(pageable, keyword);
    	}else {
    		return storeRepository.findActive(pageable);
    	}
    }
}
