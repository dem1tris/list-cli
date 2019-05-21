package ru.ivanishkin.listcli.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.Reader;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Parser {
    public static Set<File> parseFrom(Reader reader) {
        BufferedReader r = new BufferedReader(reader);

        return r.lines()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .filter(s -> !s.startsWith("#"))
                .map(s -> "." + File.separator + s)
                .map(File::new)
                .collect(Collectors.toSet());
    }
}
