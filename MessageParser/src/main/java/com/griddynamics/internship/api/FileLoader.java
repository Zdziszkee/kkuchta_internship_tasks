package com.griddynamics.internship.api;

import com.griddynamics.internship.base.exceptions.DefaultFileNotFoundException;
import com.griddynamics.internship.base.exceptions.WrongFileFormatException;

import java.nio.file.Path;

public interface FileLoader {
    Path load(String path) throws DefaultFileNotFoundException;
}
