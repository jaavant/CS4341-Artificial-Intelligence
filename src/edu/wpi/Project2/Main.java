package edu.wpi.Project2;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchService;

/**
 * Created by John on 9/26/2017.
 */
public class Main {
    public static void main(String args[]){
        Path dir = Paths.get(".");
        try {
            new Watcher(dir).processEvents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
