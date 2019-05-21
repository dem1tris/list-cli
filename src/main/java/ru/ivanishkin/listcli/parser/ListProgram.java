package ru.ivanishkin.listcli.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class ListProgram {

    public static void main(String[] args) {
        File config = new File(".ignore");

        Set<File> ignoredPaths;
        try {
            ignoredPaths = Parser.parseFrom(new FileReader(config));
        } catch (FileNotFoundException e) {
            System.err.println("File `.ignored` not found in working directory (" + config.getAbsolutePath() + ")");
            ignoredPaths = Collections.emptySet();
        }

        Deque<File> stack = new ArrayDeque<>();
        stack.push(new File("."));
        while (!stack.isEmpty()) {
            File file = stack.pop();
            if (file.isFile()) {
                System.out.println(file.getPath().replace("." + File.separator, ""));
            } else {
                File dir = file;
                File[] files = dir.listFiles();
                Set<File> finalIgnoredPaths = ignoredPaths;
                if (files != null) {
                    Arrays.stream(files)
                            .filter(f -> !finalIgnoredPaths.contains(f))
                            .forEach(stack::push);
                }
            }
        }
    }
}
