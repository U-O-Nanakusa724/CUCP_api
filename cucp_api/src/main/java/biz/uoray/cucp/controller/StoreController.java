package biz.uoray.cucp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import biz.uoray.cucp.request.RequestStore;
import biz.uoray.cucp.response.ResponseStore;
import biz.uoray.cucp.response.ResponseStoreList;
import biz.uoray.cucp.service.StoreService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@Controller
@RequestMapping("/v1/stores")
public class StoreController {

	@Autowired
	StoreService storeService;

	/**
	 * 販売店一覧表示
	 *
	 * @param model
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "販売店一覧を取得する", nickname = "getStores")
	@GetMapping(produces = "application/json")
	public ResponseStoreList getStore() throws Exception {
		Pageable pageable = PageRequest.of(0, 20);
		return new ResponseStoreList(storeService.getAll(pageable));
	}

	/**
	 * 販売店新規登録
	 *
	 * @param requestStrore
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@ApiOperation(value = "販売店を登録する", nickname = "createStores")
	@PostMapping("/create")
	public ResponseStore postStore(@RequestBody RequestStore requestStrore) throws Exception {
		return new ResponseStore(storeService.createStore(requestStrore));
	}

	/**
	 * 販売店編集
	 *
	 * @param requestStrore
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@ApiOperation(value = "販売店を編集する", nickname = "updateStores")
	@PutMapping("/update")
	public ResponseStore putStore(@Validated @RequestBody RequestStore requestStrore) throws Exception {
		return new ResponseStore(storeService.updateStore(requestStrore));
	}

	/**
	 * 販売店1件削除
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@ApiOperation(value = "販売店を削除する", nickname = "deleteStores")
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<Void> deleteStore(@PathVariable("id") Integer id) throws Exception {
		storeService.deleteStore(id);
		return ResponseEntity.ok().build();
	}

	/**
	 * 販売店検索
	 *
	 * @param select
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@ApiOperation(value = "販売店を検索する", nickname = "searchStores")
	@GetMapping("/search")
	public ResponseStoreList searchStore(@RequestParam("select") String select,
			                             @RequestParam("keyword") String keyword) throws Exception {
		Pageable pageable = PageRequest.of(0, 20);
		return new ResponseStoreList(storeService.searchStore(pageable, select, keyword));
	}
}
