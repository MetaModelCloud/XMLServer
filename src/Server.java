import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class Server extends HttpServlet
{
    /* ------------------------------------------------------------ */
    public void init(ServletConfig config) throws ServletException
    {
     super.init(config);
    }
    /* ------------------------------------------------------------ */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
    /* ------------------------------------------------------------ */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        out.println("<html>");
        out.println("<h1>Hello World</h1>");
        out.println("</html>");
        out.flush();
    }    
    public static void main(String[]args){
    	
    }
}