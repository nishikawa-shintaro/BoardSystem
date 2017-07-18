package boardsystem.controllar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardsystem.beans.Comment;
import boardsystem.beans.User;
import boardsystem.service.CommentService;

@WebServlet(urlPatterns = { "/newComment" } )
public class NewCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		;
		// コメント情報の取得
		String commentText = request.getParameter("commentText");

		// ログインユーザーの取得
		User user = (User) request.getSession().getAttribute("loginUser");

		//コメント投稿のIdを取得する
		String postId = request.getParameter("postId");

		// コメント情報の設定
		Comment comment = new Comment();
		comment.setText(commentText);
		comment.setPostId(Integer.parseInt(postId));
		comment.setUserId(user.getId());
		comment.setBranchId(user.getBranchId());
		comment.setPossitionId(user.getPossitionId());

		//DBに登録する
		new CommentService().register(comment);
			response.sendRedirect("./");
	}
}