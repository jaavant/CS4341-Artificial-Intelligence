package edu.wpi.Project1.util;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * Created by John on 8/29/2017.
 */
public class Graph {
    public HashMap<Character,Double> map;
    HashSet<Character> explored;
    Double[][] adjM;

    public Graph(File txtFile){
        this.explored = new HashSet<>();
        this.map = new HashMap<>();
        this.adjM = new Double[26][26];
        for(int i = 0; i < 26; i++){
            for(int k = 0; k < 26; k++){
                this.adjM[i][k] = null;
            }
        }
        parseTxt(txtFile);
    }

    public boolean wasExplored(Character ch){
        return explored.contains(ch);
    }

    public void setExplored(Character ch){
        explored.add(ch);
    }

    public Double getHeur(Character ltr){
        return map.get(ltr);
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
        map.put('G',0.0);
    }

    /**
     * Process a line of text from a graph.txt file
     * @param line
     */
    private void processLine(String line){
        String[] words = line.split(" ");
        if(words.length == 3){
            addWeight(words[0], words[1], Double.parseDouble(words[2]));
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
    private void addWeight(String source, String target, double weight){
        int x = source.charAt(0) - 'A';
        int y = target.charAt(0) - 'A';
        adjM[x][y] = weight;
        adjM[y][x] = weight;
    }

    public Double getWeight(Character source, Character target){
        int x = source - 'A';
        int y = target - 'A';
        return adjM[x][y];
    }

    /**
     * Adds heuristic value to goal node, to a node
     * @param source
     * @param heur
     */
    private void addHeur(String source, double heur){
        map.put(source.charAt(0), heur);
    }
}
