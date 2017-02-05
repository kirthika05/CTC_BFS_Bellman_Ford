package com.kirthika;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        Graph g;
        for (int t = 0; t < q; t++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            g = new Graph(n);
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;
                g.addEdge(u,v);
            }

            int startId = scanner.nextInt() - 1;
            g.printDistances(g.shortestReach(startId), startId);
        }
        scanner.close();
    }
}

class Graph {

    List<List<Integer>> adjList;
    int size;

    public Graph(int size) {
        adjList = new ArrayList<>();
        this.size = size;
        while (size > 0) {
            adjList.add(new ArrayList<Integer>());
            size--;
        }
    }

    public void addEdge(int first, int second) {
        adjList.get(first).add(second);
        adjList.get(second).add(first);
    }

    public int[] shortestReach(int startId) {
        int[] distances = new int[size];
        Arrays.fill(distances, 999999);
        distances[startId] = 0;

        for (int i = 1; i < size; i++) {
            for (int u = 0; u < size; u++) {
                for (int v = 0; v < adjList.get(u).size(); v++) {
                    relax(u, adjList.get(u).get(v), distances);
                }
            }
        }

        for (int i = 0; i < distances.length; i++) {
            if (distances[i] == 999999) {
                distances[i] = -1;
            }
        }
        return distances;
    }

    public static void relax(int u, int v, int[] distances) {
        if (distances[v] > distances[u] + 6) {
            distances[v] = distances[u] + 6;
        }
    }

    public static void printDistances(int[] distances, int startId) {
        String separator = "";
        for (int i = 0; i < distances.length; i++) {
            if (i == startId) continue;
            System.out.print(separator + distances[i]);
            separator = " ";
        }
        System.out.println();
    }
}
