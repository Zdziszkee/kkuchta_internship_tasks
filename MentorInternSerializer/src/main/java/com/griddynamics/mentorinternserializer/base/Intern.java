package com.griddynamics.mentorinternserializer.base;

import java.io.*;

public class Intern implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String name;

    private final transient String password;

    private Mentor mentor;

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
    }

    @Serial
    private void writeObject(ObjectOutputStream outputStream) throws IOException {

        outputStream.defaultWriteObject();
    }

}
