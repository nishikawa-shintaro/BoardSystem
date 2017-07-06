package boardsystem.controllar;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardsystem.beans.Post;
import boardsystem.beans.User;
import boardsystem.service.PostService;
import boardsystem.service.UserService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		//ログインユーザーの情報を取得
		User user = (User) request.getSession().getAttribute("loginUser");

		//全ユーザーのリストを取得
		List<User> userList = new UserService().getUserAll();

		//新規投稿メッセージ情報を取得
		List<Post> posts = new PostService().getPost();

		request.setAttribute("posts", posts);
		request.setAttribute("user",userList);
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}