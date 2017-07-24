package boardsystem.controllar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

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
		String text = request.getParameter("text");

		// ログインユーザーの取得
		User user = (User) request.getSession().getAttribute("loginUser");

		//コメント投稿のIdを取得する
		String postId = request.getParameter("postId");

		// コメント情報の設定
		Comment comment = new Comment();
		comment.setText(text);
		comment.setPostId(Integer.parseInt(postId));
		comment.setUserId(user.getId());
		comment.setBranchId(user.getBranchId());
		comment.setPossitionId(user.getPossitionId());

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		if(isValid(request,messages) == true){

			new CommentService().register(comment);
			response.sendRedirect("./");

		}else{

			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String text = request.getParameter("text");
		System.out.println(text);

		if (StringUtils.isBlank(text) == true) {
			messages.add("コメントを入力してください");
		}
		if (text.length() > 500) {
			messages.add("コメントは500文字以下で入力してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}