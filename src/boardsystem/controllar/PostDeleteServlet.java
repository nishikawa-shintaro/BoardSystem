package boardsystem.controllar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardsystem.service.PostService;

@WebServlet(urlPatterns = { "/postDelete" })
public class PostDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		int postId = Integer.parseInt(request.getParameter("postId"));

		new PostService().delete(postId);
		response.sendRedirect("./");
	}

}