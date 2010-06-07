package se.cygni.webtest;

import java.io.IOException;
import java.io.PrintWriter;
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
		people.add(new Person("Berra", 1, 12));
		people.add(new Person("Agda", 2, 22));
		people.add(new Person("Gurra", 3, 32));
		people.add(new Person("Signe", 4, 42));
	}
	
	//	@Override
	//	protected void service(HttpServletRequest request, HttpServletResponse response)
	//			throws ServletException, IOException {
	//		response.getWriter().println("Hello World");
	//	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getPathInfo() == null){
			req.setAttribute("people", people);
			getServletContext().getRequestDispatcher("/allPeople.jsp").forward(req, resp);
		}else if(req.getPathInfo().substring(1).equals("new")){
			getServletContext().getRequestDispatcher("/new.jsp").forward(req, resp);
		}else{
			String param = req.getPathInfo().substring(1);
			Person p = findPerson(Integer.parseInt(param)); 
			req.setAttribute("person", p);
			getServletContext().getRequestDispatcher("/person.jsp").forward(req, resp);
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

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		int id = Integer.parseInt(req.getParameter("id"));
		int age = Integer.parseInt(req.getParameter("age"));
		Person p = new Person(name, id, age);
		people.add(p);
		String url = "people/"+id;
		resp.sendRedirect(url);
		return;
	}

}
