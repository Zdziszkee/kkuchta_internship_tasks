package com.griddynamics.internship.countriesstatistics.api;

import com.griddynamics.internship.countriesstatistics.base.exceptions.DefaultFileNotFoundException;
import com.griddynamics.internship.countriesstatistics.base.exceptions.WrongFileFormatException;

import java.nio.file.Path;

public interface FileLoader {
    Path load(String path) throws DefaultFileNotFoundException, WrongFileFormatException;
}
