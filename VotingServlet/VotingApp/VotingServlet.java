import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class VotingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String ageText = request.getParameter("age");
        int age;

        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Please enter a valid age.");
            return;
        }

        // Escape user-provided text before displaying it as HTML.
        String safeName = escapeHtml(name == null ? "" : name);

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Voting Eligibility</title></head><body>");
        out.println("<h2>Voting Eligibility</h2>");
        out.println("<h3>Name: " + safeName + "</h3>");

        if (age >= 18) {
            out.println("<h3>Eligible to vote</h3>");
        } else {
            out.println("<h3>Not eligible to vote</h3>");
        }

        out.println("<p><a href=\"index.html\">Go back</a></p>");
        out.println("</body></html>");
    }

    private String escapeHtml(String value) {
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#x27;");
    }
}
