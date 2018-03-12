package com.ex.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ex.pojos.Student;

public class StudentIO {
	
	final static String filename = "src/data/students.txt";
	
	
	public void writeStudent(Student student) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))){
			bw.write(student.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeAllStudents(List<Student> students)
	{
		File fold = new File(filename);
		fold.delete();
		File fnew = new File(filename);
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename,true)))
		{
			for(Student s : students)
			{
				bw.write(s.toString());
			}
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	public List<Student> readStudents(){
		List<Student> students = new ArrayList<Student>();
		try(BufferedReader br = new BufferedReader(new FileReader(filename))){
			String line = null;
			while((line=br.readLine()) != null) {
				String[] data = line.split(":");
				Student temp = new Student();
				temp.setName(data[0]);
				temp.setAge(Integer.parseInt(data[1]));
				students.add(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return students;
	}

}
