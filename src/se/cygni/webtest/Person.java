package se.cygni.webtest;

import java.util.Date;

public class Person {
	private String name;
	private int id;
	private Date birthday;
	private static int count = 1;
	
	public Person(String name, Date birthday) {
		this.name = name;
		this.id = count;
		this.birthday = birthday;
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
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
