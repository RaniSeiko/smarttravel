package cn.edu.zust.se.smarttravel.service.Impl;

import cn.edu.zust.se.smarttravel.entity.R;
import cn.edu.zust.se.smarttravel.entity.po.Route;
import cn.edu.zust.se.smarttravel.entity.po.Spot;
import cn.edu.zust.se.smarttravel.entity.po.User;
import cn.edu.zust.se.smarttravel.mapper.RouteMapper;
import cn.edu.zust.se.smarttravel.mapper.SpotMapper;
import cn.edu.zust.se.smarttravel.service.RouteService;
import cn.edu.zust.se.smarttravel.service.SpotService;
import cn.edu.zust.se.smarttravel.service.UserService;
import cn.edu.zust.se.smarttravel.utils.BKSolver;
import cn.edu.zust.se.smarttravel.utils.getData;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Route> implements RouteService {

    @Resource
    private UserService userService;

    @Resource
    private SpotMapper spotMapper;

    @Resource
    private SpotService spotService;


    @Override
    public R<Route> saveRoute(Route route) {
        //线路状态设置
        route.setState(1);
        //预计距离
        double distance;
        //预计景点耗时
        double spotTime = 0;
        //预计开销
        double money = 0;

        //获取用户信息
        User user = userService.getById(route.getUserId());

        //数据合理化判断
        if (route.getStart() == null || route.getEnd() == null) {
            System.out.println("数据错误");
            return R.error("数据错误");
        }
        //景点，根据景点名，从数据库获取
        List<Spot> spots = new ArrayList<>();
        //景点名
        List<String> spotsName = new ArrayList<>();

        //起点
        spotsName.add(route.getStart());
        //途径点
        addSpotIfValid(spotsName, route.getPathway1());
        addSpotIfValid(spotsName, route.getPathway2());
        addSpotIfValid(spotsName, route.getPathway3());
        addSpotIfValid(spotsName, route.getPathway4());
        addSpotIfValid(spotsName, route.getPathway5());
        addSpotIfValid(spotsName, route.getPathway6());
        addSpotIfValid(spotsName, route.getPathway7());
        addSpotIfValid(spotsName, route.getPathway8());
        //终点
        spotsName.add(route.getEnd());

        //获取具体景点数据
        for (String name : spotsName) {
            Spot temp = spotMapper.selectOne(new LambdaQueryWrapper<Spot>()
                    .eq(Spot::getSpotName, name));
            //景点不存在则新增景点
            if (temp == null) {
                temp = new Spot();
                temp.setSpotName(name);
                temp.setResidenceTime(15);
                temp.setFunds(50);
                spotService.save(temp);
            }
            spots.add(temp);
        }


        Map<Integer, Spot> transformSpot = new HashMap<>();

        for (int i = 0; i < spots.size(); i++) {
            transformSpot.put(i, spots.get(i));
        }

        //构建上三角矩阵
        int[][] graph = new int[spots.size()][spots.size()];
        for (int i = 0; i < spots.size(); i++) {
            for (int j = i; j < spots.size(); j++) {
                graph[i][j] = (int) getData.backDis(getData.getLatitude(spotsName.get(i)),
                        getData.getLatitude(spotsName.get(j)));
            }
        }

        //补全矩阵
        for (int i = spots.size() - 1; i >= 1; i--) {
            for (int j = i - 1; j >= 0 && j != i; j--) {
                graph[i][j] = graph[j][i];
            }
        }

        List<Integer> shortPath = BKSolver.findShortestPath(graph);
        log.info("最短路径{}", shortPath);
        //最短路径值
        distance = BKSolver.calculatePathDistance(graph, shortPath);
        log.info("最短路径值:{}km",distance);
        StringBuilder bestWay = new StringBuilder();
        for (int k : shortPath) {
            bestWay.append(transformSpot.get(k).getSpotName()).append(',');
            spotTime += transformSpot.get(k).getResidenceTime();
            money += transformSpot.get(k).getFunds();
        }
        route.setStartTime(LocalDateTime.now());
        money += (user.getBudget() - 0.5) * 100;
        double timeMinutes = distance * 5 + spotTime + (user.getRhythm() - 0.5) * 20;
        log.info("分钟数:{}",timeMinutes);
        LocalDateTime endtime = route.getStartTime().plusMinutes((long) timeMinutes);
        log.info("结束时间:{}",endtime);
        route.setExpectEndTime(endtime);
        route.setExpectFunding(money);
        route.setBestWay(bestWay.toString());
        this.save(route);

        return R.success(this.getById(route.getId()));
    }

    @Override
    public List<Route> getAllRoutesByUserId(Long userid) {

        LambdaQueryWrapper<Route> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Route::getUserId, userid);
        int count = (int) this.count(queryWrapper);
        if (count == 0) {
            return null;
        } else {
            return this.list(queryWrapper);
        }
    }

    @Override
    public Route endingRoute(Long routeId, Double funding) {
        Route route = this.getById(routeId);
        User user = userService.getById(route.getUserId());
        LocalDateTime actualEndTime = LocalDateTime.now();
        route.setActualEndTime(actualEndTime);
        LocalDateTime exceptEndingTime = route.getExpectEndTime();
        Duration duration = Duration.between(exceptEndingTime, actualEndTime);
        double defTime = duration.toMinutes();
        route.setDefTime(defTime);

        //优化行程系数
        double timenumber = user.getRhythm();
        if (defTime > 60) {
            if (timenumber <= 0.8) {
                timenumber = timenumber + 0.2;
                user.setRhythm(timenumber);
            } else {
                timenumber = 1.0;
                user.setRhythm(timenumber);
            }
        } else if (defTime < 60) {
            if (timenumber >= 0.2) {
                timenumber = timenumber - 0.2;
                user.setRhythm(timenumber);
            } else {
                timenumber = 0.0;
                user.setRhythm(timenumber);
            }
        }

        //优化消费系数
        route.setActualFunding(funding);
        double expectedFunding = route.getExpectFunding();
        double actualFunding = funding;
        double defFunding = actualFunding - expectedFunding;
        route.setDefFunding(defFunding);
        double fundnumber = user.getBudget();
        if (defTime > 60) {
            if (fundnumber <= 0.8) {
                fundnumber = fundnumber + 0.2;
                user.setBudget(fundnumber);
            } else {
                fundnumber = 1.0;
                user.setBudget(fundnumber);
            }
        } else if (defTime < 60) {
            if (fundnumber >= 0.2) {
                fundnumber = fundnumber - 0.2;
                user.setBudget(fundnumber);
            } else {
                fundnumber = 0.0;
                user.setBudget(fundnumber);
            }
        }
        //行程数加一
        user.setCount(user.getCount() + 1);
        //行程结束标记
        route.setState(0);
        //数据库更新
        this.updateById(route);
        userService.updateById(user);

        return this.getById(route.getId());
    }

    @Override
    public List<Route> getActiveRoutes(Long userid) {
        LambdaQueryWrapper<Route> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Route::getState, 1)
                .eq(Route::getUserId, userid);
        int count = (int) this.count(queryWrapper);
        if (count == 0) {
            return null;
        } else {
            return this.list(queryWrapper);
        }
    }

    @Override
    public List<Route> getFinishedRoutes(Long userid) {
        LambdaQueryWrapper<Route> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Route::getState, 0)
                .eq(Route::getUserId, userid);
        int count = (int) this.count(queryWrapper);
        if (count == 0) {
            return null;
        } else {
            return this.list(queryWrapper);
        }
    }

    private void addSpotIfValid(List<String> spotsName, String spot) {
        if (spot != null && !spot.equals("")) {
            spotsName.add(spot);
        }
    }


}

