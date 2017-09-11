package edu.wpi.Project1.methods;

import edu.wpi.Project1.util.Graph;
import edu.wpi.Project1.util.NewNode;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by John on 9/11/2017.
 */
public class AStarSearch extends Algorithm {
    public AStarSearch(){
        this.printType = 3;
        this.name = "A*";
    }
    @Override
    public ArrayDeque<NewNode> add(ArrayDeque<NewNode> frontier, List<NewNode> children, Graph graph) {
        LinkedList<NewNode> sortedList = new LinkedList();
        for(NewNode child: children){
            boolean flag = true;
            for(NewNode node : frontier){
                if(node.getPath().getFirst().equals(child.getPath().getFirst()) && node.getPath().getLast().equals(child.getPath().getLast())){
                    if(node.getF() > child.getF()){
                        frontier.remove(node);
                    }
                    else{
                        flag = false;
                    }
                }
            }
            if(flag){
                frontier.addLast(child);
            }
        }

        for(NewNode node : frontier){
            sortedList.add(node);
        }
        sortedList.sort(new Comparator<NewNode>() {
            @Override
            public int compare(NewNode o1, NewNode o2) {
                if (o1.getF() < o2.getF()){
                    return -1;
                }
                else if(o1.getF() > o2.getF()){
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

