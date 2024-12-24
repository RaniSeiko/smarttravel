package cn.edu.zust.se.smarttravel.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDateTime;

@Data
@TableName(value = "route",autoResultMap = true)
public class Route {

    @Serial
    private static final long serialVersionUID = 1L;

    //主键
    @TableId
    private Long id;

    //所属用户
    @TableField("userid")
    private Long userId;

    //起点
    @TableField("start")
    private  String start;

    //途径点
    @TableField("pathway1")
    private String pathway1;
    @TableField("pathway2")
    private String pathway2;
    @TableField("pathway3")
    private String pathway3;
    @TableField("pathway4")
    private String pathway4;
    @TableField("pathway5")
    private String pathway5;
    @TableField("pathway6")
    private String pathway6;
    @TableField("pathway7")
    private String pathway7;
    @TableField("pathway8")
    private String pathway8;

    //终点
    @TableField("end")
    private String end;

    //最佳路线
    @TableField("bestway")
    private String bestWay;

    //旅行开始时间
    @TableField(value = "starttime",fill =  FieldFill.INSERT)
    private LocalDateTime startTime;
    //预计结束时间
    @TableField(value = "expecttime", fill = FieldFill.INSERT)
    private LocalDateTime expectEndTime;

    //实际结束时间
    @TableField(value = "actualentime", fill = FieldFill.INSERT)
    private LocalDateTime actualEndTime;

    //时间差值
    @TableField("deftime")
    private double defTime;

    //预期经费
    @TableField("expectedfunding")
    private double expectFunding;

    //实际经费
    @TableField("actualfunding")
    private double actualFunding;

    //经费差值
    @TableField("deffunding")
    private double defFunding;

    //状态
    private int state;
}
