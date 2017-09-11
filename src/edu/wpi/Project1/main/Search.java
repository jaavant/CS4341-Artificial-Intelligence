package edu.wpi.Project1.main;

import edu.wpi.Project1.methods.*;
import edu.wpi.Project1.util.Graph;
import edu.wpi.Project1.util.NewNode;

import java.io.File;
import java.util.*;

/**
 * Created by John on 8/30/2017.
 */
public class Search {
    public static void main(String[] args){
        File graphTxt = new File("C:\\Users\\John\\IdeaProjects\\CS4341\\resources\\graph.txt");
        Graph graph = new Graph(graphTxt);

        search(graph, new BeamSearch(2));
    }

    public static void search(Graph graph, Algorithm algo){
        ArrayDeque<NewNode> frontier = new ArrayDeque<>();
        NewNode node;
        ArrayList<NewNode> openedNodes;
        LinkedList path = new LinkedList();
        path.add('S');
        frontier.add(new NewNode(0,graph.getHeur('S'),0,0,'S',path));
        System.out.println(algo.name);
        System.out.println("    Expanded  Queue");
        do{
            printFrontier(algo.printType, frontier);
            node = frontier.pollFirst();
            if(node.getLetter() == 'G'){
                System.out.println("        goal reached!");
                return;
            }
            graph.setExplored(node.getLetter());
            openedNodes = node.getChildren(graph);
            frontier = algo.add(frontier,openedNodes,graph);
        }while(!frontier.isEmpty());
        return;
    }

    /**
     * 0 = print nothing
     * 1 = print weight
     * 2 = print heur
     * 3 = print heur + weight
     */
    public static void printFrontier(int print, ArrayDeque<NewNode> frontier){
        StringBuilder word = new StringBuilder("        " + frontier.getFirst().getLetter() + "     ");
        word.append("[");
        Iterator<NewNode> iter = frontier.iterator();

        while(iter.hasNext()){
            word.append(iter.next().toString(print));

            if(iter.hasNext()){
                word.append(" ");
            }
        }
        word.append("]");
        System.out.println(word);
    }

}
