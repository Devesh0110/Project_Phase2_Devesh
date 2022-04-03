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

@WebServlet("/payment")
public class payment extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//request.getRequestDispatcher("options.html").include(request, response);
		String password=request.getParameter("paymentpassword");
		HttpSession ss=request.getSession(false);
		if(ss!=null) {
		try {
			// 1. build hibernate session factory
			SessionFactory factory = HibernateSessionUtil.buildSessionFactory();
			
			// 2. create session object
			Session session = factory.openSession();
			
			// 3. read products
			List<user> obj = session.createQuery("from user").list();
			List<flight> obj2 = session.createQuery("from flight").list();
			int flag=0;
			double temp=0.0;
			double tempf=0.0;
			for(user p : obj) {
				if(p.getPassword().equals(password)) {
					//update user balance
//					int val=(Integer)ss.getAttribute("idvalue");
//					for(flight x:obj2) {
//						if(x.getId()==val){
//							temp=x.getPrice();
//						}
//						int userid=p.getId();
//						tempf=p.getBalance()-temp;
//					session.createSQLQuery("UPDATE user_data" + 
//							"SET balance="+tempf+ 
//							"WHERE id="+userid).executeUpdate();
//					}
					ss.setAttribute("usersid", p.getId());
					response.sendRedirect("ticket");
				}
			}
			
			session.close();
		} catch (Exception e) {
			out.print("<h3 style='color:red'> Hibernate session is failed ! "+e+"</h3>");
		}
		}
		else {
			out.write("<h2 style='color:red;'>Please Login</h2>");
		}
		
	}
}