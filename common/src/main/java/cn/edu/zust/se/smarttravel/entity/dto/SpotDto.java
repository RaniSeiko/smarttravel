package cn.edu.zust.se.smarttravel.entity.dto;

import lombok.Data;

@Data
public class SpotDto {
    //主键
    private Long id;

    //景点名称
    private String spotName;

    //经度
    private Double lat;

    //维度
    private Double lon;

    //滞留时间,单位分钟
    private int residenceTime;

    //花费经费
    private int funds;
}
