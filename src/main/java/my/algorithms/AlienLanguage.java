package my.algorithms;

import java.util.*;

/**
 * Given a sorted dictionary (array of words) of an alien language, find order of characters in the language. <br /> <br />
 *
 * <b>Examples:</b> <br />
 * Input:  words[] = {"baa", "abcd", "abca", "cab", "cad"} <br />
 * Output: Order of characters is 'b', 'd', 'a', 'c'
 */
public class AlienLanguage {

    /**
     * Create Adjacency list using the dictionary of words.
     */
    private static Map<Character, Set<Character>> getAdjacencyList(String[] words) {
        Map<Character, Set<Character>> adjacencyListMap = new HashMap<>();

        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                String str1 = words[i];
                String str2 = words[j];

                for (int k = 0; k < str1.length() && k < str2.length(); k++) {
                    if (str1.charAt(k) != str2.charAt(k)) {
                        Character start = str1.charAt(k);
                        Character end = str2.charAt(k);
                        if (adjacencyListMap.containsKey(start)) {
                            adjacencyListMap.get(start).add(end);
                        } else {
                            Set<Character> ends = new HashSet<>();
                            ends.add(end);
                            adjacencyListMap.put(start, ends);
                        }
                        break;
                    }
                }
            }
        }

        return adjacencyListMap;
    }

    /**
     * Topological sort
     */
    private static List<Character> topSort(Map<Character, Set<Character>> adjacencyListMap) {
        HashSet<Character> visitedNodes = new HashSet<>();
        List<Character> topSortNodesReverse = new ArrayList<>();

        for (Character node : adjacencyListMap.keySet()) {
            topSort(node, adjacencyListMap, visitedNodes, topSortNodesReverse);
        }

        List<Character> topSortNodes = new ArrayList<>();
        for (int i = topSortNodesReverse.size() - 1; i >= 0; i--) {
            topSortNodes.add(topSortNodesReverse.get(i));
        }

        return topSortNodes;
    }

    private static void topSort(Character currentNode, Map<Character, Set<Character>> adjacencyListMap, Set<Character> visitedNodes,
                        List<Character> topSortNodes) {

        if (visitedNodes.contains(currentNode)) {
            return;
        }

        if (adjacencyListMap.containsKey(currentNode)) {
            for (Character node : adjacencyListMap.get(currentNode)) {
                topSort(node, adjacencyListMap, visitedNodes, topSortNodes);
            }
        }

        visitedNodes.add(currentNode);
        topSortNodes.add(currentNode);
    }

    public static List<Character> getAlienLanguage(String[] words) {
        Map<Character, Set<Character>> adjacencyList = getAdjacencyList(words);
        return topSort(adjacencyList);
    }

    /**
     * Driver code
     */
    public static void main(String[] args) {
        String words[] = {"baa", "abcd", "abca", "cab", "cad"};
        System.out.println(getAlienLanguage(words));
    }
}
