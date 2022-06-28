package com.griddynamics.internship;

import com.griddynamics.internship.api.CharacterPrinter;
import com.griddynamics.internship.api.FileLoader;
import com.griddynamics.internship.api.FileReader;
import com.griddynamics.internship.api.LetterCounter;
import com.griddynamics.internship.base.DefaultCharacterPrinter;
import com.griddynamics.internship.base.DefaultFileLoader;
import com.griddynamics.internship.base.DefaultFileReader;
import com.griddynamics.internship.base.DefaultLetterCounter;
import com.griddynamics.internship.base.exceptions.DefaultFileNotFoundException;
import com.griddynamics.internship.base.exceptions.WrongFileFormatException;

import java.nio.file.Path;
import java.util.List;
import java.util.TreeMap;

public class LetterStatistics {
    private final FileLoader fileLoader;
    private final CharacterPrinter characterPrinter;
    private final FileReader fileReader;
    private final LetterCounter letterCounter;

    public LetterStatistics(FileLoader fileLoader, CharacterPrinter characterPrinter, FileReader fileReader, LetterCounter letterCounter) {
        this.fileLoader = fileLoader;
        this.characterPrinter = characterPrinter;
        this.fileReader = fileReader;
        this.letterCounter = letterCounter;
    }

    public static void main(String[] args) {
        LetterStatistics letterStatistics = new LetterStatistics(new DefaultFileLoader(), new DefaultCharacterPrinter(), new DefaultFileReader(),
                new DefaultLetterCounter());

        letterStatistics.run(args);


    }

    public void run(String[] args) {
        String path = args.length >= 1 ? args[0] : "";
        try {
            Path file = fileLoader.loadFile(path);
            List<String> lines = fileReader.read(file);
            TreeMap<Character, Integer> characterCount = letterCounter.count(lines);
            characterPrinter.print(characterCount, 10);
        }
        catch (DefaultFileNotFoundException exception) {
            System.out.println("Default file could not be found!");
        }
        catch (WrongFileFormatException exception) {
            System.out.println("Given file is not txt file!");
        }

    }

}