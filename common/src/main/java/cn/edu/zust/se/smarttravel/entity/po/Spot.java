package cn.edu.zust.se.smarttravel.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

@Data
@TableName(value = "spot",autoResultMap = true)
@AllArgsConstructor
@NoArgsConstructor
public class Spot {

    @Serial
    private static final long serialVersionUID = 1L;

    //主键
    @TableId
    private Long id;

    //景点名称
    @TableField("spotname")
    private String spotName;

    //经度
    @TableField("lat")
    private Double lat;

    //维度
    @TableField("lon")
    private Double lon;

    //滞留时间,单位分钟
    @TableField("residencetime")
    private int residenceTime;

    //花费经费
    @TableField("funds")
    private int funds;

    public Spot(String start, int i) {
    }

    public Spot(String spotName){
        this.spotName = spotName;
    }
}
