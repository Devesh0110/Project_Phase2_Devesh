package adminlogin;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

	import java.io.IOException;
	import java.io.PrintWriter;
import javax.servlet.ServletException;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entityclasses.*;
import util.HibernateSessionUtil;


@WebServlet("/changePassword")
public class changePassword extends HttpServlet {

		private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			try {
			
			HttpSession s=request.getSession(false);
			if(s!=null) {
			// fetch data from form
			String password = request.getParameter("password");
		
				// 1. build hibernate session factory
				SessionFactory factory = HibernateSessionUtil.buildSessionFactory();
				
				// 2. create session object
				Session session = factory.openSession();
				
				// 3. create a product object
				admin obj = new admin("admin",password);
				
				// 4. begin transaction
				Transaction tx = session.beginTransaction();
				
				// 5. update product
				session.update(obj);
				
				// 6. commit transaction
				tx.commit();
				
				if(session != null ) {
					out.print("<h3 style='color:green'> Password is updated sucessfully ! </h3>");
				}
			
				// 3. close session
				session.close();
			}
			} catch (Exception e) {
				out.print("<h3 style='color:red'> Hibernate session is failed ! </h3>");
			}
		}
}
			
		