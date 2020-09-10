package my.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Travelling salesman problem.
 *
 * Brute force approach. <br />
 * time complexity O(n!) <br />
 *
 * Not a recommended solution. Only for analysis purpose.
 *
 * @author github.com/saikatroy038
 */
public class TravellingSalesmanProblem {

    private static class Node {
        int nodeId;
        boolean include;

        public Node(int nodeId, boolean include) {
            this.nodeId = nodeId;
            this.include = include;
        }
    }

    /**
     * @param distance distance[i][j] = distance from i'th node to j'th node.
     * @return minimum path distance from 0th node going through all nodes
     */
    public static int minDistance(int[][] distance) {
        // find all possible path combinations
        List<Node> nodes = new ArrayList<>();
        for (int i = 1; i < distance.length; i++) {
            nodes.add(new Node(i, true));
        }
        List<List<Integer>> combinations = combinations(nodes);

        // calculate cost for each path and choose the min cost path
        int minCost = Integer.MAX_VALUE;
        for (List<Integer> combination : combinations) {
            int currentCombinationCost = 0;
            for (int i = 0; i < combination.size(); i++) {
                if (i == 0) {
                    currentCombinationCost += distance[0][combination.get(i)];
                } else {
                    currentCombinationCost += distance[combination.get(i - 1)][combination.get(i)];
                }

                if (i == combination.size() - 1) {
                    currentCombinationCost += distance[combination.get(i)][0];
                }
            }

            minCost = Math.min(minCost, currentCombinationCost);
        }

        return minCost;
    }

    /**
     * Find all possible path combinations among nodes
     * @return List of paths
     */
    private static List<List<Integer>> combinations(List<Node> nodes) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).include == false) {
                continue;
            }

            nodes.get(i).include = false;

            List<List<Integer>> subList = combinations(nodes);
            if (subList.isEmpty()) {
                List<Integer> combination = new ArrayList<>();
                combination.add(nodes.get(i).nodeId);
                result.add(combination);
            } else {
                for (List<Integer> subCombination: subList) {
                    List<Integer> combination = new ArrayList<>();
                    combination.add(nodes.get(i).nodeId);
                    combination.addAll(subCombination);
                    result.add(combination);
                }
            }

            nodes.get(i).include = true;
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] distances = {
                {0, 10, 15, 20},
                {5, 0, 9, 10},
                {6, 13, 0, 12},
                {8, 8, 9, 0}
        };

        System.out.println(minDistance(distances));
    }
}
