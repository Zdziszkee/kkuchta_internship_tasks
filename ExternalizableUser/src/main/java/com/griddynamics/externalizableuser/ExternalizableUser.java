package com.griddynamics.externalizableuser;

import com.griddynamics.externalizableuser.base.User;

import java.io.*;
import java.net.URL;

public class ExternalizableUser {

    public static void main(String[] args) {

        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        final URL url = loader.getResource("cache.txt");
        if (url == null) {
            System.out.println("Could not find default file!");
            return;
        }
        final File file = new File(url.getPath());
        final User user = new User();
        try (final FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(user);
        }
        catch (IOException e) {
            System.out.println("something went wrong writing to file");
        }
        try (final FileInputStream fileInputStream = new FileInputStream(file)) {
            final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            User readUser = (User) objectInputStream.readObject();
            System.out.println(readUser);
            byte[] bytes = fileInputStream.readAllBytes();
            System.out.println("byte array size: " + bytes.length);
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("something went wrong reading from file");
        }
    }

}
