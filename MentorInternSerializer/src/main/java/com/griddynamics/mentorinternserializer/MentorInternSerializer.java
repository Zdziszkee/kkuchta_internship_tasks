package com.griddynamics.mentorinternserializer;

import com.griddynamics.mentorinternserializer.base.Mentor;

import java.io.*;
import java.net.URL;

public class MentorInternSerializer {

    public static void main(String[] args) {

        final Mentor mentor = new Mentor("mentorName", "mentorPassword");
        mentor.addIntern("intern1Name", "intern1Password");
        mentor.addIntern("intern2Name", "intern2Password");
        mentor.addIntern("intern3Name", "intern3Password");
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        final URL url = loader.getResource("cache.txt");
        if (url == null) {
            System.out.println("Could not find default file!");
            return;
        }
        final File file = new File(url.getPath());
        try {
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(mentor);
                objectOutputStream.flush();
            }
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                final ObjectInputStream objectOutputStream = new ObjectInputStream(fileInputStream);
                final Mentor readMentor = (Mentor) objectOutputStream.readObject();
                System.out.println("Mentor name equality: " + readMentor.getName().equals(mentor.getName()));
                System.out.println("Mentor interns equality: " + readMentor.getInterns().equals(mentor.getInterns()));
                System.out.println("Mentor hashcodes equality: " + (readMentor.hashCode() == mentor.hashCode()));
                System.out.println("Intern mentor's hashcodes: ");
                readMentor.getInterns().forEach(intern -> System.out.println(intern.getMentor().hashCode()));
            }
            catch (ClassNotFoundException e) {
                System.out.println("Could not find class which should be used for deserialization");
            }
        }
        catch (IOException e) {
            System.out.println("Something went wrong reading the file!");
        }
    }

}
