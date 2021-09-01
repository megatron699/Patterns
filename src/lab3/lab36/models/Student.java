package lab3.lab36.models;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String lastName;
    private List<Subject> subjects = new ArrayList<>();
    private double average;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public Student() {
    }

    public Student(String lastName, ArrayList<Subject> subjects, double average) {
        this.lastName = lastName;
        this.subjects = subjects;
        this.average = average;
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    public void checkAverage() throws Exception {
        if (subjects.size() == 0) {
            throw new Exception("Оценок нет");
        }

        double average = subjects.stream().map(Subject::getMark).reduce(Integer::sum).get() / (double) subjects.size();
        if (average != this.average) {
            this.average = average;
        }
    }
}
