package cn.edu.zust.se.smarttravel.service.Impl;

import cn.edu.zust.se.smarttravel.entity.R;
import cn.edu.zust.se.smarttravel.entity.po.Spot;
import cn.edu.zust.se.smarttravel.mapper.SpotMapper;
import cn.edu.zust.se.smarttravel.service.SpotService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SpotServiceImpl extends ServiceImpl<SpotMapper, Spot> implements SpotService {

    @Override
    public R<Spot> addSpot(Spot spot) {
        spot.setFunds(0);
        spot.setResidenceTime(30);
        if (spot.getSpotName() == null) {
            return R.error("景点未命名");
        }
        this.save(spot);
        return R.success("新增景点成功", this.getById(spot.getId()));
    }

    @Override
    public R<Spot> updateSpot(Spot spot) {
        spot.setId(this.getOne(new LambdaQueryWrapper<Spot>()
                .eq(Spot::getSpotName, spot.getSpotName())).getId());
        return this.updateById(spot)?R.success("信息更新成功",this.getById(spot.getId())):R.error("更新失败");
    }

    @Override
    public R<Spot> getSpotById(Long id) {
        Spot spot= this.getById(id);
        return spot==null?R.error("景点不存在"):R.success("成功获取景点信息",spot);
    }

    @Override
    public List<Spot> getAll() {
        return this.list();
    }


}