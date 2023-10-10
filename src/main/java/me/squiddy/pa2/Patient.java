package me.squiddy.pa2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Patient {
    // contains the information and priority of a patient
    private String name;
    private int age;
    private LocalDate registrationDate, deathDate;

    public Patient(String name, int age, LocalDate registrationDate, LocalDate deathDate) {
        this.name = name;
        this.age = age;
        this.registrationDate = registrationDate;
        this.deathDate = deathDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public double calculatePriority() {
        // calculates the priority of a patient
        // uses their age, days they have left without the organ and how long they have been waiting for
        LocalDate currentDate = LocalDate.now();
        int daysWaited = (int) ChronoUnit.DAYS.between(registrationDate, currentDate);
        int daysLeft = (int) ChronoUnit.DAYS.between(currentDate, deathDate);
        return (double) daysWaited/(daysLeft+1)-age/75.0;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                ", Age: " + age +
                ", Registration Date: " + registrationDate +
                ", Death Date: " + deathDate;
    }
}
