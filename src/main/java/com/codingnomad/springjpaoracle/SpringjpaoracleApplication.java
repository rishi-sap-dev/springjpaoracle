package com.codingnomad.springjpaoracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.codingnomad.springjpaoracle.dao.EmployeeRepository;
import com.codingnomad.springjpaoracle.entities.Employee;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SpringjpaoracleApplication {

	static EmployeeRepository emprepository;

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringjpaoracleApplication.class, args);

		emprepository = ctx.getBean(EmployeeRepository.class);
		int selection;
		boolean validSel = true;
		
		try (Scanner input = new Scanner(System.in)) {
			while (validSel) {
				/***************************************************/
				System.out.println("Choose from these choices");
				System.out.println("-------------------------\n");
				System.out.println("0 - Quit");
				System.out.println("1 - Insert Employee");
				System.out.println("2 - Insert Multiple Employees");
				System.out.println("3 - Get Single Employee");
				System.out.println("4 - Update Employee");
				System.out.println("5 - Delete Student");
				System.out.println("6 - Custom Finder");
				System.out.println("7 - JPQL Example 1");
				System.out.println("8 - Native Example 1");
				//selection = input.nextInt();
				selection = 2;

				// loop
				switch (selection) {
				case 1:
					insertData();
					break;
				case 2:
					insertDataMulti();
					break;
				case 3:
					getStudent(0);
					break;
				case 4:
					updateData();
					break;
				case 5:
					deleteStudent();
					break;
				case 6:
					customMethodCity();
					break;
				case 7:
					customJPQL();
					break;
				case 8:
					customNative();
					break;						
				case 0:
					// Perform "quit" case.
					validSel = false;
					break;
				default:
					System.out.println("Invalid Selection, Please try again !");
					break;
				}
				// loop
			}
		}

		System.out.println("Thank you for using the application");

		ctx.close();
	}

	private static void insertData() {
		Employee emp1 = new Employee("Sunny", "Puri", "Darwin");
		Employee result = emprepository.save(emp1);
		System.out.println("Employee added with id :" + result.getId());
		System.out.println(result);
	}

	private static void insertDataMulti() {
		Employee emp1 = new Employee("DockerInsert", "Puri", "Adelaide");
		Employee emp2 = new Employee("DockerInsert", "Puri", "Adelaide");
		List<Employee> emps = List.of(emp1, emp2);
		Iterable<Employee> result = emprepository.saveAll(emps);
		result.forEach(emp -> {
			System.out.println("Employee added with id :" + emp.getId());
			System.out.println(result);
		});

	}

	private static Employee getStudent(int im_id) {
		int selectStudent = 0;
		if (im_id != 0)
			selectStudent = im_id;
		else {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter Student ID");
			selectStudent = input.nextInt();
		}
		try {
			Optional<Employee> findEmp = emprepository.findById(selectStudent);
			System.out.println(findEmp);
			if (findEmp.isPresent()) {
				return findEmp.get();
			} else {
				System.out.println("Employee not found");
				return null;
			}

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Employee not found");
			return null;
		}

	}

	public static void updateData() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Student ID to be updated");
		int selection = input.nextInt();
		Employee emp = getStudent(selection);
		if (emp != null) {
			emp.setEMP_CITY("Malaysia");
			emprepository.save(emp);

		} else {
			System.out.println("Employee not found");
		}
	}

	private static void deleteStudent() {
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter Employee ID to be deleted");
			int selection = input.nextInt();
			emprepository.deleteById(selection);
			System.out.println("Employee Deleted ");
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("System Error");

		}
	}
	private static void customMethodCity() {
		try {
			List<Employee> empList = emprepository.findByEmpCity("Darwin");
			
			empList.forEach(emp->{
				System.out.println(emp);
			});
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("System Error");
		}
	}
	private static void customJPQL() {
		try {
			List<Employee> empList = emprepository.findMyJPQlExample1("win", "Su");
			empList.forEach(emp->{
				System.out.println(emp);
			});
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("System Error");
		}
	}

	private static void customNative() {
		try {
			List<Employee> empList = emprepository.findMyNativeExample1("win", "Su");
		//	List<Employee> empList = emprepository.findMyNativeExample1("laid", "M");
			empList.forEach(emp->{
				System.out.println(emp);
			});		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("System Error");
		}
	}
}
