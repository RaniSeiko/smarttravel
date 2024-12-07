package cn.edu.zust.se.smarttravel.service;

import cn.edu.zust.se.smarttravel.entity.R;
import cn.edu.zust.se.smarttravel.entity.po.Spot;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SpotService  extends IService<Spot> {
    R<Spot> addSpot(Spot spot);

    R<Spot> updateSpot(Spot spot);

    R<Spot> getSpotById(Long id);

    List<Spot> getAll();

}
