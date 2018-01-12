package z7z8.z7z8Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentDTO implements Comparable<StudentDTO> {

	private String name;
	private int age;

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

	@Override
	public int compareTo(StudentDTO o) {
		// TODO Auto-generated method stub
		return this.age - o.getAge();
	}

	public static void main(String args[]) {
		List<StudentDTO> studentList = new ArrayList();

		StudentDTO s1 = new StudentDTO();

		s1.setName("yuanyuan");

		s1.setAge(12);

		studentList.add(s1);

		StudentDTO s2 = new StudentDTO();

		s2.setName("lily");

		s2.setAge(23);
		studentList.add(s2);
		
		StudentDTO s3 = new StudentDTO();

		s3.setName("cash");

		s3.setAge(13);

		studentList.add(s3);

		Collections.sort(studentList); // 升序
		for(StudentDTO dto:studentList){
			System.out.println(dto.getName());
		}
		
		System.out.println("------------------");
		
		Collections.reverse(studentList); // 降序
		for(StudentDTO dto:studentList){
			System.out.println(dto.getName());
		}
	}

}
