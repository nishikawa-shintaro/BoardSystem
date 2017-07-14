package boardsystem.controllar;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardsystem.beans.Comment;
import boardsystem.beans.Post;
import boardsystem.beans.User;
import boardsystem.service.CommentService;
import boardsystem.service.PostService;
import boardsystem.service.UserService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		//全ユーザーのリストを取得
		List<User> userList = new UserService().getUserAll();

		//新規投稿メッセージ情報を取得
		List<Post> posts = new PostService().getPost();

		//コメント投稿情報を取得
		List<Comment> commentList = new CommentService().getCommentAll();

		//カテゴリーリストを取得する
		List<String> categories = new PostService().getCategoryAll();


		request.setAttribute("categories", categories);
		request.setAttribute("posts", posts);
		request.setAttribute("userList", userList);
		request.setAttribute("commentList", commentList);
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}