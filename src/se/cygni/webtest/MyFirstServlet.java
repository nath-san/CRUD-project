package se.cygni.webtest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.cygni.webtest.Person;

@SuppressWarnings("serial")
public class MyFirstServlet extends HttpServlet {
	private List<Person> people = new ArrayList<Person>();
	
	public MyFirstServlet(){
		people.add(new Person("Berra", 12));
		people.add(new Person("Agda", 22));
		people.add(new Person("Gurra", 32));
		people.add(new Person("Signe", 42));
	}
	
	//	@Override
	//	protected void service(HttpServletRequest request, HttpServletResponse response)
	//			throws ServletException, IOException {
	//		response.getWriter().println("Hello World");
	//	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//All people view
		if(req.getPathInfo() == null){
			req.setAttribute("people", people);
			getServletContext().getRequestDispatcher("/allPeople.jsp").forward(req, resp);
		}
		//New person view
		else if(req.getPathInfo().substring(1).equals("new")){
			getServletContext().getRequestDispatcher("/new.jsp").forward(req, resp);
		}
		//Update person view
		else if(req.getPathInfo().endsWith("update")){
			int id = Integer.parseInt(req.getPathInfo().substring(1, 2));
			Person p = findPerson(id);
			req.setAttribute("p", p);
			getServletContext().getRequestDispatcher("/update.jsp").forward(req, resp);
		}
		//Person view
		else{
			String param = req.getPathInfo().substring(1);
			Person p = findPerson(Integer.parseInt(param)); 
			req.setAttribute("person", p);
			getServletContext().getRequestDispatcher("/person.jsp").forward(req, resp);
		}
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		//Create a new person
		if(req.getPathInfo() == null){
			//Checks to see if name and age are valid
			if(checkInput(name, age)){
				Person p = new Person(name, Integer.parseInt(age));
				people.add(p);
				resp.sendRedirect(getServletContext().getContextPath()+"/people/"+p.getId());
				return;
			}
			//If name or age are invalid, go back to form
			else{
				req.setAttribute("errorNew", true);
				req.setAttribute("name", name);
				req.setAttribute("age", age);
				req.setAttribute("message", "Invalid input! Name must contain more than three characters and age must be a positive number! Please try again!");
				getServletContext().getRequestDispatcher("/new.jsp").forward(req, resp);
			}
		}
		//Update person
		else if(req.getParameter("_method")==null){
			int id = Integer.parseInt(req.getParameter("id"));
			Person p = findPerson(id);
			if(checkInput(name, age)){
				p.setName(name);
				p.setAge(Integer.parseInt(age));
				resp.sendRedirect(getServletContext().getContextPath()+"/people/"+p.getId());
				return;
			}
			//If name or age are invalid, go back to form
			else{
				req.setAttribute("p", p);
				req.setAttribute("name", name);
				req.setAttribute("age", age);
				req.setAttribute("errorUpdate", true);
				req.setAttribute("message", "Invalid input! Name must contain more than three characters and age must be a positive number! Please try again!");
				getServletContext().getRequestDispatcher("/update.jsp").forward(req, resp);
			}
		}
		//Delete a person
		else{
			deletePerson(Integer.parseInt(req.getPathInfo().substring(1)));
			resp.sendRedirect(getServletContext().getContextPath()+"/people");
		}
	}
	
	private boolean checkInput(String name, String age) {
		int parsedAge;
		try{
			parsedAge = Integer.parseInt(age);
		}
		catch(NumberFormatException nfe){
			return false;
		}
		if(name.length()<3 || name==null || parsedAge<0){
			return false;
		}
		else{
			return true;
		}
	}

	private Person findPerson(int id) {
		for(Person p:people){
			if(p.getId()==id){
				return p;
			}
		}
		return null;
	}
	
	private void deletePerson(int id){
		for(Person p:people){
			if(p.getId()==id){
				people.remove(p);
				return;
			}
		}
	}

}
