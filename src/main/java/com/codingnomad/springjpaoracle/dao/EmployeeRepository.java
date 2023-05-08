package com.codingnomad.springjpaoracle.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.codingnomad.springjpaoracle.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	List<Employee> findByEmpCity(String name);

	// https://stackoverflow.com/questions/25362540/like-query-in-spring-jparepository
	@Query(value = "SELECT emp FROM Employee emp WHERE emp.empCity LIKE %:cityname% and emp.firstName LIKE :fname%")
	List<Employee> findMyJPQlExample1(@Param("cityname") String cname, @Param("fname") String fname);

	//https://stackoverflow.com/questions/66071165/how-to-pass-parameters-in-a-native-query-jpa
	@Query(nativeQuery = true, value = "SELECT *" + " FROM Employees"
			+ " WHERE EMP_CITY LIKE %:cityname% and EMP_FIRST_NAME LIKE :fname%")
	List<Employee> findMyNativeExample1(@Param("cityname") String cname, @Param("fname") String fname);

}
