package edu.wpi.Project1.util;

import java.util.ArrayList;

/**
 * Created by John on 8/29/2017.
 */
public class Node {
    private String ltr;
    private double heur;
    private ArrayList<Edge> edges;

    public Node(String ltr){
        this.ltr = ltr;
        heur = 0;
        edges = new ArrayList<>();
    }

    public void addEdge(Node target, double weight){
        edges.add(new Edge(target, weight));
    }

    public void addHeur(double heur){
        this.heur = heur;
    }

    public ArrayList<Edge> getEdges(){
        return edges;
    }


}
