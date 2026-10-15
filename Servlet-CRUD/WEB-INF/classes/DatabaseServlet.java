
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DatabaseServlet extends HttpServlet {

    Connection con;

    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student",
                "root",
                "ssn1"
            );

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        String regno = request.getParameter("regno");

        try {

            if ("add".equals(action)) {

                String name = request.getParameter("name");
                String course = request.getParameter("course");

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO students VALUES (?, ?, ?)"
                );

                ps.setString(1, regno);
                ps.setString(2, name);
                ps.setString(3, course);

                ps.executeUpdate();
                ps.close();

                out.println("Student added successfully");

            } else if ("view".equals(action)) {

                Statement st = con.createStatement();

                ResultSet rs = st.executeQuery(
                    "SELECT * FROM students"
                );

                out.println("<h2>Student Records</h2>");
                out.println("<table border='1'>");
                out.println("<tr><th>Register No</th>" +
                    "<th>Name</th><th>Course</th></tr>");

                while (rs.next()) {
                    out.println("<tr><td>" +
                        rs.getString("regno") + "</td><td>" +
                        rs.getString("name") + "</td><td>" +
                        rs.getString("course") + "</td></tr>");
                }

                out.println("</table>");

                rs.close();
                st.close();

            } else if ("search".equals(action)) {

                PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM students WHERE regno = ?"
                );

                ps.setString(1, regno);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    out.println("Name: " + rs.getString("name") +
                        "<br>Course: " + rs.getString("course"));
                } else {
                    out.println("Student not found");
                }

                rs.close();
                ps.close();

            } else if ("update".equals(action)) {

                String name = request.getParameter("name");
                String course = request.getParameter("course");

                PreparedStatement ps = con.prepareStatement(
                    "UPDATE students SET name=?, course=? WHERE regno=?"
                );

                ps.setString(1, name);
                ps.setString(2, course);
                ps.setString(3, regno);

                int count = ps.executeUpdate();

                out.println(count > 0
                    ? "Student updated successfully"
                    : "Student not found");

                ps.close();

            } else if ("delete".equals(action)) {

                PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM students WHERE regno = ?"
                );

                ps.setString(1, regno);

                int count = ps.executeUpdate();

                out.println(count > 0
                    ? "Student deleted successfully"
                    : "Student not found");

                ps.close();

            } else {
                out.println("Invalid operation");
            }

        } catch (SQLException e) {
            out.println("Database operation failed");
            e.printStackTrace();
        }

        out.println("<br><br><a href='index.html'>Back to Home</a>");
    }

    public void destroy() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
