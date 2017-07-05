package boardsystem.controllar;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardsystem.beans.Post;
import boardsystem.service.PostService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		List<Post> postList = new PostService().getPost();
		//メッセージを渡す
		request.setAttribute("postList", postList);

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}