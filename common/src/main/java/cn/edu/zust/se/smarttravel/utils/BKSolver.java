package cn.edu.zust.se.smarttravel.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lweeg
 * @version 1.0
 */
public class BKSolver {
    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
        int[][] graph = {
                {0, 20, 5, 7, 1},
                {20, 0, 20, 8, 5},
                {5, 20, 0, 7, 13},
                {7, 8, 7, 0, 11},
                {1, 5, 13, 11, 17}
        };

        int[][] graph2={
                {0, 20, 5, 7},
                {20, 0, 20, 8},
                {5, 20, 0, 7},
                {7, 8, 7, 0}
        };



        List<Integer> shortestPath = findShortestPath(graph2);
        System.out.println("Shortest path: " + shortestPath);
        System.out.println("Total distance: " + calculatePathDistance(graph, shortestPath));
    }

    public static List<Integer> findShortestPath(int [][]graph){
        int length=graph.length;
        int[] nodes=new int[length-2];
        for(int i=0;i<nodes.length;i++){
            nodes[i]=i+1;
        }
        List<List<Integer>> allPermutationsOfMiddle = permute(nodes);
        List<List<Integer>> allPermutations=new ArrayList<>();
        for(List<Integer> i:allPermutationsOfMiddle){
            List<Integer> temp=new ArrayList<>();
            temp.add(0);
            for(int k:i){
                temp.add(k);
            }
            temp.add(length-1);
            allPermutations.add(temp);
        }

        int minPath=INF;
        List<Integer> shortestPath=new ArrayList<>();
        for(List<Integer> i:allPermutations){
            int tempPath=0;
            for (int j=0;j<i.size()-1;j++){
                tempPath+=graph[i.get(j)][i.get(j+1)];
            }
            if(tempPath<minPath){
                minPath =tempPath;
                shortestPath=i;
            }
        }

        return shortestPath;
    }

    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, result);
        return result;
    }

    private static void backtrack(int[] nums, int index, List<List<Integer>> result) {
        if (index == nums.length - 1) {
            result.add(toList(nums));
        } else {
            for (int i = index; i < nums.length; i++) {
                if (i > index && nums[i] == nums[index]) {
                    continue;
                }
                swap(nums, index, i);
                backtrack(nums, index + 1, result);
                swap(nums, index, i);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static List<Integer> toList(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return list;
    }

    public static Integer calculatePathDistance(int [][]graph,List<Integer> shortestPath){
        int path=0;
        for(int i=0;i<shortestPath.size()-1;i++){
            path+=graph[shortestPath.get(i)][shortestPath.get(i+1)];
        }
        return path;
    }

}
