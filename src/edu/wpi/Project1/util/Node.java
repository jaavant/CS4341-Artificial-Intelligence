package edu.wpi.Project1.util;

import java.util.ArrayList;

/**
 * Created by John on 8/29/2017.
 */
public class Node {
    private String ltr;
    private ArrayList<Edge> edges;

    public Node(String ltr){
        this.ltr = ltr;
    }

    public void addEdge(Node target, double weight){
        edges.add(new Edge(target, weight));
    }

    public ArrayList<Edge> getEdges(){
        return edges;
    }


}
