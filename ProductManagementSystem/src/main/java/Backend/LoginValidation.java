package Backend;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/LoginValidation")
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginValidation() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con;
		try {
			int access=0;
			con = Dbconnection.initializeDatabase();
			Statement stmt=con.createStatement();
			String VendorQuery="Select * from userdetails where username='"+request.getParameter("user")+"' and password='"+request.getParameter("pass")+"' and role='vendor' and access =1";
			String AdminQuery="Select * from userdetails where username='"+request.getParameter("user")+"' and password='"+request.getParameter("pass")+"' and role='admin'";
			ResultSet resultSet1 = stmt.executeQuery(VendorQuery);
			if(resultSet1.next()) {
				response.sendRedirect("VendorHome.html");
				access=1;
				}
			ResultSet resultSet2 = stmt.executeQuery(AdminQuery);
			if(resultSet2.next()) {
				response.sendRedirect("AdminHome.html");
				access=1;}
			if(access==0) {
					PrintWriter p=response.getWriter();
					RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
			    	dispatcher.include( request, response );
			    	p.print("<style>.Login{border:1px solid red}</style>");
				}
		} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
		}
		
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
