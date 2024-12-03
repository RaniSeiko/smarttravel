package cn.edu.zust.se.smarttravel.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user",autoResultMap = true)
public class
User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    //主键
    private Long id;

    //用户名
    private String username;

    //手机号
    private String account;

    //密码
    private String password;

    //性别
    private String gender;

    //消费系数，与行程经费计算相关
    private double budget;

    //旅游模式
    private double mode;

    //旅游节奏，行动系数，与行程耗时计算相关
    private double rhythm;

    //完成行程次数
    private int count;
}

