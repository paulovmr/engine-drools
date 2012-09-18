package com.client;

public class Client {

	private String name;

	private int age;

	private CivilStatus civilStatus;

	private int amountOfProperty;

	private double creditLimit;

	public Client(String name, int age, CivilStatus civilStatus, int amountOfProperty) {
		this.name = name;
		this.age = age;
		this.civilStatus = civilStatus;
		this.amountOfProperty = amountOfProperty;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public CivilStatus getCivilStatus() {
		return civilStatus;
	}

	public int getAmountOfProperty() {
		return amountOfProperty;
	}
}
