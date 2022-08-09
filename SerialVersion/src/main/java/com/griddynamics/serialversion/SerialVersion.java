package com.griddynamics.serialversion;

import com.griddynamics.serialversion.base.Client;

import java.io.*;

public class SerialVersion {

    public static void main(String[] args) {

        final File file = new File("/Users/kkuchta/IdeaProjects/kkuchta_internship_tasks/SerialVersion/src/main/resources/cache.txt");
        final Client client = new Client("NAME", "PASSWORD",  1.0);
        /*
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(client);
            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch (IOException e) {
            System.out.println("something went wrong writing");
        }
         */
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            final Client readClient = (Client) objectInputStream.readObject();
            System.out.println(readClient);
            objectInputStream.close();
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("something went wrong reading");
        }
    }

}
