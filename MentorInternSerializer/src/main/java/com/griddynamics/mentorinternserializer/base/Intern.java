package com.griddynamics.mentorinternserializer.base;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Intern implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    private final String name;

    private final transient String password;

    private transient Mentor mentor;


    Intern(String name, String password, Mentor mentor) {

        this.name = name;
        this.password = password;
        this.mentor = mentor;
    }


    public String getName() {

        return name;
    }

    public String getPassword() {

        return password;
    }

    public Mentor getMentor() {

        return mentor;
    }


    @Serial
    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {

        inputStream.defaultReadObject();
        final String mentorName = (String) inputStream.readObject();

        this.mentor = new Mentor(mentorName, null);
        List<String> interns = (List<String>) inputStream.readObject();
        interns.forEach(intern -> mentor.addIntern(intern, null));


    }


    @Serial
    private void writeObject(ObjectOutputStream outputStream) throws IOException {

        outputStream.defaultWriteObject();
        outputStream.writeObject(mentor.getName());
        outputStream.writeObject(mentor.getInterns().stream().map(intern -> intern.name).collect(Collectors.toList()));

    }

}
