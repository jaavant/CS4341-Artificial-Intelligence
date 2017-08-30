package edu.wpi.Project1.util;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Created by John on 8/29/2017.
 */
public class Graph {
    HashMap<String,Node> map;

    public Graph(File txtFile){
        this.map = new HashMap<>();

    }

    /**
     * Parses a text file to create a graph
     * @param file graph.txt
     */
    private void parseTxt(File file){
        try (Stream<String> lines = Files.lines(file.toPath(), Charset.defaultCharset())) {
            lines.forEachOrdered(line -> processLine(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Process a line of text from a graph.txt file
     * @param line
     */
    private void processLine(String line){
        String[] words = line.split(" ");
        if(words.length == 3){
            addEdge(words[0], words[1], Double.parseDouble(words[2]));
        }
        else if(words.length == 2){
            addHeur(words[0], Double.parseDouble(words[1]));
        }
    }

    /**
     * Adds an outgoing edge to a node
     * @param source Outgoing node
     * @param target Incoming node
     * @param weight Weight of edge
     */
    private void addEdge(String source, String target, double weight){
        if(!map.containsKey(source)){
            map.put(source, new Node(source));
        }

        if(!map.containsKey(target)){
            map.put(source, new Node(target));
        }

        map.get(source).addEdge(map.get(target), weight);
    }

    /**
     * Adds heurtisc value to goal node, to a node
     * @param source
     * @param heur
     */
    private void addHeur(String source, double heur){
        map.get(source).addHeur(heur);
    }
}
