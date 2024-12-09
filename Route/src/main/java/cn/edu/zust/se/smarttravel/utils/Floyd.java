package cn.edu.zust.se.smarttravel.utils;


import java.util.ArrayList;
import java.util.List;

public class Floyd {
    private static final int INF=Integer.MAX_VALUE;
    public List<Integer> getShortestPath(int[][] graph, int start, int end) {
        int V = graph.length;
        int[][] dist = new int[V][V];
        int[][] path = new int[V][V];

        // 初始化
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
                if (i != j && dist[i][j] != INF) {
                    path[i][j] = i;
                } else {
                    path[i][j] = -1;
                }
            }
        }

        //算法核心步骤
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }

        // 最短路径
        List<Integer> shortestPath = new ArrayList<>();
        int current = end;
        while (current != start) {
            shortestPath.add(0, current);
            current = path[start][current];
        }
        shortestPath.add(0, start);

        return shortestPath;
    }


    public int getShortestDistance(int[][] graph, int start, int end) {
        return graph[start][end];
    }
}
