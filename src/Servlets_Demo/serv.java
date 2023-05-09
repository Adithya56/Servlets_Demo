package Servlets_Demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/serv")
public class serv extends HttpServlet {
	public serv() {
		System.out.println("hii");
	}

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// int a = Integer.parseInt(request.getParameter("num1"));
		// int b = Integer.parseInt(request.getParameter("num2"));
		// int c = a + b;
		// out.println("Sum of given two numbers is: " + c);

		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
			// CallableStatement cs = con.prepareCall("CALL UpdateAcc(?)");
			// cs.setInt(1, 1006);
			// cs.execute();
			PreparedStatement ps = con.prepareStatement("insert into adi_acc values(?,?,?,?)");
			ps.setInt(1, Integer.parseInt(request.getParameter("acno")));
			ps.setObject(2, request.getParameter("name"));
			ps.setDate(3, java.sql.Date.valueOf(request.getParameter("date")));
			ps.setDouble(4, Double.parseDouble(request.getParameter("bal")));
			ps.executeUpdate();
			// Statement ps = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			// ResultSet rs = ps.executeQuery("select * from adi_acc");
			// rs.moveToInsertRow();
			// rs.updateInt(1, Integer.parseInt(request.getParameter("acno")));
			// rs.updateString(2, request.getParameter("name"));
			// rs.updateDate(3, java.sql.Date.valueOf(request.getParameter("date")));
			// rs.updateDouble(4, Double.parseDouble(request.getParameter("bal")));
			// rs.updateRow();
			// rs.insertRow();

			out.println("New Account inserted..");
			// out.println("<table border='1'>");
			// while (rs.next()) {
			// out.println("<tr>");
			// out.println("<td>" + rs.getInt(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getDate(3)
			// + "</td><td>" + rs.getDouble(4));
			// out.println("</tr>");
			// }
			// out.println("</table>");
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
