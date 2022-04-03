package userlogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entityclasses.*;
import util.HibernateSessionUtil;

@WebServlet("/availableflights")
public class availableflights extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("options.html").include(request, response);
		String source=request.getParameter("source");
		String destination=request.getParameter("destination");
		String userdate=request.getParameter("dateoftravel");
		
		//my logic to check date is which day
		  

		  SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		  Date dt1;
		  HttpSession ss=request.getSession(false);
		if(ss!=null) {
		try {
			dt1 = format1.parse(userdate);
			DateFormat format2 = new SimpleDateFormat("EEEE"); 
			String str = format2.format(dt1);
		
		  
		
		try {
			// 1. build hibernate session factory
			SessionFactory factory = HibernateSessionUtil.buildSessionFactory();
			
			// 2. create session object
			Session session = factory.openSession();
			
			// 3. read products
			List<flight> obj = session.createQuery("from flight").list();
			
			int flag=0;
			for(flight p : obj) {
				//res has days stored on whcih flights are available
				//str has the user required day
				String[] res = p.getAvailabledays().split("[,]", 0);
			       for(String myStr: res) {
			    	   //check user source destination with flight path along with date availability
			          if(myStr.equals(str)&&p.getSource().equalsIgnoreCase(source)&&
			        		  p.getDestination().equalsIgnoreCase(destination))
			        	  flag=1;
			       }
			}
			
			//show data as table.
			out.print("<h4 style='text-align:center;'>Available Flights for "+userdate+" :- </h4>");
			if(flag==1) {
				flag=0;
			out.print("<style> table,td,th {"
					+ "border:2px solid red;text-align:center;"
					+ "padding: 10px; "
					+ "}</style>");
			out.print("<center><table >");
			out.print("<tr>");
				out.print("<th> ID</th>");
				out.print("<th> Airline Name</th>");
				out.print("<th> Ticket Price</th>");
			out.print("</tr>");
			
			int flag2=0;
			for(flight p : obj) {
				String[] res = p.getAvailabledays().split("[,]", 0);
			       for(String myStr: res) {
				if(myStr.equals(str)&&p.getSource().equalsIgnoreCase(source)&&
		        		  p.getDestination().equalsIgnoreCase(destination)) {
					flag2=1;
				}
			       }
			      if(flag2==1) {
			    	  flag2=0;
				out.print("<tr>");
				out.print("<td>"+p.getId()+"</td>");
				out.print("<td>"+p.getAirline()+"</td>");
				out.print("<td>"+p.getPrice()+"</td>");
				out.print("</tr>");
				}
			}
			       
			
			
			out.print("</table></center>");
			request.getRequestDispatcher("bookflight.html").include(request, response);;
			//response.sendRedirect("bookflight.html");
			}
			
			//if flight is unavailable on that day
			else {
				out.print("<h4 style='color:red'>No Flights for selected date</h4>");
			}
			// 3. close session
			session.close();
		} catch (Exception e) {
			out.print("<h3 style='color:red'> Hibernate session is failed ! "+e+"</h3>");
		}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
	}
}
