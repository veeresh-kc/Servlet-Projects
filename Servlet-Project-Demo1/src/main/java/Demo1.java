

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Demo1")
public class Demo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Demo1() {
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter display=response.getWriter();
		display.print("<html><body>");
		display.print("<h1>Java Servelet Program</h>");
		display.print("</body></html>");
	}

}
