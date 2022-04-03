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

@WebServlet("/viewairlines")
public class viewairlines extends HttpServlet{

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
			out.print("<h4 style='text-align:center;'>Registerd Airlines:- </h4>");
			
			out.print("<style> table,td,th {"
					+ "border:2px solid red;text-align:center;"
					+ "padding: 10px; "
					+ "}</style>");
			out.print("<center><table >");
			out.print("<tr>");
				out.print("<th> ID</th>");
				out.print("<th> Airline Name</th>");
			out.print("</tr>");
			
			for(flight p : obj) {
				out.print("<tr>");
				out.print("<td>"+p.getId()+"</td>");
				out.print("<td>"+p.getAirline()+"</td>");
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
