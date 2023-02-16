package Backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//Import Database Connection Class file
import Backend.Dbconnection;

/**
 * Servlet implementation class Addproduct
 */
@WebServlet("/Addproduct")
public class Addproduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Addproduct() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			  
            // Initialize the database
            Connection con = Dbconnection.initializeDatabase();
            
            // Create a SQL query to insert data into demo table
            // demo table consists of two columns, so two '?' is used
            PreparedStatement st = con
                   .prepareStatement("insert into product (pname,price,brand,category,description,status)values(?,?,?,?,?,?)");

            
            // For the first parameter,
            // get the data using request object
            // sets the data to st pointer
            st.setString(1,request.getParameter("pname"));
  
            // Same for second parameter
            st.setDouble(2, Double.valueOf(request.getParameter("price")));
            st.setString(3, request.getParameter("brand"));
            st.setString(4, request.getParameter("category"));
            st.setString(5, request.getParameter("description"));
            st.setInt(6, 1);
  
            // Execute the insert command using executeUpdate()
            // to make changes in database
            st.executeUpdate();
  
            // Close all the connections
            st.close();
            con.close();
  
            // Get a writer pointer 
            // to display the successful result
//            PrintWriter out = response.getWriter();
//            out.println("<html><body><b>Successfully Inserted"
//                        + "</b></body></html>");
            response.sendRedirect ("AddProduct.html");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}

}