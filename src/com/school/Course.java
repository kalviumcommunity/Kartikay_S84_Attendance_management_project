package com.school;


public class Course {
private static int courseCounter = 0;
private int courseId;
private String courseName;


public Course(String courseName) {
this.courseId = ++courseCounter;
this.courseName = courseName;
}


public int getCourseId() {
return courseId;
}


public String getCourseName() {
return courseName;
}


public void displayDetails() {
System.out.printf("Course ID: %d | Name: %s\n", courseId, courseName);
}
}