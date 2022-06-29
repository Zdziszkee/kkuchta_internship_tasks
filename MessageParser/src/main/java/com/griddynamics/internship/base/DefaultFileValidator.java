package com.griddynamics.internship.base;

import com.griddynamics.internship.api.FileValidator;

import java.nio.file.Path;
import java.util.List;

public class DefaultFileValidator implements FileValidator {
    @Override
    public boolean isValid(Path file, List<String> lines){
        if (lines.size() != 2) {
            System.out.println("Invalid file format (wrong amount of lines!)");
            return false;
        }

        if (!file.getFileName()
                 .toString()
                 .endsWith(".txt")) {
            System.out.println("This file is not a txt file!");
            return false;
        }

        try {
            Integer.parseInt(lines.get(1));
        }
        catch (NumberFormatException e) {
            System.out.println("Number line does not contain number!");
            return false;
        }

        return true;
    }

}
