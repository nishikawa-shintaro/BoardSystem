package boardsystem.controllar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;;

@WebServlet(urlPatterns = { "/useredit" })
	public class UserEditServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	@Override
		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws IOException,
	ServletException{
			request.getRequestDispatcher("./useredit.jsp").forward(request, response);
	}

}