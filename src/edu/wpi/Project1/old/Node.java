package edu.wpi.Project1.old;

import java.util.ArrayList;

/**
 * Created by John on 8/29/2017.
 */
public class Node {
    private Edge cameFrom;
    private String ltr;
    private double heur;
    private ArrayList<Edge> edges;

    public Node(String ltr){
        this.ltr = ltr;
        heur = 0;
        edges = new ArrayList<>();
    }

    public void cameFrom(Edge edge){
        this.cameFrom = edge;
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

    public ArrayList<Tuple> getChildrenWithWeight(){
        ArrayList<Tuple> list = new ArrayList();
        for(Edge edge: edges){
            list.add(new Tuple(edge.getWeight(),edge.getNode()));
        }
        return list;
    }

    public ArrayList<Node> getChildren(){
        ArrayList<Node> list = new ArrayList();
        for(Edge edge: edges){
            list.add(edge.getNode());
        }
        return list;
    }

    @Override
    public String toString() {
        return ltr;
    }

    public double getHeur() {
        return heur;
    }
}
