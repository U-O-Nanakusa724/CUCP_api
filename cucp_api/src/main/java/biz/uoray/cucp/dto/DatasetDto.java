package biz.uoray.cucp.dto;

import biz.uoray.cucp.constant.PointStyle;
import biz.uoray.cucp.entity.CarDetail;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DatasetDto {

    public DatasetDto(CarDetail carDetail) {
        this.label = carDetail.getCar().getCode() + " " + carDetail.getStore().getName();
        this.data = new ArrayList<>(carDetail.getPriceList());
        this.borderWidth = 1;
        this.pointRadius = 5;
        this.pointHoverRadius = 20;
        this.pointStyle = PointStyle.CIRCLE.getStyle();
        this.fill = false;
        this.lineTension = 0.0;
    }

    // 凡例名
    private String label;

    // データ
    private List<Object> data;

    // 線の太さ
    private int borderWidth;

    // 点の大きさ
    private int pointRadius;

    // マウスオーバー時の大きさ
    private int pointHoverRadius;

    // 点の形
    private String pointStyle;

    // 折れ線の下部を埋めるかどうか
    private boolean fill;

    // 曲線の湾曲具合
    private double lineTension;

}
