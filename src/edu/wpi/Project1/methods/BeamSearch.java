package edu.wpi.Project1.methods;

import edu.wpi.Project1.util.Graph;
import edu.wpi.Project1.util.NewNode;
import sun.awt.image.ImageWatched;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by John on 9/11/2017.
 */
public class BeamSearch extends Algorithm {
    private int k;
    public BeamSearch(int k){
        this.k = k;
        this.printType = 2;
        this.name = "Beam search (use w = 2)";
    }

    @Override
    public ArrayDeque<NewNode> add(ArrayDeque<NewNode> frontier, List<NewNode> children, Graph graph) {
        LinkedList<NewNode> sortedList = new LinkedList(children);
        sortedList.addAll(frontier);

        sortedList.sort(new Comparator<NewNode>() {
            @Override
            public int compare(NewNode o1, NewNode o2) {
                if(o2.getPath().size() < o1.getPath().size()){
                    return 1;
                }
                else if((o2.getPath().size() > o1.getPath().size())){
                    return -1;
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

        boolean beamNodes;

        if(sortedList.size() > 0){
            beamNodes = true;
            int pathSize = sortedList.getFirst().getPath().size();
            for(NewNode node : sortedList){
                if(node.getPath().size() != pathSize){
                    beamNodes = false;
                    break;
                }
            }
        }
        else{
            beamNodes = false;
        }

        frontier.clear();

        if(beamNodes) {
            int max = (sortedList.size() < this.k) ? sortedList.size() : this.k;

            sortedList.sort(new Comparator<NewNode>() {
                @Override
                public int compare(NewNode o1, NewNode o2) {
                    if(o2.getH() > o1.getH()){
                        return -1;
                    }
                    else if(o2.getH() < o1.getH()){
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
            List<NewNode> newList = (List<NewNode>) sortedList.subList(0, max);
            newList.sort(new Comparator<NewNode>() {
                             @Override
                             public int compare(NewNode o1, NewNode o2) {
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
                         });
            frontier.addAll(newList);
        }
        else{
            frontier.addAll(sortedList);
        }


        return frontier;
    }
}
