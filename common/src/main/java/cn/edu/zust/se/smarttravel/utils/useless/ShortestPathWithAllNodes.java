package cn.edu.zust.se.smarttravel.utils.useless;

/**
 * @author lweeg
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.List;

public class ShortestPathWithAllNodes {

    private static final int INF = Integer.MAX_VALUE;
    private static final int NUM_NODES = 5; // Number of nodes in the graph

    public static void main(String[] args) {
        int[][] graph2 = {
                {0, 20, 5, 7, 1},
                {20, 0, 20, 8, 5},
                {5, 20, 0, 7, 13},
                {7, 8, 7, 0, 11},
                {1, 5, 13, 11, 17}
        };

        List<Integer> shortestPath = findShortestPath(graph2, 0, NUM_NODES - 1);
        System.out.println("Shortest path: " + shortestPath);
        System.out.println("Total distance: " + calculatePathDistance(graph2, shortestPath));
    }

    public static List<Integer> findShortestPath(int[][] graph, int start, int end) {
        List<Integer> shortestPath = new ArrayList<>();
        dfs(graph, start, end, new ArrayList<>(), shortestPath);
        return shortestPath;
    }

    private static void dfs(int[][] graph, int currentNode, int endNode, List<Integer> currentPath, List<Integer> shortestPath) {
        if (currentNode == endNode && currentPath.size() == NUM_NODES) {
            if (shortestPath.isEmpty() || calculatePathDistance(graph, currentPath) < calculatePathDistance(graph, shortestPath)) {
                shortestPath.clear();
                shortestPath.addAll(currentPath);
            }
            return;
        }

        for (int nextNode = 0; nextNode < NUM_NODES; nextNode++) {
            if (!currentPath.contains(nextNode) && graph[currentNode][nextNode] != INF) {
                currentPath.add(nextNode);
                dfs(graph, nextNode, endNode, currentPath, shortestPath);
                currentPath.remove(currentPath.size() - 1); // Backtrack
            }
        }
    }

    private static int calculatePathDistance(int[][] graph, List<Integer> path) {
        int distance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            int fromNode = path.get(i);
            int toNode = path.get(i + 1);
            distance += graph[fromNode][toNode];
        }
        return distance;
    }
}
