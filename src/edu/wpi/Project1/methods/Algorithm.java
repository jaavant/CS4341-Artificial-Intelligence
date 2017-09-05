package edu.wpi.Project1.methods;

import edu.wpi.Project1.util.Node;

import java.util.AbstractCollection;
import java.util.ArrayDeque;
import java.util.List;

/**
 * Created by John on 9/5/2017.
 */
public abstract class Algorithm {
    public abstract void addNode(ArrayDeque<Node> frontier, List<Node> children);
}
