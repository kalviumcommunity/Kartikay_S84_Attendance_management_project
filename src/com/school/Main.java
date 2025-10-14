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

        registrationService.createCourse("Intro to Programming", 2);
        registrationService.createCourse("Data Structures", 3);

        // Display school directory using RegistrationService
        displaySchoolDirectory(registrationService);

        System.out.println("\n\n--- Course Enrollment ---");
        // Get students and courses for enrollment
        List<Student> students = registrationService.getStudents();
        List<Course> courses = registrationService.getCourses();
        
        // Enroll students in courses
        registrationService.enrollStudentInCourse(students.get(0), courses.get(0)); // Alice in Intro to Programming
        registrationService.enrollStudentInCourse(students.get(1), courses.get(0)); // Bob in Intro to Programming
        registrationService.enrollStudentInCourse(students.get(2), courses.get(0)); // Charlie in Intro to Programming (should fail - capacity exceeded)
        
        registrationService.enrollStudentInCourse(students.get(0), courses.get(1)); // Alice in Data Structures
        registrationService.enrollStudentInCourse(students.get(2), courses.get(1)); // Charlie in Data Structures
        
        System.out.println("\n--- Course Details After Enrollment ---");
        for (Course course : courses) {
            course.displayDetails();
        }

        System.out.println("\n\n--- Marking Attendance (Overloaded Methods) ---");
        // 1. Mark attendance using Student and Course objects (for enrolled students)
        attendanceService.markAttendance(students.get(0), courses.get(0), "Present");
        attendanceService.markAttendance(students.get(1), courses.get(0), "Absent");

        // 2. Mark attendance using studentId and courseId (uses RegistrationService for lookup)
        // Alice (ID 1) in Data Structures (ID C102)
        attendanceService.markAttendance(students.get(0).getId(), courses.get(1).getCourseId(), "Present");
        // Charlie (ID 3) in Data Structures (ID C102) - Charlie is enrolled in this course
        attendanceService.markAttendance(students.get(2).getId(), courses.get(1).getCourseId(), "Late");


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

        System.out.println("\nPart 10: Course Capacity and Enrollment Complete.");
    }
}