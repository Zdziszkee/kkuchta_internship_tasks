package com.griddynamics.internship.countriesstatistics.base;

import com.griddynamics.internship.countriesstatistics.api.FileLoader;
import com.griddynamics.internship.countriesstatistics.base.exceptions.DefaultFileNotFoundException;
import com.griddynamics.internship.countriesstatistics.base.exceptions.WrongFileFormatException;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DefaultFileLoader implements FileLoader {
    @Override
    public Path load(String path) throws DefaultFileNotFoundException, WrongFileFormatException {
        if (path == null) {
            System.out.println("You did not provide path for file so sample file will be used instead.");
        }

        ClassLoader loader = Thread.currentThread()
                                   .getContextClassLoader();
        URL defaultFileURL = loader.getResource("countries-data.csv");

        if (defaultFileURL == null) {
            System.out.println("Default txt file could not be find!");
            throw new DefaultFileNotFoundException();
        }
        final String file = path == null || path.isEmpty() ? defaultFileURL.getPath() : path;


        final Path targetFile = Paths.get(file);

        if (!targetFile.getFileName()
                 .toString()
                 .endsWith(".csv")) {
            System.out.println("This file is not a .csv file!");
            throw new WrongFileFormatException();
        }

        return targetFile;
    }
}
