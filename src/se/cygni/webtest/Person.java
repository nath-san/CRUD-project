package se.cygni.webtest;

public class Person {
	private String name;
	private int id, age;
	private static int count = 1;
	
	public Person(String name, int age) {
		this.name = name;
		this.id = count;
		this.age = age;
		count++;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
