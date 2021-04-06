package biz.uoray.cucp.service;

import biz.uoray.cucp.entity.Color;
import biz.uoray.cucp.exception.CucpNotFoundException;
import biz.uoray.cucp.repository.ColorRepository;
import biz.uoray.cucp.request.RequestColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ColorService {

    @Autowired
    ColorRepository colorRepository;

    /**
     * 有効なカラーコード一覧を取得する
     *
     * @param pageable ページング
     * @return カラーコードリスト(ページング付)
     */
    public Page<Color> getAll(Pageable pageable) {
        return colorRepository.getActive(pageable);
    }

    /**
     * カラーコードを１件登録する
     *
     * @param requestColor リクエスト
     */
    public Color createColor(RequestColor requestColor) {
        Color color = new Color();
        color.setLabel(requestColor.getLabel());
        color.setColorCode(requestColor.getColorCode());
        return colorRepository.save(color);
    }

    /**
     * カラーコードを１件更新する
     *
     * @param requestColor リクエスト
     */
    public Color updateColor(RequestColor requestColor) {
        Color color = Optional.ofNullable(colorRepository.findActiveById(requestColor.getColorId()))
                .orElseThrow(() -> new CucpNotFoundException("errors.ColorNotFound"));
        color.setLabel(requestColor.getLabel());
        color.setColorCode(requestColor.getColorCode());
        return colorRepository.save(color);
    }

    /**
     * 対象のカラーコードの削除日を設定する
     *
     * @param colorId カラーコードID
     */
    public Color deleteColor(Integer colorId) {
        Color color = Optional.ofNullable(colorRepository.findActiveById(colorId))
                .orElseThrow(() -> new CucpNotFoundException("errors.ColorNotFound"));
        color.setDeletedAt(new Date());
        return colorRepository.save(color);
    }
}
