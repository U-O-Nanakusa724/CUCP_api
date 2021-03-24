package biz.uoray.cucp.dto;

import biz.uoray.cucp.constant.PointStyle;
import biz.uoray.cucp.entity.CarDetail;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DatasetDto {

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
