package com.school;

import java.util.ArrayList;
import java.util.List;

public class Course implements Storable {
    private static int nextCourseIdCounter = 101;

    private int courseId;       // Made private
    private String courseName;  // Made private
    private int capacity;
    private List<Student> enrolledStudents;

    // Constructor
    public Course(String courseName, int capacity) {
        this.courseId = nextCourseIdCounter++; // Auto-increment and assign ID
        this.courseName = courseName;          // Assign course name
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
    }

    // Getter for courseId
    public int getCourseId() {
        return courseId;
    }

    // Getter for courseName
    public String getCourseName() {
        return courseName;
    }

    // Getter for capacity
    public int getCapacity() {
        return capacity;
    }

    // Getter for enrolled students
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    // Getter for number of enrolled students
    public int getNumberOfEnrolledStudents() {
        return enrolledStudents.size();
    }

    // Method to add student to course
    public boolean addStudent(Student student) {
        if (capacity > enrolledStudents.size()) {
            enrolledStudents.add(student);
            return true;
        }
        return false;
    }

    public void displayDetails() {
        System.out.println("Course ID: C" + this.courseId + ", Name: " + this.courseName + 
                          ", Capacity: " + this.capacity + 
                          ", Enrolled: " + this.enrolledStudents.size());
    }

    @Override
    public String toDataString() {
        // Format: courseId,courseName,capacity
        return courseId + "," + courseName + "," + capacity;
    }
}