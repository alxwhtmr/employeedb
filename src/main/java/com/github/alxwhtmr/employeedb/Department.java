package com.github.alxwhtmr.employeedb;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created on 31.03.2015.
 */
public class Department {
	private String departmentName;
	private double minSalary;
	private double maxSalary;
	private ArrayList<Employee> employees;

	public Department(String departmentName, double minSalary, double maxSalary) {
		this.departmentName = departmentName;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		employees = new ArrayList<>();
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public double getMinSalary() {
		return minSalary;
	}

	public double getMaxSalary() {
		return maxSalary;
	}

	public double getSalaryIncrement() {
		return minSalary * 0.1;
	}

	public void initEmployees() {
		String firstNames[]  = Initializer.initStringsFromFile(Constants.FIRSTNAME_CATEGORY);
		String lastNames[]   = Initializer.initStringsFromFile(Constants.LASTNAME_CATEGORY);
		// Minimum persons in department is set to 5
		for (int i = 0; i < 5 + Constants.EMPLOYEE_RANGE; i++) {
			String firstName = firstNames[new Random().nextInt(firstNames.length)];
			String lastName = lastNames[new Random().nextInt(lastNames.length)];
			Employee employee = new Employee(firstName, lastName, this);
			employee.initHire();
			employee.initSalaries();
			employees.add(employee);
		}
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}
}
