package com.school;

import java.util.List;

public class Main {

    public static void displaySchoolDirectory(RegistrationService regService) {
        System.out.println("\n--- School Directory ---");
        List<Person> people = regService.getAllPeople();
        if (people.isEmpty()) {
            System.out.println("No people in the directory.");
            return;
        }
        for (Person person : people) {
            person.displayDetails();
        }
    }

    public static void main(String[] args) {
        System.out.println("--- School System (Registration Service Demo) ---");

        // --- Setup Services ---
        FileStorageService storageService = new FileStorageService();
        RegistrationService registrationService = new RegistrationService(storageService);
        AttendanceService attendanceService = new AttendanceService(storageService, registrationService);

        // --- Data Setup: Register Students, Teachers, Staff, and Courses ---
        registrationService.registerStudent("Alice Wonderland", "Grade 10");
        registrationService.registerStudent("Bob The Builder", "Grade 9");
        registrationService.registerStudent("Charlie Chaplin", "Grade 10");

        registrationService.registerTeacher("Dr. Emily Carter", "Physics");
        registrationService.registerTeacher("Prof. John Smith", "Mathematics");

        registrationService.registerStaff("Jane Doe", "Librarian");
        registrationService.registerStaff("Mike Johnson", "Administrator");

        registrationService.createCourse("Intro to Programming");
        registrationService.createCourse("Data Structures");

        // Display school directory using RegistrationService
        displaySchoolDirectory(registrationService);

        System.out.println("\n\n--- Marking Attendance (Overloaded Methods) ---");
        // Get students and courses for attendance marking
        List<Student> students = registrationService.getStudents();
        List<Course> courses = registrationService.getCourses();
        
        // 1. Mark attendance using Student and Course objects
        attendanceService.markAttendance(students.get(0), courses.get(0), "Present");
        attendanceService.markAttendance(students.get(1), courses.get(0), "Absent");

        // 2. Mark attendance using studentId and courseId (uses RegistrationService for lookup)
        // Alice (ID 1) in Data Structures (ID C102)
        attendanceService.markAttendance(students.get(0).getId(), courses.get(1).getCourseId(), "Present");
        // Charlie (ID 3) in Intro to Programming (ID C101)
        attendanceService.markAttendance(students.get(2).getId(), courses.get(0).getCourseId(), "Late");


        System.out.println("\n\n--- Querying Attendance (Overloaded Methods) ---");
        // 1. Display full attendance log
        attendanceService.displayAttendanceLog();

        // 2. Display attendance for a specific student (Alice)
        attendanceService.displayAttendanceLog(students.get(0));

        // 3. Display attendance for a specific course (Intro to Programming)
        attendanceService.displayAttendanceLog(courses.get(0));

        // --- Saving All Data ---
        System.out.println("\n\n--- Saving All Data ---");
        registrationService.saveAllRegistrations(); // Save students, teachers, staff, and courses
        attendanceService.saveAttendanceData(); // Save attendance log

        System.out.println("\nPart 9: RegistrationService Integration Complete.");
    }
}