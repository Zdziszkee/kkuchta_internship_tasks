package com.griddynamics.internship.api;

import com.griddynamics.internship.base.exceptions.InvalidFileFormatException;
import com.griddynamics.internship.util.Pair;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileReader {
    List<Pair<String,Double>> read(Path file) throws IOException;
}
