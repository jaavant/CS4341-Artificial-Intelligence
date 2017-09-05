package edu.wpi.Project1.main;

import edu.wpi.Project1.methods.Algorithm;
import edu.wpi.Project1.util.Graph;
import edu.wpi.Project1.util.Node;

import java.io.File;
import java.util.ArrayDeque;
import java.util.List;

/**
 * Created by John on 8/30/2017.
 */
public class Search {
    public static void main(String[] args){
        File graphTxt = new File(args[0]);
    }

    public static Node search(Graph graph, Algorithm algo){
        Node node;
        List children;
        ArrayDeque<Node> frontier = new ArrayDeque<>();
        frontier.add(graph.get("S"));

        do{
            node = frontier.pollFirst();
            if(node.getLtr().equalsIgnoreCase("G")){
                return node;
            }

            children = node.
        }while(!frontier.isEmpty());
        return null;
    }

}
