package com.griddynamics.internship.base;

import com.griddynamics.internship.api.FileReader;
import com.griddynamics.internship.api.FileValidator;
import com.griddynamics.internship.base.exceptions.WrongFileFormatException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class DefaultFileReader implements FileReader {
    private final FileValidator fileValidator;

    public DefaultFileReader(FileValidator fileValidator) {
        this.fileValidator = fileValidator;
    }

    @Override
    public List<String> read(Path path) throws WrongFileFormatException {
        try {
            List<String> lines = Files.readAllLines(path);
            if (!fileValidator.isValid(path,lines)) {
                System.out.println("File validation failed!");
                throw new WrongFileFormatException();
            }
            return lines;
        }
        catch (IOException exception) {
            System.out.println("Error occurred while trying to read lines from file.");
        }
        return Collections.emptyList();
    }
}
