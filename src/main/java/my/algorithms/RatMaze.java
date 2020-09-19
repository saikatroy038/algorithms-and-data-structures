package my.algorithms;

import java.util.*;

/**
 * Rate maze problem use BFS.
 */
public class RatMaze {

    private static class Node {
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return x == node.x &&
                    y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    /**
     * find shortest path cost from (0, 0) to (endX, endY)
     * @param maze maze[i][j] = 1 means blocked path, 0 means allowed path.
     * @param endX
     * @param endY
     * @return shortest path cost. -1 if path doesn't exist.
     */
    public static int minPathCost(int[][] maze, int endX, int endY) {
        int cost = 0;
        Set<Node> visitedNodes = new HashSet<>();
        Queue<Node> nodes = new LinkedList<>();

        int[] xMoves = {1, -1, 0, 0};
        int[] yMoves = {0, 0, -1, 1};

        Node start = new Node(0, 0, 0);
        nodes.add(start);
        visitedNodes.add(start);

        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            if (node.x == endX && node.y == endY) {
                return node.cost;
            }

            for (int i = 0; i < xMoves.length; i++) {
                int x = node.x + xMoves[i];
                int y = node.y + yMoves[i];

                try {
                    if (maze[x][y] == 0) {
                        Node adjacentNode = new Node(x, y, node.cost + 1);
                        if (!visitedNodes.contains(adjacentNode)) {
                            nodes.add(adjacentNode);
                            visitedNodes.add(adjacentNode);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        // 25 min path cost
        int[][] maze1 = {
                {0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 1},
                {0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 1, 1},
                {0, 0, 0, 1, 0, 0, 0, 0}
        };
        System.out.println(minPathCost(maze1, 6, 7));

        // 13 min path cost
        int[][] maze2 = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        System.out.println(minPathCost(maze2, 6, 7));
    }
}
