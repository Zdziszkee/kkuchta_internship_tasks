package com.griddynamics.internship;

import com.griddynamics.internship.api.*;
import com.griddynamics.internship.base.*;
import com.griddynamics.internship.base.exceptions.DefaultFileNotFoundException;
import com.griddynamics.internship.base.exceptions.WrongFileFormatException;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class LetterStatistics {
    private final FileLoader fileLoader;
    private final CharacterPrinter characterPrinter;
    private final FileReader fileReader;
    private final InputSorter inputSorter;
    private final LetterCounter letterCounter;

    public LetterStatistics(FileLoader fileLoader, CharacterPrinter characterPrinter, FileReader fileReader, InputSorter inputSorter,
                            LetterCounter letterCounter) {
        this.fileLoader = fileLoader;
        this.characterPrinter = characterPrinter;
        this.fileReader = fileReader;
        this.inputSorter = inputSorter;
        this.letterCounter = letterCounter;
    }

    public static void main(String[] args) {
        LetterStatistics letterStatistics = new LetterStatistics(new DefaultFileLoader(),
                                                                 new DefaultCharacterPrinter(),
                                                                 new DefaultFileReader(),
                                                                 new DefaultInputSorter(),
                                                                 new DefaultLetterCounter());

        letterStatistics.run(args);


    }

    public void run(String[] args) {
        String path = args.length >= 1 ? args[0] : "";
        try {
            Path file = fileLoader.loadFile(path);
            List<String> lines = fileReader.read(file);
            Map<Character, Integer> characterCount = letterCounter.count(lines);
            Map<Character, Integer> sortedCharacterCount = inputSorter.sort(characterCount);
            characterPrinter.print(sortedCharacterCount, 10);
        }
        catch (DefaultFileNotFoundException exception) {
            System.out.println("Default file could not be found!");
        }
        catch (WrongFileFormatException exception) {
            System.out.println("Given file is not txt file!");
        }

    }

}