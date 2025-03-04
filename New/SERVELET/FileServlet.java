import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String filename = request.getParameter("filename");
        File file = new File(filename);
        out.println("<html><body>");
        out.println("<h2>Contents of the file: " + filename + "</h2>");
        if (file.exists() && file.isFile()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {

                    out.println(line + "<br>");
                }
            } catch (IOException e) {
                out.println("<p>Error reading file: " + e.getMessage() + "</p>");
            }
        } else {
            out.println("<p>File does not exist or is not a regular file.</p>");
        }
        out.println("</body></html>");
        out.close();
    }
}