# Graph

Graph vs. Tree:

- Trees have no loops, graph do
- Trees store hierachical data, graph store unordered data

## Graph Basics

1. Types:
   - undirected graph
   - directed graph
   - weighted graph
2. Terminologies
   - Graph:
     - vertice / node
     - edge
     - adjacent vertices / neighbors: nodes connected by an edge
   - path
   - cycle: a non-empty trail in which only the first and last node are equal
   - connected: a graph is connected if there are paths return all pairs of nodes
     - connected components
   - degree
     - In-degree
     - out-degree
3. Graph types:
   - simple graph: a pair of nodes can have at most 1 edge, and one node can't have self-edges. Otherwise, this graph is a complicated graph.
   - DAG: Directed Acyclic Graph - a directed graph with no cycles
   - Bipartite graph
   - Complete graphs: a complete graph contains all possible edges
     - for undirected graph with N nodes, it has N(N - 1) / 2 edges
     - for directed graph with N nodes, it has N<sup>2</sup> edges
4. Graph Problems // TODO

## Graph representation

1. Node

   ```java
   public class Graph<T> {
     private class Node {
       T data;
       Set<Node> neighbors;
     }
     public Set<Node> nodes;
   }
   ```

   - adjacent list: suitable for sparse graph
   - adjacent matrix: suitable for dense graph
   - edge set

## Path Problems

### Path finding

Given `start` and `end` nodes, find a path from `start` to `end`. (Often we want shortest path with least costs.)

#### DFS - stack

#### BFS - queue

BFS can give us a shortest path.



### Shortest Path

BFS！！！

### Minimum Cost

// TODO 看看这个算法怎么写！！！

## Graph Traversal

### Depth First Search (DFS)

```java
```

### Breadth First Search (BFS)

```java
```



