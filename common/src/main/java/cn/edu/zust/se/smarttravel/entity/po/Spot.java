package cn.edu.zust.se.smarttravel.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName(value = "spot",autoResultMap = true)
@AllArgsConstructor
@NoArgsConstructor
public class Spot {
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

    public Spot(String start, int i) {
    }

    public Spot(String spotName){
        this.spotName = spotName;
    }
}
