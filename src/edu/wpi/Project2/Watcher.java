package edu.wpi.Project2;

/**
 * Created by John on 9/26/2017.
 */
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;


/**
 * This code was derived from the Java 8 WatchService API Tutorial
 * https://howtodoinjava.com/java-8/java-8-watchservice-api-tutorial/
 */

public class Watcher {

    private final WatchService watcher;
    private final Map<WatchKey, Path> keys;

    /**
     * Creates a WatchService and registers the given directory
     */
    Watcher(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey, Path>();

        walkAndRegisterDirectories(dir);
    }

    /**
     * Register the given directory with the WatchService; This function will be called by FileVisitor
     */
    private void registerDirectory(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
        keys.put(key, dir);
    }

    /**
     * Register the given directory, and all its sub-directories, with the WatchService.
     */
    private void walkAndRegisterDirectories(final Path start) throws IOException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDirectory(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Process all events for keys queued to the watcher
     */
    void processEvents() {
        for (; ; ) {

            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                @SuppressWarnings("rawtypes")
                WatchEvent.Kind kind = event.kind();
                @SuppressWarnings("unchecked")
                Path name = ((WatchEvent<Path>) event).context();
                Path child = dir.resolve(name);

                //////////////////////////////////////////GAME LOGIC///////////////////////////////////////////
                if(child.getFileName().toString().contains("Cortana.go")){
                    BufferedReader bReader = null;
                    String move;
                    try {
                        bReader = new BufferedReader(new FileReader("move_file.txt"));
                        move = bReader.readLine();

                        if(move.trim().length() > 0) {
                            Game.getInstance().applyOpponentsMove(move.trim());
                        }
                        bReader.close();

                        BufferedWriter outStream= new BufferedWriter(new FileWriter("move_file.txt"));
                        outStream.write(Game.getInstance().getNextMove());
                        outStream.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(child.getFileName().toString().contains("end_game.txt")){
                    return;
                }
                ////////////////////////////////////////////////////////////////////////////////////////////////
            }

            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }
}

