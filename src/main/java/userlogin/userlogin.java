package userlogin;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

	import java.io.IOException;
	import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.hibernate.Transaction;

	import entityclasses.*;
	import util.HibernateSessionUtil;


@WebServlet("/loginuser")
public class userlogin extends HttpServlet {

		private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			request.getRequestDispatcher("index.jsp").include(request, response);
			request.getRequestDispatcher("userlogin.html").include(request, response);
			
		}
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			//add top nav 
			request.getRequestDispatcher("index.jsp").include(request, response);
			
			// fetch data from form
			String username = request.getParameter("name");
			String password = request.getParameter("password");
			HttpSession ss=request.getSession(true);
			if(ss!=null) {
			try {
				// 1. build hibernate session factory
				SessionFactory factory = HibernateSessionUtil.buildSessionFactory();
				
				// 2. create session object
				Session session = factory.openSession();
				int f=0;
				List<user> obj = session.createQuery("from user").list();
				for(user i:obj) {
					String x=i.getName();
					String y=i.getPassword();
					double s=i.getBalance();
					if(x.equals(username)&&y.equals(password)&&s>0.0) {
						//response.sendRedirect("bookflight.html");
						f=1;
					}
				}
				if(f==1) {
					session.close();
					user.usersesion=true;
					response.sendRedirect("options.html");
					
				}
				else {
					out.print("<h3 style='color:red'> Login failed ! </h3>");
				}
				
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		}
	
}