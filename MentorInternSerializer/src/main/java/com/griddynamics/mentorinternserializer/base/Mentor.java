package com.griddynamics.mentorinternserializer.base;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mentor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String name;

    private final transient String password;

    private final List<Intern> interns = new ArrayList<>();

    public Mentor(String name, String password) {

        this.name = name;
        this.password = password;
    }

    public String getName() {

        return name;
    }

    public String getPassword() {

        return password;
    }

    public List<Intern> getInterns() {

        return Collections.unmodifiableList(interns);
    }

    public void addIntern(String name, String password) {

        interns.add(new Intern(name, password, this));
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
