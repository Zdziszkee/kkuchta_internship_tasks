package com.griddynamics.internship.api;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileReader {
    List<String> read(Path file);
}
