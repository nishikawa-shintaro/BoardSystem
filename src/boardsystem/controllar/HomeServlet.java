package boardsystem.controllar;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardsystem.beans.Comment;
import boardsystem.beans.Post;
import boardsystem.beans.User;
import boardsystem.beans.UserPost;
import boardsystem.service.CommentService;
import boardsystem.service.PostService;
import boardsystem.service.UserPostService;
import boardsystem.service.UserService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {


		//全ユーザーのリストを取得
		List<User> userList = new UserService().getUserAll();
		request.setAttribute("userList", userList);

		//新規投稿メッセージ情報を取得
		List<Post> posts = new PostService().getPost();
		request.setAttribute("posts", posts);

		//コメント投稿情報を取得
		List<Comment> commentList = new CommentService().getCommentAll();
		request.setAttribute("commentList", commentList);

		//カテゴリーリストを取得する
		List<String> categories = new PostService().getCategoryAll();
		request.setAttribute("categories",categories);
		//System.out.println(categories);

		//絞込み時間の指定の取得
		String startdate= request.getParameter("startdate");
		//System.out.println(request.getParameter("startdate"));
		String enddate= request.getParameter("enddate");
		//System.out.println(request.getParameter("enddate"));
		String category = request.getParameter("category");
		//System.out.println(request.getParameter("category"));

		request.setAttribute("searchcategory",category);
		request.setAttribute("startdate",startdate);
		request.setAttribute("enddate",enddate);




		//初期値の指定
		if(startdate == null || startdate.isEmpty()){
			startdate = "2017-07-01";
		}

		if(enddate==null || enddate.isEmpty()){
			Date date= new Date();
			enddate = new SimpleDateFormat("yyyy-MM-dd").format(date);

		}

		List<UserPost> userposts = new UserPostService().getPosts(startdate, enddate, category);
		request.setAttribute("userposts", userposts);
		//System.out.println(userposts);

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}