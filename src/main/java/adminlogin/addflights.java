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


@WebServlet("/addflightdata")
public class addflights extends HttpServlet {

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
			String airline=request.getParameter("airline");
			double price=Double.parseDouble(request.getParameter("price"));
			String availabledays=request.getParameter("availabledays");
			try {
				// 1. build hibernate session factory
				SessionFactory factory = HibernateSessionUtil.buildSessionFactory();
				
				// 2. create session object
				Session session = factory.openSession();
				// 3. create a object with 5000 as default balance of wallet
				flight obj = new flight(source,destination,airline,price,availabledays);
				
				// 4. begin transaction
				Transaction tx = session.beginTransaction();
				
				// 5. save product
				session.save(obj);
				
				// 6. commit transaction
				tx.commit();
				
				if(session != null ) {
					request.getRequestDispatcher("optionsadmin.html").include(request, response);
					out.print("<h3 style='color:green'> Flight added sucessfully </h3>");
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