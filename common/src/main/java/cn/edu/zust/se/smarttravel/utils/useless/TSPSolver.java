package cn.edu.zust.se.smarttravel.utils.useless;

/**
 * @author lweeg
 */
import java.util.*;

public class TSPSolver {

    private static final int INF = Integer.MAX_VALUE;

    public static List<Integer> getShortestPath(int[][] graph, int start, int end) {
        int V = graph.length;
        int[] dist = new int[V];
        int[] prev = new int[V];

        // Initialize distance array and prev array
        for (int i = 1; i < V; i++) {
            dist[i] = graph[start][i];
            prev[i] = start;
        }

        // Dynamic programming loop
        for (int i = 2; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                int tempDist = dist[j] + graph[j][i];
                if (tempDist < dist[i]) {
                    dist[i] = tempDist;
                    prev[i] = j;
                }
            }
        }

        // Construct the shortest path
        List<Integer> shortestPath = new ArrayList<>();
        int current = end;
        while (current != start) {
            shortestPath.add(current);
            current = prev[current];
        }
        shortestPath.add(start); // Add the starting point

        // Reverse the path to ensure it starts from 'start' and ends at 'end'
        shortestPath = new ArrayList<>(shortestPath.subList(0, shortestPath.size()));
        Collections.reverse(shortestPath);

        return shortestPath;
    }

    public static List<Integer> solveTSPWithAllVertices(int[][] graph, int start, int end) {
        int V = graph.length;

        // 将上三角矩阵转换为完全图
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                graph[j][i] = graph[i][j]; // 填充下三角部分
            }
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        boolean[] visited = new boolean[V];
        visited[start] = true;

        List<Integer> tspPath = new ArrayList<>();
        tspPath.add(start);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            for (int next = 0; next < V; next++) {
                if (!visited[next] && graph[current][next] != INF) {
                    stack.push(next);
                    visited[next] = true;
                    tspPath.add(next);
                }
            }
        }

        tspPath.add(end);

        return tspPath;
    }
    public static void main(String[] args) {
        int[][] graph = {
                {0, 10, 15, INF},
                {INF, 0, 20, 8},
                {INF, INF, 0, 7},
                {INF, INF, INF, 0}
        };

        int[][] graph2 = {
                {0, 10, 15, 7,1},
                {INF, 0, 20, 8,5},
                {INF, INF, 0, 7,13},
                {INF, INF, INF, 0,11},
                {INF,INF,INF,INF,17}
        };
        int[][] graph3 = {
                {0, 10, 15},
                {INF, 0, 20},
                {INF, INF, 0 }

        };

        int[][] graph4 = {
                {0, 15, 10, 20},
                {INF, 0, 1, 3},
                {INF, INF, 0, 2},
                {INF, INF, INF, 0}
        };


        int start = 0;
        int end = 3;

        List<Integer> shortestPath = getShortestPath(graph, start, end);
        System.out.println("Shortest Path (start to end only): " + shortestPath);

        List<Integer> tspPath = solveTSPWithAllVertices(graph, start, end);
        System.out.println("TSP Path (visit all vertices): " + tspPath);

        List<Integer> tspPath2 = solveTSPWithAllVertices(graph2, 0, 4);
        System.out.println("TSP Path (visit all vertices): " + tspPath2);

        List<Integer> tspPath3 = solveTSPWithAllVertices(graph3, 0, 2);
        System.out.println("TSP Path (visit all vertices): " + tspPath3);

        List<Integer> tspPath4 = solveTSPWithAllVertices(graph4, 0, 3);
        System.out.println("TSP Path4 (visit all vertices): " + tspPath4);
    }
}
