package com.griddynamics.internship.api;

import java.nio.file.Path;
import java.util.List;

public interface FileValidator {

    boolean isValid(Path file, List<String> lines);
}
