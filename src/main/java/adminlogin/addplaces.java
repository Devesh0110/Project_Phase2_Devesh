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


@WebServlet("/places")
public class addplaces extends HttpServlet {

		private static final long serialVersionUID = 1L;

		protected void service(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			HttpSession s=request.getSession(false);
			if(s!=null) {
			// fetch data from form
			String source = request.getParameter("source");
			String destination = request.getParameter("destination");
			
			try {
				// 1. build hibernate session factory
				SessionFactory factory = HibernateSessionUtil.buildSessionFactory();
				
				// 2. create session object
				Session session = factory.openSession();
				// 3. create a object with 5000 as default balance of wallet
				places obj = new places(source,destination);
				
				// 4. begin transaction
				Transaction tx = session.beginTransaction();
				
				// 5. save product
				session.save(obj);
				
				// 6. commit transaction
				tx.commit();
				
				if(session != null ) {
					request.getRequestDispatcher("optionsadmin.html").include(request, response);
					out.print("<h3 style='color:green'> Source and Destination added sucessfully </h3>");
				}
			
				// 3. close session
				session.close();
				
			}
			catch (Exception e) {
				out.print("<h3 style='color:red'>Addition failed ! </h3>");
			}
			}
			else
				out.print("<h2>Problem with session, please re-login</h2>");
		}
	
}

