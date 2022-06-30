package com.griddynamics.internship.countriesstatistics.api;

import com.griddynamics.internship.countriesstatistics.base.Country;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileReader {
    List<Country> read(Path path) throws IOException;
}
