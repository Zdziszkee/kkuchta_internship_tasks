package com.griddynamics.internship.base;

import com.griddynamics.internship.api.FileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class DefaultFileReader implements FileReader {
    @Override
    public List<String> read(Path file) {

        try {
            return Files.readAllLines(file);
        }
        catch (IOException e) {
            System.out.println("Could not read txt file!");
            return Collections.emptyList();
        }

    }
}
