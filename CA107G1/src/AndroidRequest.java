import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AndroidRequest extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		
		
		PrintWriter out = res.getWriter();
		out.print("HelloWorld");
	}
	

}
