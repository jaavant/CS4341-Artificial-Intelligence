package edu.wpi.Project1.util;

/**
 * Created by John on 9/5/2017.
 */
public class Tuple {
    private Node node;
    private double weight;

    public Tuple(Double weight, Node node){
        this.weight = weight;
        this.node = node;
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
