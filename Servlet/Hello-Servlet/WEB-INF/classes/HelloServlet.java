import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest req,
                      HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        String name = req.getParameter("t1");

        pw.println("<html><body>");
        pw.println("<h1>Hello " + name + "</h1>");
        pw.println("</body></html>");

        pw.close();
    }
}