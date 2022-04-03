package adminlogin;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entityclasses.*;
import util.HibernateSessionUtil;

@WebServlet("/viewplaces")
public class viewplaces extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("optionsadmin.html").include(request, response);

		try {
			// 1. build hibernate session factory
			SessionFactory factory = HibernateSessionUtil.buildSessionFactory();
			
			// 2. create session object
			Session session = factory.openSession();
			
			// 3. read products
			List<flight> obj = session.createQuery("from flight").list();
			
			//show data as table.
			out.print("<center><h1> List of places registered as Sources and Destinations :- </h1></center>");
			
			out.print("<style> table,td,th {"
					+ "border:2px solid red;"
					+ "padding: 10px; "
					+ "}</style>");
			out.print("<center><table>");
			out.print("<tr>");
				out.print("<th> ID</th>");
				out.print("<th> Source Place</th>");
				out.print("<th> Destination Place</th>");
			out.print("</tr>");
			
			for(flight p : obj) {
				out.print("<tr>");
				out.print("<td>"+p.getId()+"</td>");
				out.print("<td>"+p.getSource()+"</td>");
				out.print("<td>"+p.getDestination()+"</td>");
				out.print("</tr>");
			}
			out.print("</table></center>");
			// 3. close session
			session.close();
		} catch (Exception e) {
			out.print("<h3 style='color:red'> Hibernate session is failed ! "+e+"</h3>");
		}
	}
	
}
