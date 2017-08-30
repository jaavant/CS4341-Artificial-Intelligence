package edu.wpi.Project1.util;

/**
 * Created by John on 8/29/2017.
 */
public class Edge {
    private Node node;
    private double weight;

    public Edge(Node node, double weight){
        this.node = node;
        this.weight = weight;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
