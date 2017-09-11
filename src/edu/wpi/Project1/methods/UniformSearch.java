package edu.wpi.Project1.methods;

import edu.wpi.Project1.util.Graph;
import edu.wpi.Project1.util.NewNode;

import java.util.*;

/**
 * Created by John on 9/11/2017.
 */
public class UniformSearch extends Algorithm {
    public UniformSearch(){
        this.printType = 1;
        this.name = "Uniform Search (Branch-and-bound)";
    }
    @Override
    public ArrayDeque<NewNode> add(ArrayDeque<NewNode> frontier, List<NewNode> children, Graph graph) {
        LinkedList<NewNode> sortedList = new LinkedList();
        for(NewNode child: children){
            frontier.addLast(child);
        }

       for(NewNode node : frontier){
            sortedList.add(node);
       }
       sortedList.sort(new Comparator<NewNode>() {
            @Override
            public int compare(NewNode o1, NewNode o2) {
                if (o1.getG() < o2.getG()){
                    return -1;
                }
                else if(o1.getG() > o2.getG()){
                    return 1;
                }
                else{
                    if(o2.getLetter() > o1.getLetter()){
                        return -1;
                    }
                    else if (o1.getLetter() > o2.getLetter()){
                        return 1;
                    }
                    else{
                        return 0;
                    }
                }
            }
        });
        frontier.clear();
        frontier.addAll(sortedList);

        return frontier;
    }
}
