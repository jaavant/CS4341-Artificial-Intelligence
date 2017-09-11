package edu.wpi.Project1.methods;

import edu.wpi.Project1.util.Graph;
import edu.wpi.Project1.util.NewNode;

import java.util.ArrayDeque;
import java.util.List;

/**
 * Created by John on 9/11/2017.
 */
public class DepthLimitedSearch extends Algorithm{
    public int depth;

    public DepthLimitedSearch(int depth){
        this.printType = 0;
        this.name = "Depth-limited search (depth-limit = 2)";
        this.depth = depth;
    }
    @Override
    public ArrayDeque<NewNode> add(ArrayDeque<NewNode> frontier, List<NewNode> children, Graph graph) {
        NewNode child;
        for(int i = children.size() - 1; i >= 0; i--){
            child = children.get(i);
            if(child.getDepth() > this.depth){
                continue;
            }
            frontier.addFirst(child);
        }
        return frontier;
    }
}