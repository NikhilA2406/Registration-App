

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String name=request.getParameter("name");
		String email_id =request.getParameter("email");
		String city=request.getParameter("city");
		String pass=request.getParameter("password");
		String phone_No=request.getParameter("phone");
		
		//Load and Register the Driver
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
				//Establish the Connection
				
				String url="jdbc:mysql://localhost:3306/userregistraioninfo";
				String user="root";
				String password="Nikhil#24";
				Connection connect=DriverManager.getConnection(url, user, password);
				
				PreparedStatement pstm=connect.prepareStatement("INSERT INTO userregistraioninfo (name, city, email, phone_number, password) VALUES(?,?,?,?,?) ");
				pstm.setString(1, name);
				pstm.setString(2, city);
				pstm.setString(3, email_id);
				pstm.setString(4, phone_No);
				pstm.setString(5, pass);
				
				int rowAffected=pstm.executeUpdate();
				PrintWriter writer = response.getWriter();
				if(rowAffected!=0)
				{
					writer.println("<h1>Registration Success!</h1>");
				}
				else
				{
					writer.println("<h1>Registration Failed!!!</h1>");
				}
				pstm.close();
				connect.close();
				
				
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				} 
				catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
				}
	}

}
