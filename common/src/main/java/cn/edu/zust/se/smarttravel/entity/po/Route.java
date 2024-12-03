package cn.edu.zust.se.smarttravel.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "route",autoResultMap = true)
public class Route {

    //主键
    private Long id;

    //所属用户
    private Long userId;

    //起点
    private  String start;

    //途径点
    private String pathway1;
    private String pathway2;
    private String pathway3;
    private String pathway4;
    private String pathway5;
    private String pathway6;
    private String pathway7;
    private String pathway8;

    //终点
    private String end;

    //最佳路线
    private String bestWay;

    //旅行开始时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime startTime;
    //预计结束时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime expectEndTime;

    //实际结束时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime actualEndTime;

    //时间差值
    private double defTime;

    //预期经费
    private double expectFunding;

    //实际经费
    private double actualFunding;

    //经费差值
    private double defFunding;

    //状态
    private int state;
}
