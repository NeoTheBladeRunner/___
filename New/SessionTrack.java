import java.io.*;
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.util.*;

public class SessionTrack extends HttpServlet {  

    public void doGet(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {  

        HttpSession session = req.getSession(true);
        Date createTime = new Date(session.getCreationTime());
        Date lastAccessTime = new Date(session.getLastAccessedTime()); 

        Integer visitCount = (Integer) session.getAttribute("visitCount");  
        if (visitCount == null) {
            visitCount = 0;
        }

        String userIDKey = "userID";
        String userID = (String) session.getAttribute(userIDKey);  
        if (userID == null) {
            userID = "ABCD";
            session.setAttribute(userIDKey, userID);  
        }

        visitCount++;
        session.setAttribute("visitCount", visitCount); 

        res.setContentType("text/html"); 
        PrintWriter out = res.getWriter();
        String docType = "<!DOCTYPE html>";
        out.println(docType);
        out.println("<html><body>");
        out.println("<h2>Session Tracking</h2>");
        out.println("<p>Session created at " + createTime + "</p>");
        out.println("<p>Session last accessed at " + lastAccessTime + "</p>");
        out.println("<p>Number of visits: " + visitCount + "</p>");
        out.println("<p>UserID: " + userID + "</p>");
        out.println("</body></html>");
    }
}


// javac -cp "C:\Users\nikhi\Downloads\EX_download\apache-tomcat-9.0.100\apache-tomcat-9.0.100\lib\servlet-api.jar" SessionTrack.java
