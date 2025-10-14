package com.school;

import java.util.ArrayList;
import java.util.List;

public class RegistrationService {
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Staff> staffMembers;
    private List<Course> courses;
    private FileStorageService storageService;

    // File names for data persistence
    private final String STUDENTS_FILE = "students.txt";
    private final String TEACHERS_FILE = "teachers.txt";
    private final String STAFF_FILE = "staff.txt";
    private final String COURSES_FILE = "courses.txt";

    public RegistrationService(FileStorageService storageService) {
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.staffMembers = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.storageService = storageService;
    }

    // Registration methods
    public void registerStudent(String name, String gradeLevel) {
        Student student = new Student(name, gradeLevel);
        students.add(student);
        System.out.println("Student registered: " + student.getName() + " (ID: " + student.getId() + ")");
    }

    public void registerTeacher(String name, String subjectTaught) {
        Teacher teacher = new Teacher(name, subjectTaught);
        teachers.add(teacher);
        System.out.println("Teacher registered: " + teacher.getName() + " (ID: " + teacher.getId() + ")");
    }

    public void registerStaff(String name, String role) {
        Staff staff = new Staff(name, role);
        staffMembers.add(staff);
        System.out.println("Staff member registered: " + staff.getName() + " (ID: " + staff.getId() + ")");
    }

    public void createCourse(String courseName) {
        Course course = new Course(courseName);
        courses.add(course);
        System.out.println("Course created: " + course.getCourseName() + " (ID: C" + course.getCourseId() + ")");
    }

    // Getter methods
    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Staff> getStaffMembers() {
        return staffMembers;
    }

    public List<Course> getCourses() {
        return courses;
    }

    // Lookup methods
    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public Course findCourseById(int id) {
        for (Course course : courses) {
            if (course.getCourseId() == id) {
                return course;
            }
        }
        return null;
    }

    // Get all people method
    public List<Person> getAllPeople() {
        List<Person> allPeople = new ArrayList<>();
        allPeople.addAll(students);
        allPeople.addAll(teachers);
        allPeople.addAll(staffMembers);
        return allPeople;
    }

    // Save all registrations to files
    public void saveAllRegistrations() {
        storageService.saveData(students, STUDENTS_FILE);
        storageService.saveData(teachers, TEACHERS_FILE);
        storageService.saveData(staffMembers, STAFF_FILE);
        storageService.saveData(courses, COURSES_FILE);
        System.out.println("All registration data saved successfully.");
    }
}