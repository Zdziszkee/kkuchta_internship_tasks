package com.griddynamics.internship.api;

import com.griddynamics.internship.base.exceptions.WrongFileFormatException;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;

public interface FileReader {
    List<String> read(Path path) throws WrongFileFormatException;
}
