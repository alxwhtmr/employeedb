package com.github.alxwhtmr.employeedb;

import java.util.*;

/**
 * Created on 01.04.2015.
 */
public class EmployeeDB {
	private ArrayList<Department> departments;

	public void init() {
		initDepartments();
	}

	public void outputDbToConsole() {
		System.out.println("RANDOMLY GENERATED EMPLOYEE DATABASE");
		for (Department department : departments) {
			System.out.println("---" + department.getDepartmentName() + "---");
			for (Employee employee : department.getEmployees()) {
				System.out.printf("\t%s %s (hired %s):\n", employee.getFirstName(), employee.getLastName(), employee.getHireDate());
				for (HashMap.Entry<GregorianCalendar, Double> entry : employee.getSalaries().entrySet()) {
					GregorianCalendar date = entry.getKey();
					Double salary = entry.getValue();
					System.out.printf("\t%1$tm.%1$tY: %2$-10.0f", date, salary);
					if (date.get(Calendar.MONTH) == 11) {
						System.out.println();
					}
				}
				System.out.printf("\t---\n\tAVERAGE: %.0f\n\n", employee.getAverageSalary());
			}
			for (int i = 0; i < department.getDepartmentName().length(); i++) {
				System.out.print("-");
			}
			System.out.println("------");
		}
		System.out.println("END OF OUTPUT");
	}

	private void initDepartments() {
		departments = new ArrayList<Department>();
		String departmentsArray[] = Initializer.initStringsFromFile(Constants.DEPARTMENTS_CATEGORY);
		for (String depString : departmentsArray) {
			String divided[] = depString.split(":");
			String name = divided[0];
			double minSalary = Double.parseDouble(divided[1]);
			double maxSalary = Double.parseDouble(divided[2]);
			Department depObj = new Department(name, minSalary, maxSalary);
			depObj.initEmployees();
			departments.add(depObj);
		}
	}

	private void outputDepartmentsToConsole() {
		for (Department d : departments) {
			System.out.printf("%s $%.0f - $%.0f\n", d.getDepartmentName(), d.getMinSalary(), d.getMaxSalary());
		}
	}
}