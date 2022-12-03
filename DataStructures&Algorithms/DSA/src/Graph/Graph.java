package Graph;

import java.util.*;

public class Graph<T> {
    private class Node {
        T data;
        boolean visited;
        Map<Node, Integer> neighbors; // for weight graph, use hashmap<node, int>
//        Set<Node> neighbors;

        Node() {

        }
    }

    public Set<Node> nodes;

    public void add(Node node) {
        nodes.add(node);
    }

    List<Node> findPath(Node start, Node end) {
        List<Node> path = new ArrayList<>();
        DFS(start, end, path);
        return path;
    }

    void DFS(Node node, Node end, List<Node> path) {
        if (node == end) {
            path.add(node);
            return;
        }
        node.visited = true;
        for (Node neighbor : node.neighbors.keySet()) {
            if (!neighbor.visited) {
                DFS(neighbor, end, path);
                path.add(node);
            }
        }
    }

    void BFS(Node start, Node end) {
        start.visited = true;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node == end) {
                // return the path from the start to the end, which is the shortest path
            }
            for (Node neighbor : node.neighbors.keySet()) {
                if (!neighbor.visited) {
                    neighbor.visited = true;
                    // TODO add a node to the path
                    queue.add(neighbor);
                }
            }
        }
        // TODO return no path
    }

    /**
     * only works for non-negative edges
     * @param start
     * @param end
     */
    void djikstra(Node start, Node end) { // TODO change return type
        Map<Node, Node> prev = new HashMap<>(); // prev node
        Map<Node, Integer> distance = new HashMap<>(); // the dist from start to this node
        Map<Node, Integer> nodeIndex = new HashMap<>(); // record the index of nodes (used to change priority, in priority queue)
        prev.put(start, start);
        distance.put(start, 0);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        while (!pq.isEmpty()) {
            Node n = pq.poll();
            if (n == end) {
                // return the path
            }
            for (Node neighbor : n.neighbors.keySet()) {
                if (prev.get(neighbor) == null || distance.get(neighbor) > distance.get(n) + n.neighbors.get(neighbor)) {
                    distance.put(neighbor, distance.get(n) + n.neighbors.get(neighbor));
                    prev.put(neighbor, n);
                    // TODO update the distance of n in pq (decrease key???)
                    // O(logN) if we know the index of this element -> use hash map
                }
            }
        }
    }

    private ArrayList<Node> reconstructPath(HashMap<Node, Node> prev, Node end) {
        return null;
    }
}
