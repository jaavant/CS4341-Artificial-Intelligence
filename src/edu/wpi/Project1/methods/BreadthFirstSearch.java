package edu.wpi.Project1.methods;

import edu.wpi.Project1.util.Node;

import java.util.ArrayDeque;
import java.util.List;

/**
 * Created by John on 9/5/2017.
 */
public class BreadthFirstSearch extends Algorithm {
    @Override
    public void addNode(ArrayDeque<Node> frontier, List<Node> children) {
        for(Node child: children){
            frontier.addLast(child);
        }
    }
}
