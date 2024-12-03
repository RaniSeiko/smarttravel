package cn.edu.zust.se.smarttravel.utils.useless;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itczx.smarttourism.entity.R;
import com.itczx.smarttourism.entity.po.Route;
import com.itczx.smarttourism.entity.po.Spot;
import com.itczx.smarttourism.entity.po.User;
import com.itczx.smarttourism.service.RouteService;
import com.itczx.smarttourism.service.SpotService;
import com.itczx.smarttourism.service.UserService;
import com.itczx.smarttourism.utils.Dijkstra;
import com.itczx.smarttourism.utils.Edge;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.itczx.smarttourism.utils.getData.backDis;
import static com.itczx.smarttourism.utils.getData.getLatitude;

/**
 * 请忽调用
 */
@Slf4j
@RestController("/4a781d387a45938e45ca470cee581749")
public class Failure {
    @Resource
    private RouteService routeService;

    @Resource
    private SpotService spotService;

    @Resource
    private UserService userService;

    /**
     * 直接新增路线
     *
     * @param route
     * @return
     */
    @PostMapping("/save")
    public R<String> save(@RequestBody Route route) {
        log.info(route.toString());
        //初始化数据
        route.setState(1);
        double distance = 0;
        double spottime = 0;
        double money = 0;

        //userid
        LambdaQueryWrapper<User> queryWrapperuser = new LambdaQueryWrapper<>();
        queryWrapperuser.eq(User::getId, route.getUserId());//等值查询
        User user = userService.getOne(queryWrapperuser);

        //数据合理化判断
        if (route.getStart() == null) {
            return R.error("数据错误");
        }

        // 使用百度地图API获取spot1和spotstart之间的距离
        //double distance = backDis(getLatitude(spot1.getSpotname()), getLatitude(spotstart.getSpotname()));
        //System.out.println("距离: " + distance + "公里");

        LambdaQueryWrapper<Spot> queryWrapper0 = new LambdaQueryWrapper<>();
        queryWrapper0.eq(Spot::getSpotName, route.getStart());//等值查询
        Spot spotstart = spotService.getOne(queryWrapper0);
        if (spotstart == null) {
            return R.error("景点不存在");
        }
        String resultroute = String.valueOf(spotstart);
        spottime = spottime + spotstart.getResidenceTime();
        money = money + spotstart.getFunds();

        if (route.getPathway1() != null) {
            LambdaQueryWrapper<Spot> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(Spot::getSpotName, route.getPathway1());//等值查询
            Spot spot1 = spotService.getOne(queryWrapper1);
            resultroute = resultroute + "," + spot1.getSpotName();
            spottime = spottime + spot1.getResidenceTime();
            money = money + spot1.getFunds();
            distance = distance + backDis(getLatitude(spot1.getSpotName()), getLatitude(spotstart.getSpotName()));
        }

        if (route.getPathway2() != null) {
            LambdaQueryWrapper<Spot> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(Spot::getSpotName, route.getPathway2());//等值查询
            Spot spot2 = spotService.getOne(queryWrapper2);
            resultroute = resultroute + "," + spot2.getSpotName();
            spottime = spottime + spot2.getResidenceTime();
            money = money + spot2.getFunds();
            if (route.getPathway1() != null) {
                LambdaQueryWrapper<Spot> queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.eq(Spot::getSpotName, route.getPathway1());//等值查询
                Spot spot1 = spotService.getOne(queryWrapper1);
                distance = distance + backDis(getLatitude(spot2.getSpotName()), getLatitude(spot1.getSpotName()));
            }
        }

        if (route.getPathway3() != null) {
            LambdaQueryWrapper<Spot> queryWrapper3 = new LambdaQueryWrapper<>();
            queryWrapper3.eq(Spot::getSpotName, route.getPathway3());//等值查询
            Spot spot3 = spotService.getOne(queryWrapper3);
            resultroute = resultroute + "," + spot3.getSpotName();
            spottime = spottime + spot3.getResidenceTime();
            money = money + spot3.getFunds();
            if (route.getPathway2() != null) {
                LambdaQueryWrapper<Spot> queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.eq(Spot::getSpotName, route.getPathway1());//等值查询
                Spot spot2 = spotService.getOne(queryWrapper1);
                distance = distance + backDis(getLatitude(spot3.getSpotName()), getLatitude(spot2.getSpotName()));
            }
        }

        if (route.getPathway4() != null) {
            LambdaQueryWrapper<Spot> queryWrapper4 = new LambdaQueryWrapper<>();
            queryWrapper4.eq(Spot::getSpotName, route.getPathway4());//等值查询
            Spot spot4 = spotService.getOne(queryWrapper4);
            resultroute = resultroute + "," + spot4.getSpotName();
            spottime = spottime + spot4.getResidenceTime();
            money = money + spot4.getFunds();
            if (route.getPathway3() != null) {
                LambdaQueryWrapper<Spot> queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.eq(Spot::getSpotName, route.getPathway1());//等值查询
                Spot spot3 = spotService.getOne(queryWrapper1);
                distance = distance + backDis(getLatitude(spot4.getSpotName()), getLatitude(spot3.getSpotName()));
            }
        }

        if (route.getPathway5() != null) {
            LambdaQueryWrapper<Spot> queryWrapper5 = new LambdaQueryWrapper<>();
            queryWrapper5.eq(Spot::getSpotName, route.getPathway5());//等值查询
            Spot spot5 = spotService.getOne(queryWrapper5);
            resultroute = resultroute + "," + spot5.getSpotName();
            spottime = spottime + spot5.getResidenceTime();
            money = money + spot5.getFunds();
            if (route.getPathway4() != null) {
                LambdaQueryWrapper<Spot> queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.eq(Spot::getSpotName, route.getPathway1());//等值查询
                Spot spot4 = spotService.getOne(queryWrapper1);
                distance = distance + backDis(getLatitude(spot5.getSpotName()), getLatitude(spot4.getSpotName()));
            }
        }

        if (route.getPathway6() != null) {
            LambdaQueryWrapper<Spot> queryWrapper6 = new LambdaQueryWrapper<>();
            queryWrapper6.eq(Spot::getSpotName, route.getPathway6());//等值查询
            Spot spot6 = spotService.getOne(queryWrapper6);
            resultroute = resultroute + "," + spot6.getSpotName();
            spottime = spottime + spot6.getResidenceTime();
            money = money + spot6.getFunds();
            if (route.getPathway5() != null) {
                LambdaQueryWrapper<Spot> queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.eq(Spot::getSpotName, route.getPathway1());//等值查询
                Spot spot5 = spotService.getOne(queryWrapper1);
                distance = distance + backDis(getLatitude(spot6.getSpotName()), getLatitude(spot5.getSpotName()));
            }
        }

        if (route.getPathway7() != null) {
            LambdaQueryWrapper<Spot> queryWrapper7 = new LambdaQueryWrapper<>();
            queryWrapper7.eq(Spot::getSpotName, route.getPathway7());//等值查询
            Spot spot7 = spotService.getOne(queryWrapper7);
            resultroute = resultroute + "," + spot7.getSpotName();
            spottime = spottime + spot7.getResidenceTime();
            money = money + spot7.getFunds();
            if (route.getPathway6() != null) {
                LambdaQueryWrapper<Spot> queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.eq(Spot::getSpotName, route.getPathway1());//等值查询
                Spot spot6 = spotService.getOne(queryWrapper1);
                distance = distance + backDis(getLatitude(spot7.getSpotName()), getLatitude(spot6.getSpotName()));
            }
        }

        if (route.getPathway8() != null) {
            LambdaQueryWrapper<Spot> queryWrapper8 = new LambdaQueryWrapper<>();
            queryWrapper8.eq(Spot::getSpotName, route.getPathway8());//等值查询
            Spot spot8 = spotService.getOne(queryWrapper8);
            resultroute = resultroute + "," + spot8.getSpotName();
            spottime = spottime + spot8.getResidenceTime();
            money = money + spot8.getFunds();
            if (route.getPathway7() != null) {
                LambdaQueryWrapper<Spot> queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.eq(Spot::getSpotName, route.getPathway1());//等值查询
                Spot spot7 = spotService.getOne(queryWrapper1);
                distance = distance + backDis(getLatitude(spot8.getSpotName()), getLatitude(spot7.getSpotName()));
            }
        }

        if (route.getEnd() == null) {
            return R.error("数据错误");
        }
        LambdaQueryWrapper<Spot> queryWrapper9 = new LambdaQueryWrapper<>();
        queryWrapper9.eq(Spot::getSpotName, route.getEnd());//等值查询
        Spot spotend = spotService.getOne(queryWrapper9);
        if (spotend == null) {
            return R.error("景点不存在");
        }
        spottime = spottime + spotend.getResidenceTime();
        money = money + spotend.getFunds();

        //路线
        resultroute = resultroute + "," + spotend.getSpotName();
        route.setBestWay(resultroute);

        //预计时间
        double time = distance * 12 + spottime + (user.getRhythm()-0.5)*20;
        LocalDateTime endtime = null;
        if (route.getStart() != null) {
            endtime = route.getStartTime().plusMinutes((long) time);
        } else {
            endtime = route.getStartTime();
        }
        route.setExpectEndTime(endtime);

        //预计经费
        money = money + (user.getBudget()-0.5)*100;
        route.setExpectFunding(money);

        routeService.save(route);

        return R.success("新建路线成功");
    }
    @PostMapping("/save2")
    public R<String> save2(@RequestBody Route route) {
        log.info(route.toString());
        // 初始化数据
        route.setState(1);
        double distance = 0;
        double spottime = 0;
        double money = 0;

        // userid
        LambdaQueryWrapper<User> queryWrapperuser = new LambdaQueryWrapper<>();
        queryWrapperuser.eq(User::getId, route.getUserId());// 等值查询
        User user = userService.getOne(queryWrapperuser);

        // 数据合理化判断
        if (route.getStart() == null) {
            return R.error("数据错误");
        }

        // 使用百度地图API获取spot1和spotstart之间的距离
        // double distance = backDis(getLatitude(spot1.getSpotname()), getLatitude(spotstart.getSpotname()));
        // System.out.println("距离: " + distance + "公里");

        List<Spot> spots = new ArrayList<>();
        spots.add(new Spot(route.getStart(), 0)); // 起点


        // 查询所有spot1-8中不为空且距离最近的景点
        for (int i = 1; i <= 8; i++) {
            String pathwayName = "getPathway" + i;
            String pathwayValue = (String) getFieldValue(route, pathwayName);
            if (pathwayValue != null) {
                LambdaQueryWrapper<Spot> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.select(Spot::getSpotName, Spot::getResidenceTime, Spot::getFunds)
                        .eq(Spot::getSpotName, pathwayValue)
                        .notIn(Spot::getSpotName, spots.stream().map(Spot::getSpotName).collect(Collectors.toList()))
                        .orderByAsc(Spot::getSpotName);
                Spot spot = spotService.getOne(queryWrapper);
                if (spot != null) {
                    spots.add(spot);
                    spottime += spot.getResidenceTime();
                    money += spot.getFunds();
                    if (spots.size() > 1) {
                        Spot prevSpot = spots.get(spots.size() - 2);
                        distance += backDis(getLatitude(spot.getSpotName()), getLatitude(prevSpot.getSpotName()));
                    }
                }
            }
        }

        if (route.getEnd() == null) {
            return R.error("数据错误");
        }
        LambdaQueryWrapper<Spot> queryWrapper9 = new LambdaQueryWrapper<>();
        queryWrapper9.eq(Spot::getSpotName, route.getEnd());// 等值查询
        Spot spotend = spotService.getOne(queryWrapper9);
        if (spotend == null) {
            return R.error("景点不存在");
        }
        spottime += spotend.getResidenceTime();
        money += spotend.getFunds();

        // 路线
        String resultroute = String.join(",", spots.stream().map(Spot::getSpotName).collect(Collectors.toList()));
        route.setBestWay(resultroute);

        // 预计时间
        double time = distance * 12 + spottime + (user.getRhythm() - 0.5) * 20;
        LocalDateTime endtime = route.getStart() != null ? route.getStartTime().plusMinutes((long) time) : route.getStartTime();
        route.setExpectEndTime(endtime);

        // 预计经费
        money += (user.getBudget() - 0.5) * 100;
        route.setExpectFunding(money);

        routeService.save(route);

        return R.success("新建路线成功");
    }

    // 获取字段值的方法save2
    private Object getFieldValue(Route route, String fieldName) {
        try {
            Field field = Route.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(route);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/save3")
    public R<String> save3(@RequestBody Route route) {
        log.info(route.toString());
        // 初始化数据
        route.setState(1);
        double distance = 0;
        double spottime = 0;
        double money = 0;

        // userid
        LambdaQueryWrapper<User> queryWrapperuser = new LambdaQueryWrapper<>();
        queryWrapperuser.eq(User::getId, route.getUserId());// 等值查询
        User user = userService.getOne(queryWrapperuser);

        // 数据合理化判断
        if (route.getStart() == null) {
            return R.error("数据错误");
        }

        List<Spot> spots = new ArrayList<>();
        spots.add(new Spot(route.getStart(), 0)); // 起点

        // 查询所有spot1-8中不为空且距离最近的景点
        for (int i = 1; i <= 8; i++) {
            String pathwayName = "getPathway" + i;
            String pathwayValue = (String) getFieldValue(route, pathwayName);
            if (pathwayValue != null) {
                LambdaQueryWrapper<Spot> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.select(Spot::getSpotName, Spot::getResidenceTime, Spot::getFunds)
                        .eq(Spot::getSpotName, pathwayValue)
                        .notIn(Spot::getSpotName, spots.stream().map(Spot::getSpotName).collect(Collectors.toList()))
                        .orderByAsc(Spot::getSpotName);
                Spot spot = spotService.getOne(queryWrapper);
                if (spot != null) {
                    spots.add(spot);
                    spottime += spot.getResidenceTime();
                    money += spot.getFunds();
                    if (spots.size() > 1) {
                        Spot prevSpot = spots.get(spots.size() - 2);
                        distance += backDis(getLatitude(spot.getSpotName()), getLatitude(prevSpot.getSpotName()));
                    }
                }
            }
        }

        if (route.getEnd() == null) {
            return R.error("数据错误");
        }
        LambdaQueryWrapper<Spot> queryWrapper9 = new LambdaQueryWrapper<>();
        queryWrapper9.eq(Spot::getSpotName, route.getEnd());// 等值查询
        Spot spotend = spotService.getOne(queryWrapper9);
        if (spotend == null) {
            return R.error("景点不存在");
        }
        spottime += spotend.getResidenceTime();
        money += spotend.getFunds();


        // 引入Dijkstra算法
        Map<String, List<Edge>> graph = buildGraph(route); // 构建景点之间的图
        Dijkstra dijkstraAlgorithm = new Dijkstra();
        Map<String, Double> distances = dijkstraAlgorithm.dijkstra(graph,route.getStart());

        // 路线
        String resultroute = String.join(",", spots.stream().map(Spot::getSpotName).collect(Collectors.toList()));
        route.setBestWay(resultroute);

        // 预计时间
        double time = distance * 12 + spottime + (user.getRhythm() - 0.5) * 20;
        LocalDateTime endtime = route.getStart() != null ? route.getStartTime().plusMinutes((long) time) : route.getStartTime();
        route.setExpectEndTime(endtime);

        // 预计经费
        money += (user.getBudget() - 0.5) * 100;
        route.setExpectFunding(money);

        routeService.save(route);

        return R.success("新建路线成功");
    }


    // 构建景点之间的图save3
    private Map<String, List<Edge>> buildGraph(Route route) {
        // 实现构建图的逻辑，返回景点之间的连接关系和距离信息
        Map<String, List<Edge>> graph = new HashMap<>();
        // 添加景点之间的连接关系和距离信息到graph中

        List<String> pathwayList = Arrays.asList(
                route.getStart(),
                route.getPathway1(),
                route.getPathway2(),
                route.getPathway3(),
                route.getPathway4(),
                route.getPathway5(),
                route.getPathway6(),
                route.getPathway7(),
                route.getPathway8(),
                route.getEnd()
        );

        for (String pathway : pathwayList) {
            graph.put(pathway, Arrays.asList(
                    new Edge(pathway, route.getStart(), (pathway != null && route.getPathway1() != null) ? backDis(getLatitude(pathway), getLatitude(route.getPathway1())) : 100000),
                    new Edge(pathway, route.getPathway1(), (pathway != null && route.getPathway1() != null) ? backDis(getLatitude(pathway), getLatitude(route.getPathway1())) : 100000),
                    new Edge(pathway, route.getPathway2(), (pathway != null && route.getPathway1() != null) ? backDis(getLatitude(pathway), getLatitude(route.getPathway1())) : 100000),
                    new Edge(pathway, route.getPathway3(), (pathway != null && route.getPathway1() != null) ? backDis(getLatitude(pathway), getLatitude(route.getPathway1())) : 100000),
                    new Edge(pathway, route.getPathway4(), (pathway != null && route.getPathway1() != null) ? backDis(getLatitude(pathway), getLatitude(route.getPathway1())) : 100000),
                    new Edge(pathway, route.getPathway5(), (pathway != null && route.getPathway1() != null) ? backDis(getLatitude(pathway), getLatitude(route.getPathway1())) : 100000),
                    new Edge(pathway, route.getPathway6(), (pathway != null && route.getPathway1() != null) ? backDis(getLatitude(pathway), getLatitude(route.getPathway1())) : 100000),
                    new Edge(pathway, route.getPathway7(), (pathway != null && route.getPathway1() != null) ? backDis(getLatitude(pathway), getLatitude(route.getPathway1())) : 100000),
                    new Edge(pathway, route.getPathway8(), (pathway != null && route.getPathway1() != null) ? backDis(getLatitude(pathway), getLatitude(route.getPathway1())) : 100000),
                    new Edge(pathway, route.getEnd(), (pathway != null && route.getPathway1() != null) ? backDis(getLatitude(pathway), getLatitude(route.getPathway1())) : 100000)
            ));
        }

        return graph;
    }
}
