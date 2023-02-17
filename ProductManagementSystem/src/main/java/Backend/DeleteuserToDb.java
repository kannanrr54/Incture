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

/**
 * Servlet implementation class DeletePro
 */
@WebServlet("/DeleteuserToDb")
public class DeleteuserToDb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteuserToDb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String display="<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200\" />"
					+ "<style>"
					+ ".material-symbols-outlined {"
					+ "  font-variation-settings:"
					+ "  'FILL' 0,"
					+ "  'wght' 400,"
					+ "  'GRAD' 0,"
					+ "  'opsz' 48;"
					+ "cursor:pointer;"
					+ "}"
					+ "</style>"
					+ "<table><tr><th>NAME</th><th>EMAIL</th><th>USERNAME</th>"
								+ "<th>ROLE</th><th>ACTION</th></tr>";
			Connection con = Dbconnection.initializeDatabase();
			Statement stmt=con.createStatement();
			ResultSet resultSet = stmt.executeQuery("Select * from userdetails where access=1 and role='vendor'");
			while(resultSet.next()) {
				display+="<tr><td>"+resultSet.getString(2)+"</td>"
							+ "<td>"+resultSet.getString(3)+"</td>"
									+ "<td>"+resultSet.getString(4)+"</td>"
											+ "<td>"+resultSet.getString(6)+"</td>"
															+ "<td><span class='material-symbols-outlined' onclick='onDel(this)'  data-idr='"+resultSet.getInt(1)+"'>Delete</span></td>"
															+ "<tr>";
			}
			PrintWriter p=response.getWriter();
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/DeleteUser.html");
	    	dispatcher.include( request, response );
	    	p.print(display);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
