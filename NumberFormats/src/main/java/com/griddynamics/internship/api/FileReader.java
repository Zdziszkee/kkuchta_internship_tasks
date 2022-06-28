package com.griddynamics.internship.api;

import com.griddynamics.internship.base.exceptions.InvalidFileFormatException;
import com.griddynamics.internship.util.Pair;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.TreeMap;

public interface FileReader {
    TreeMap<String,Double> read(Path file) throws IOException;
}
