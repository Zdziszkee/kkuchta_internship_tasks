package com.griddynamics.internship.base;

import com.griddynamics.internship.api.FileLoader;
import com.griddynamics.internship.base.exceptions.DefaultFileNotFoundException;
import com.griddynamics.internship.base.exceptions.WrongFileFormatException;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DefaultFileLoader implements FileLoader {
    @Override
    public Path loadFile(String path) throws DefaultFileNotFoundException, WrongFileFormatException {
        if (path==null) {
            System.out.println("You did not provide path for file so sample file will be used instead.");
        }

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL defaultFileURL = loader.getResource("test.txt");

        if (defaultFileURL == null) {
            System.out.println("Default txt file could not be find!");
            throw new DefaultFileNotFoundException();
        }
        final String file = path == null || path.isEmpty() ? defaultFileURL.getPath() : path;


        final Path textFile = Paths.get(file);

        if (!textFile.getFileName()
                     .toString()
                     .endsWith(".txt")) {
            System.out.println("This file is not a txt file!");
            throw new WrongFileFormatException();
        }
        return textFile;
    }
}
