package userlogin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

	import java.io.IOException;
	import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.hibernate.Transaction;

	import entityclasses.*;
	import util.HibernateSessionUtil;

@WebServlet("/registeruser")
public class registeruser extends HttpServlet {

		private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			request.getRequestDispatcher("index.jsp").include(request, response);
			request.getRequestDispatcher("userregister.html").include(request, response);
			
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
			
			try {
				// 1. build hibernate session factory
				SessionFactory factory = HibernateSessionUtil.buildSessionFactory();
				
				// 2. create session object
				Session session = factory.openSession();
				int f=0;
				List<user> obj=session.createQuery("from user").list();
				for(user i:obj) {
					if(i.getName().equals(username)) {
						f=1;
					}
				}
				if(f==1) {
						out.write("<h4 style'color:red'>User Already Registered. Please Re-Login</h4>");
					}
					else {
				
				// 3. create a object with 5000 as default balance of wallet
				user obj1 = new user(username,password,5000.0);
				
				// 4. begin transaction
				Transaction tx = session.beginTransaction();
				
				// 5. save product
				session.save(obj1);
				
				// 6. commit transaction
				tx.commit();
				
				if(session != null ) {
					out.print("<h3 style='color:green'> User registered sucessfully, Please Login ! </h3>");
				}
					}
				
				// 3. close session
				session.close();
			} catch (Exception e) {
				out.print("<h3 style='color:red'> Register session is failed ! </h3>");
			}
		}
	
}
