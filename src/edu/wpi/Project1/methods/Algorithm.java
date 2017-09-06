package edu.wpi.Project1.methods;

import edu.wpi.Project1.util.Node;
import edu.wpi.Project1.util.Tuple;

import java.util.AbstractCollection;
import java.util.ArrayDeque;
import java.util.List;

/**
 * Created by John on 9/5/2017.
 */
public abstract class Algorithm {
    public abstract ArrayDeque<Tuple> add(ArrayDeque<Tuple> frontier, List<Tuple> children);
}
