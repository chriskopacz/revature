package com.ex.run;

import java.util.List;

import com.ex.io.StudentIO;
import com.ex.pojos.Student;

public class Main {

	public static void main(String[] args) {
		
		StudentIO io = new StudentIO();
		//Student a = new Student("elite", 1337);
		//io.writeStudent(a);
		
		
		
		List<Student> students = io.readStudents();
		//students.add(a);
		for(Student s : students) {
			System.out.println(s.getName() + " is " + s.getAge() + " years old.");
			//io.writeStudent(s);
		}
		io.writeAllStudents(students);
		System.out.println("students written to file");
		
		
	}
	
	static void getNewStudent() {
		//read in from console
	}

}
