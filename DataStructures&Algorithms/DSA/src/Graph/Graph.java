package Graph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Set;

public class Graph<T> {
    private class Node {
        T data;
        boolean visited;
        Set<Node> neighbors;
    }
    public Set<Node> nodes;

    void findPath(Node start, Node end) {

    }

    void DFS(Node node, Node end) {
//        if (node == end) return
        node.visited = true;
        for (Node neighbor : node.neighbors) {
            if (!neighbor.visited) {
                DFS(neighbor, end);
                // TODO prepend node to the path
            }
        }
        // return null;
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
