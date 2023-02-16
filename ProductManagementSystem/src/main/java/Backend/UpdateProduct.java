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


@WebServlet("/UpdateProduct")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateProduct() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			String display="<form action='UpdateProductToDB' method='post'>"
							+ "<table><tr><th>Product name</th><th>Price</th><th>Brand</th>"
								+ "<th>Category</th><th>Description</th><th>Edit</th></tr>";
			Connection con = Dbconnection.initializeDatabase();
			Statement stmt=con.createStatement();
			ResultSet resultSet = stmt.executeQuery("Select * from product where status=1");
			while(resultSet.next()) {
				display+="<tr>"
					+ "<td><input type='text' name='name' disabled value='"+resultSet.getString(2)+"' id='a"+resultSet.getString(1)+"'></td>"
					+ "<td><input type='text' name='price' disabled value='"+resultSet.getString(3)+"' id='a"+resultSet.getString(1)+"'></td>"
					+ "<td><input type='text' name='brand' disabled value='"+resultSet.getString(4)+"' id='a"+resultSet.getString(1)+"'></td>"
					+ "<td><input type='text' name='category' disabled value='"+resultSet.getString(5)+"' id='a"+resultSet.getString(1)+"'></td>"
					+ "<td><input type='text' name='description' disabled value='"+resultSet.getString(6)+"' id='a"+resultSet.getString(1)+"'></td>"
					+ "<td><input type='hidden' name='id' disabled value='"+resultSet.getString(1)+"' id='a"+resultSet.getString(1)+"'>"
					+ "<input type='hidden' id='"+resultSet.getString(1)+"btn'>"
					+ "<img src='images/edit.png' id='"+resultSet.getString(1)+"img' data-value='"+resultSet.getString(1)+"' onclick='change(this)'></td></tr>";
			}
			display+="</table></form>";
			PrintWriter p=response.getWriter();
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/UpdateProduct.html");
	    	dispatcher.include( request, response );
	    	p.print(display);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
