package me.squiddy.pa2;

public class PatientEntry {
    // a table entry that represents a patient
    private int rank;
    private String info;
    private double priority;

    public PatientEntry(int rank, Patient patient) {
        this.rank = rank;
        this.info = patient.toString();
        this.priority = patient.calculatePriority();
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }
}
