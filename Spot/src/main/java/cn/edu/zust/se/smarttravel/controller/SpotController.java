package cn.edu.zust.se.smarttravel.controller;

import cn.edu.zust.se.smarttravel.entity.R;
import cn.edu.zust.se.smarttravel.entity.po.Spot;
import cn.edu.zust.se.smarttravel.service.SpotService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/spot")
public class SpotController {

    @Resource
    private SpotService spotService;

    /**
     * 新增景点
     *
     * @param spot 景点信息
     * @return R<Spot>
     */
    @PostMapping("/spot")
    public R<Spot> save(@RequestBody Spot spot) {
        log.info("新增景点:{}", spot);
        return spotService.addSpot(spot);
    }

    /**
     * 修改景点信息
     *
     * @param spot 景点信息
     * @return R<Spot> 修改后景点信息
     */
    @PostMapping("/update")
    @Transactional
    public R<Spot> update(@RequestBody Spot spot) {
        log.info("修改景点:{}",spot);
        return spotService.updateSpot(spot);
    }



    /*查询景点
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String spotname){
        //构造分页构造器
        Page<Spot> pageInfo=new Page<>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Spot> queryWrapper =new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(condition:spotname !=null,Spot::getSpotname,spotname);
        //添加排序条件
        queryWrapper.orderByDesc(Spot::getSort);

        //执行分页查询
        spotService.page(pageInfo,queryWrapper);

        return  R.success(spotInfo);
    }*/


    /**
     * 根据id获取景点信息
     *
     * @param id 景点id
     * @return R<Spot>
     */
    @GetMapping("/id")
    @Transactional
    public R<Spot> getSpotById(@RequestParam Long id) {
        log.info("获取id:{}的景点信息",id);
        return spotService.getSpotById(id);
    }

    /**
     * 获取全部景点
     *
     * @return R<List<Spot>>
     */
    @GetMapping("/getAll")
    public R<List<Spot>> getAllSpots(){
        log.info("获取全部景点信息");
        return R.success(spotService.getAll());
    }

    /**
     * 删除景点
     *
     * @param id 景点id
     * @return R<String>
     */
    @DeleteMapping("del")
    @Transactional
    public R<String> delete(@RequestParam Long id) {
        log.info("删除景点，id:{}", id);
        return  spotService.removeById(id)?R.success("景点删除成功"):R.error("删除失败");
    }
}