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
        File graphTxt = new File(args[0]);
        Graph graph = new Graph(graphTxt);

        search(graph, new DepthFirstSearch());
        search(graph, new BreadthFirstSearch());
        search(graph, new DepthLimitedSearch(2));
        search(graph, new IterativeDeepeningSearch());
        search(graph, new UniformSearch());
        search(graph, new GreedySearch());
        search(graph, new AStarSearch());
        search(graph, new BeamSearch(2));
        search(graph, new HillClimbing());
    }

    public static void search(Graph graph, Algorithm algo){
        System.out.println("");
        LinkedList path = new LinkedList();
        path.add('S');
        NewNode initNode = new NewNode(0,graph.getHeur('S'),0,0,'S',path);
        System.out.println(algo.name);
        System.out.println("    Expanded  Queue");
        general_search(graph,algo,initNode);
    }

    public static void general_search(Graph graph, Algorithm algo, NewNode initNode){
        ArrayDeque<NewNode> frontier = new ArrayDeque<>();
        frontier.add(initNode);
        do{
            printFrontier(algo.printType, frontier);
            NewNode node = frontier.pollFirst();
            if(node.getLetter() == 'G'){
                System.out.println("        goal reached!");
                return;
            }
            graph.setExplored(node.getLetter());
            ArrayList<NewNode> openedNodes = node.getChildren(graph);
            frontier = algo.add(frontier,openedNodes,graph);
        }while(!frontier.isEmpty());
        System.out.println("	failure to find path between S and G");
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
