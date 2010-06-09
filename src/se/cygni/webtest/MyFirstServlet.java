package se.cygni.webtest;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.cygni.webtest.Person;

@SuppressWarnings("serial")
public class MyFirstServlet extends HttpServlet {
	private List<Person> people = new ArrayList<Person>();
	private DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	
	public MyFirstServlet(){
		try{
			Date date1 = (Date)formatter.parse("19850429");
			Date date2 = (Date)formatter.parse("19750523");
			Date date3 = (Date)formatter.parse("19650621");
			people.add(new Person("Berra", date1));
			people.add(new Person("Agda", date2));
			people.add(new Person("Gurra", date3));
		}
		catch(ParseException pe){}

	}
	
	//	@Override
	//	protected void service(HttpServletRequest request, HttpServletResponse response)
	//			throws ServletException, IOException {
	//		response.getWriter().println("Hello World");arg1
	//	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//All people view
		if(req.getPathInfo() == null){
			req.setAttribute("people", people);
			getServletContext().getRequestDispatcher("/allPeople.jsp").forward(req, resp);
		}
		//New person view
		else if(req.getPathInfo().endsWith("new")){
			getServletContext().getRequestDispatcher("/new.jsp").forward(req, resp);
		}
		//Update person view
		else if(req.getPathInfo().endsWith("update")){
			int id = Integer.parseInt(req.getPathInfo().substring(1, 2));
			Person p = findPerson(id);
			String date = formatter.format(p.getBirthday());
			req.setAttribute("p", p);
			req.setAttribute("date", date);
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
		String birthday = req.getParameter("birthday");
		//Create a new person
		if(req.getPathInfo() == null){
			//Checks to see if name and age are valid
			if(checkInput(name, birthday)){
				try{
					Person p = new Person(name, (Date)formatter.parse(birthday));
					people.add(p);
					resp.sendRedirect(getServletContext().getContextPath()+"/people/"+p.getId());
				}
				catch(ParseException pe){}
				return;
			}
			//If name or birthday are invalid, go back to form
			else{
				req.setAttribute("errorNew", true);
				req.setAttribute("name", name);
				req.setAttribute("birthday", birthday);
				req.setAttribute("message", "Invalid input! Name must contain more than three characters and birthday must have the format yyyyMMdd! Please try again!");
				getServletContext().getRequestDispatcher("/new.jsp").forward(req, resp);
			}
		}
		//Update person
		else if(req.getParameter("_method")==null){
			int id = Integer.parseInt(req.getParameter("id"));
			Person p = findPerson(id);
			if(checkInput(name, birthday)){
				p.setName(name);
				try{
					p.setBirthday((Date)formatter.parse(birthday));
					resp.sendRedirect(getServletContext().getContextPath()+"/people/"+p.getId());
				}
				catch(ParseException pe){}
				return;
			}
			//If name or birthday are invalid, go back to form
			else{
				req.setAttribute("p", p);
				req.setAttribute("name", name);
				req.setAttribute("birthday", birthday);
				req.setAttribute("errorUpdate", true);
				req.setAttribute("message", "Invalid input! Name must contain more than three characters and birthday must have the format yyyyMMdd! Please try again!");
				getServletContext().getRequestDispatcher("/update.jsp").forward(req, resp);
			}
		}
		//Delete a person
		else{
			deletePerson(Integer.parseInt(req.getPathInfo().substring(1)));
			resp.sendRedirect(getServletContext().getContextPath()+"/people");
		}
	}
	
	private boolean checkInput(String name, String birthday) {
		try{
			formatter.parse(birthday);
		}
		catch(ParseException pe){
			return false;
		}
		if(name.length()<3 || name==null){
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
