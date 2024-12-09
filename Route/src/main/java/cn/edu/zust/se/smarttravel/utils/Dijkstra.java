package cn.edu.zust.se.smarttravel.utils;

import java.util.*;

public class Dijkstra {

    public Map<String, Double> dijkstra(Map<String, List<Edge>> graph, String start) {
        Map<String, Double> distances = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        Set<String> visited = new HashSet<>();

        for (String vertex : graph.keySet()) {
            distances.put(vertex, Double.MAX_VALUE);
        }
        distances.put(start, 0.0);

        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);

            for (Edge neighbor : graph.get(current)) {
                double newDistance = distances.get(current) + neighbor.distance;
                if (newDistance < distances.get(neighbor.destination)) {
                    distances.put(neighbor.destination, newDistance);
                    queue.add(neighbor.destination);
                }
            }
        }
        return distances;
    }

}

