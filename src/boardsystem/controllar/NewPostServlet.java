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

import boardsystem.beans.Post;
import boardsystem.beans.User;
import boardsystem.service.PostService;

@WebServlet(urlPatterns = { "/newpost" })
	public class NewPostServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

	@Override
		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws IOException,
	ServletException{

		request.getRequestDispatcher("./newpost.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();


		User user = (User)request.getSession().getAttribute("loginUser");

		//新規投稿の情報を設定し投稿する
		Post post = new Post();

		post.setTitle(request.getParameter("title"));
		post.setCategory(request.getParameter("category"));
		post.setText(request.getParameter("text"));
		post.setUserId(user.getId());
		post.setBranchId(user.getBranchId());
		post.setPossitionId(user.getPossitionId());

		if(isValid(request, messages)==true){

			new PostService().register(post);
			response.sendRedirect("./");
			return;

		}else{

			request.setAttribute("post",post);
			System.out.println(post);
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("newpost.jsp").forward(request,response);
		}

}
	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String text = request.getParameter("text");

		if(StringUtils.isBlank(title)==true){
			messages.add("件名を入力してください");

		}else if(30 < title.length()){
			messages.add("件名は30文字以下で入力してください");
		}

		if(StringUtils.isBlank(category)==true){
			messages.add("カテゴリー名を入力してください");

		}else if(10 < category.length()){
			messages.add("カテゴリー名は10文字以下で入力してください");

		}

		if(StringUtils.isBlank(text)==true){
			messages.add("本文を入力してください");

		}else if(1000 < text.length()){
			messages.add("本文は1000文字以下で入力してください");
		}
		if(messages.size() ==0){
			return true;

		}else{

			return false;
		}
	}
}