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

@WebServlet("/DisplayProducts")
public class DisplayProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DisplayProducts() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String display="<table><tr><th>Product name</th><th>Price</th><th>Brand</th>"
								+ "<th>Category</th><th>Description</th></tr>";
			Connection con = Dbconnection.initializeDatabase();
			Statement stmt=con.createStatement();
			ResultSet resultSet = stmt.executeQuery("Select * from product where status=1");
			while(resultSet.next()) {
				display+="<tr><td>"+resultSet.getString(2)+"</td>"
							+ "<td>"+resultSet.getInt(3)+"</td>"
									+ "<td>"+resultSet.getString(4)+"</td>"
											+ "<td>"+resultSet.getString(5)+"</td>"
													+ "<td>"+resultSet.getString(6)+"</td><tr>";
			}
			PrintWriter p=response.getWriter();
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/DisplayProduct.html");
	    	dispatcher.include( request, response );
	    	p.print(display);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
