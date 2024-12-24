package cn.edu.zust.se.smarttravel.service;

import cn.edu.zust.se.smarttravel.entity.R;
import cn.edu.zust.se.smarttravel.entity.po.Route;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface RouteService extends IService<Route> {

    R<Route> saveRoute(Route route);

    R<Route> addRoute(Route route);

    List<Route> getAllRoutesByUserId(Long userid);

    Route endingRoute(Long routeId,Double funding);

    List<Route> getActiveRoutes(Long userid);

    List<Route> getFinishedRoutes(Long userid);
}
