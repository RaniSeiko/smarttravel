package cn.edu.zust.se.smarttravel.contorller;

import cn.edu.zust.se.smarttravel.entity.R;
import cn.edu.zust.se.smarttravel.entity.po.Route;
import cn.edu.zust.se.smarttravel.service.RouteService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@Transactional
@RestController
@RequestMapping("/route")
public class RouteController {

    @Resource
    private RouteService routeService;
    @PostMapping("/add")
    public R<Route> addRoute(@RequestBody Route route){
        log.info("行程数据:{}",route);
        return routeService.addRoute(route);
    }

    @PostMapping("/save")
    public R<Route> saveRoute(@RequestBody Route route){
        log.info("行程数据:{}",route);
        return routeService.saveRoute(route);
    }

    /**
     * 根据用户id查询用户的全部行程
     *
     * @param userId 用户id
     * @return R<List<Route>>
     */
    @GetMapping("/getAll")
    public R<List<Route>> getAllRotesByUserId(@RequestParam Long userId){
        List<Route> routes= routeService.getAllRoutesByUserId(userId);
        return routes==null?R.error("无进行中的进程"):
                R.success("查询成功",routes);
    }

    /**
     * 结束行程
     *
     * @param routeId 行程id
     * @return R<Route>
     */
    @PostMapping("/over")
    public R<Route> endingTheRoute(@RequestParam Long routeId,@RequestParam (defaultValue = "0") Double funding) {
        log.info("结束行程，id:{}",routeId);
        if(funding-1<0.1){
            funding=routeService.getById(routeId).getExpectFunding();
        }
        Route endRoute= routeService.endingRoute(routeId,funding);
        return R.success("行程结束",endRoute);
    }



    /**
     * 根据用户id查询进行中的行程
     *
     * @param userId 用户
     * @return R<List<Route>>
     */
    @GetMapping("/active")
    public R<List<Route>> get(@RequestParam Long userId) {
        log.info("用户:{},获取进行中行程", userId);
        List<Route> routes= routeService.getActiveRoutes(userId);
        return routes==null?R.error("无进行中的进程"):
                R.success("查询成功",routes);
    }

    /**
     * 根据用户id查询已结束的行程
     *
     * @param userId 用户id
     * @return R<List<Route>>
     */
    @GetMapping("/finished")
    public R<List<Route>> getFinishedRoutes(@RequestParam Long userId) {
        log.info("用户:{},获取已结束行程", userId);
        List<Route> routes= routeService.getFinishedRoutes(userId);
        return routes==null?R.error("无已结束的进程"):
                R.success("查询成功",routes);
    }

    /**
     * 删除路线
     *
     * @param routeId 线路id
     * @return R<String>
     */
    @DeleteMapping()
    public R<String> delete(@RequestParam Long routeId){
        log.info("删除id:{}的路线",routeId);
        return  routeService.removeById(routeId)?R.success("行程删除成功"):R.error("删除失败");
    }

}

