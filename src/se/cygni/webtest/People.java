package se.cygni.webtest;

import java.util.ArrayList;
import java.util.List;

public class People {

	public static List<Person> people = new ArrayList<Person>();
	
	public void add(Person p){
		people.add(p);
	}
	
}
