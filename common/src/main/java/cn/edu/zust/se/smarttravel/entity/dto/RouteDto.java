package cn.edu.zust.se.smarttravel.entity.dto;

import lombok.Data;

@Data
public class RouteDto {
    //主键
    private Long id;

    //所属用户
    private Long userId;

    //起点
    private  String start;
}
