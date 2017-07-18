package boardsystem.controllar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardsystem.service.CommentService;

@WebServlet(urlPatterns = { "/commentDelete"})
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		int commentId = Integer.parseInt(request.getParameter("commentId"));

		new CommentService().delete(commentId);
		response.sendRedirect("./");

	}

}