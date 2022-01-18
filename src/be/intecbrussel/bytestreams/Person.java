package be.intecbrussel.bytestreams;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.Duration;

public class Person implements Serializable {
    public static final long serialVersionUID = 2;

    private String name;

    private String mood;
    private transient Duration timeLoggedOff = Duration.ZERO;

    public Person(String name, String mood) {
        this.name = name;
        this.mood = mood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public Duration getTimeLoggedOff() {
        return timeLoggedOff;
    }

    public void setTimeLoggedOff(Duration timeLoggedOff) {
        this.timeLoggedOff = timeLoggedOff;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        timeLoggedOff = Duration.ZERO;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", mood='" + mood + '\'' +
                ", timeLoggedOn=" + timeLoggedOff +
                '}';
    }
}
