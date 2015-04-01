package com.github.alxwhtmr.employeedb;

import java.util.*;

/**
 * Created on 01.04.2015.
 */
public class Employee {
	private String firstName;
	private String lastName;
	private Department department;
	private TreeMap<GregorianCalendar, Double> salaries;
	private GregorianCalendar hired;

	public Employee(String firstName, String lastName, Department department) {
		lastName = lastName.replaceAll("[^\\x00-\\x7f]","");
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		salaries = new TreeMap<>();
	}

	public void initHire() {
		int yearsDiff = Constants.END_YEAR - Constants.START_YEAR;
		int yearHired = Constants.START_YEAR + new Random().nextInt(yearsDiff);
		int monthHired = new Random().nextInt(Constants.MONTHS_IN_YEAR);
		hired = new GregorianCalendar(yearHired, monthHired, 1);
	}

	public void initSalaries() {
		Double salary = department.getMinSalary() + new Random().nextInt((int) (department.getMaxSalary() - department.getMinSalary()));
		for (int year = hired.get(Calendar.YEAR); year <= Constants.END_YEAR; year++) {
			int startMonth = year == hired.get(Calendar.YEAR) ? hired.get(Calendar.MONTH) : 0;
			for (int month = startMonth; month < Constants.MONTHS_IN_YEAR; month++) {
				if (year == hired.get(Calendar.YEAR) && month == hired.get(Calendar.MONTH)) {
					continue;
				} else {
					// Chance to increment salary is 1 to 5. An ugly real-life simulation
					if (new Random().nextInt(5) == 0) {
						salary = salary + department.getSalaryIncrement();
					}
				}
				GregorianCalendar date = new GregorianCalendar(year, month, 1);
				salaries.put(date, salary);
			}
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public TreeMap<GregorianCalendar, Double> getSalaries() {
		return salaries;
	}

	public String getHireDate() {
		String hireDate = String.format("%1$tm.%1$tY", hired);
		return hireDate;
	}

	public double getTotalIncome() {
		double total = 0.0;
		for (HashMap.Entry<GregorianCalendar, Double> entry : salaries.entrySet()) {
			Double salary = entry.getValue();
			total += salary;
		}
		return total;
	}

	public double getAverageSalary() {
		return getTotalIncome() / salaries.size();
	}
}
