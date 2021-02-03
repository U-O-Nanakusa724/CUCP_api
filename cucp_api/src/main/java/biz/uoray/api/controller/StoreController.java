package biz.uoray.api.controller;

import biz.uoray.api.form.StoreForm;
import biz.uoray.api.service.StoreService;
import biz.uoray.common.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping()
    public String getStore(Model model, Pageable pageable) {
        Page<Store> storeEntityPage = storeService.getAll(pageable);
        model.addAttribute("pages", storeEntityPage);
        model.addAttribute("storeList", storeEntityPage.getContent());
        model.addAttribute("url", "/stores");
        return "store/index";
    }

    /**
     * 販売店新規登録画面表示
     *
     * @return
     */
    @GetMapping("/new")
    public String createStore(StoreForm storeForm) {
        return "store/new";
    }

    /**
     * 販売店新規登録実行
     *
     * @param storeForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public String insertStore(@Validated StoreForm storeForm,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "store/new";
        }

        storeService.createStore(storeForm.getName());
        return "redirect:/stores";
    }

    /**
     * 販売店編集画面表示
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/{id}/edit")
    public String editStore(Model model,
                            StoreForm storeForm,
                            @PathVariable("id") Integer id) {
        Store store = storeService.getActiveStoreById(id);
        storeForm.setName(store.getName());
        model.addAttribute("storeId", store.getId());
        model.addAttribute("store", storeForm);

        return "store/edit";
    }

    /**
     * 販売店編集実行
     *
     * @param id
     * @param storeForm
     * @return
     */
    @PutMapping("/{id}/update")
    public String updateStore(Model model,
                              @PathVariable("id") Integer id,
                              @Validated StoreForm storeForm,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("storeId", id);
            model.addAttribute("store", storeForm);
            return "store/edit";
        }

        storeService.updateStore(id, storeForm.getName());
        return "redirect:/stores";
    }

    /**
     * 販売店１件削除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}/delete")
    public String deleteStore(@PathVariable("id") Integer id) {

        storeService.deleteStore(id);
        return "redirect:/stores";
    }
}
