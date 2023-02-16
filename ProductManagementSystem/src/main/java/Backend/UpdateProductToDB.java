package Backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateProductToDB")
public class UpdateProductToDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UpdateProductToDB() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            Connection con = Dbconnection.initializeDatabase();
            
            PreparedStatement st = con
                   .prepareStatement("update product set pname=?,price=?,brand=?,category=?,description=? where pid=?");

            st.setString(1,	request.getParameter("name"));
            st.setDouble(2, Double.valueOf(request.getParameter("price")));
            st.setString(3, request.getParameter("brand"));
            st.setString(4, request.getParameter("category"));
            st.setString(5, request.getParameter("description"));
            st.setInt(6, Integer.parseInt(request.getParameter("id")));
            st.executeUpdate();
            st.close();
            con.close();
            response.sendRedirect ("UpdateProduct");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
