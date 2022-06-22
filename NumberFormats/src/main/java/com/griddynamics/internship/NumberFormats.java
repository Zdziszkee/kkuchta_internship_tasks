package com.griddynamics.internship;

import com.griddynamics.internship.api.FileLoader;
import com.griddynamics.internship.api.FilePrinter;
import com.griddynamics.internship.api.FileReader;
import com.griddynamics.internship.base.DefaultFileLoader;
import com.griddynamics.internship.base.DefaultFilePrinter;
import com.griddynamics.internship.base.DefaultFileReader;
import com.griddynamics.internship.base.exceptions.DefaultFileNotFoundException;
import com.griddynamics.internship.util.Pair;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class NumberFormats {
    private final FileLoader fileLoader;
    private final FilePrinter filePrinter;
    private final FileReader fileReader;

    public NumberFormats(FileLoader fileLoader, FilePrinter filePrinter, FileReader fileReader) {
        this.fileLoader = fileLoader;
        this.filePrinter = filePrinter;
        this.fileReader = fileReader;
    }

    public static void main(String[] args) {
        final String path = args.length < 1 ? "" : args[1];

        final NumberFormats numberFormats = new NumberFormats(new DefaultFileLoader(), new DefaultFilePrinter(), new DefaultFileReader());
        numberFormats.run(path);

    }

    public void run(String path) {
        try {
            final Path file = fileLoader.load(path);
            final List<Pair<String, Double>> input = fileReader.read(file);
            filePrinter.print(input);
        }
        catch (DefaultFileNotFoundException e) {
            System.out.println("Could not find default text file!");
        }
        catch (IOException e) {
            System.out.println("Could not read contents of given file!");
        }
    }
}