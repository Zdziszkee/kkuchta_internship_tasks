package com.griddynamics.internship.base;

import com.griddynamics.internship.api.FileLoader;
import com.griddynamics.internship.base.exceptions.DefaultFileNotFoundException;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DefaultFileLoader implements FileLoader {
    @Override
    public Path load(String path) throws DefaultFileNotFoundException {
        if (path == null || path.equals("")) {
            System.out.println("You did not provide path for file so sample file will be used instead.");
        }

        ClassLoader loader = Thread.currentThread()
                                   .getContextClassLoader();
        URL defaultFileURL = loader.getResource("test.txt");

        if (defaultFileURL == null) {
            System.out.println("Default txt file could not be find!");
            throw new DefaultFileNotFoundException();
        }
        final String file = (path == null || path.isEmpty()) ? defaultFileURL.getPath() : path;


        return Paths.get(file);
    }
}
