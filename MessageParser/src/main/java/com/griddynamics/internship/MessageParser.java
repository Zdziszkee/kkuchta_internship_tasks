package com.griddynamics.internship;

import com.griddynamics.internship.api.FileLoader;
import com.griddynamics.internship.api.FileReader;
import com.griddynamics.internship.api.InputFormatter;
import com.griddynamics.internship.base.DefaultFileLoader;
import com.griddynamics.internship.base.DefaultFileReader;
import com.griddynamics.internship.base.DefaultFileValidator;
import com.griddynamics.internship.base.DefaultInputFormatter;
import com.griddynamics.internship.base.exceptions.DefaultFileNotFoundException;
import com.griddynamics.internship.base.exceptions.WrongFileFormatException;

import java.nio.file.Path;
import java.util.List;

public class MessageParser {
    private final FileLoader fileLoader;
    private final FileReader fileReader;

    private final InputFormatter inputFormatter;

    public MessageParser(FileLoader fileLoader, FileReader fileReader, InputFormatter inputFormatter) {
        this.fileLoader = fileLoader;
        this.fileReader = fileReader;
        this.inputFormatter = inputFormatter;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("You did not provide path for file so sample file will be used instead.");
        }
        final String path = (args.length == 0) ? "" : args[0];
        MessageParser messageParser = new MessageParser(new DefaultFileLoader(), new DefaultFileReader(new DefaultFileValidator()),
                new DefaultInputFormatter());

        messageParser.run(path);

    }

    public void run(String path) {
        try {
            final Path file = fileLoader.load(path);
            final List<String> lines = fileReader.read(file);
            List<String> formattedLines = inputFormatter.format(lines.get(0), Integer.parseInt(lines.get(1)));
            formattedLines.forEach(System.out::println);
        }
        catch (DefaultFileNotFoundException exception) {
            System.out.println("Default file is corrupted");
        }
        catch (WrongFileFormatException exception) {
            System.out.println("Provided file is not a txt file!");
        }
    }
}