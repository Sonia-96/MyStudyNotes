package Graph;

import java.util.*;

public class Graph<T> {
    private class Node {
        T data;
        boolean visited;
        Set<Node> neighbors;

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
        for (Node neighbor : node.neighbors) {
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
            for (Node neighbor : node.neighbors) {
                if (!neighbor.visited) {
                    neighbor.visited = true;
                    // TODO add a node to the path
                    queue.add(neighbor);
                }
            }
        }
        // TODO return no path
    }
}
