package me.squiddy.pa2;

import java.util.Date;

public class Patient {
    // contains the information and priority of a patient
    private String name;
    private int age, organ;
    private Date registrationDate, deathDate;
    private double priority;

    public Patient(String name, int age, int organ, Date registrationDate, Date deathDate) {
        this.name = name;
        this.age = age;
        this.organ = organ;
        this.registrationDate = registrationDate;
        this.deathDate = deathDate;
        priority = calculatePriority();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrgan() {
        return organ;
    }

    public void setOrgan(int organ) {
        this.organ = organ;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        priority = calculatePriority();
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
        priority = calculatePriority();
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
        priority = calculatePriority();
    }

    public double getPriority() {
        return priority;
    }

    private double calculatePriority() {
        // calculates the priority of a patient using their age, death date and registration date
        return 0;
    }
}
