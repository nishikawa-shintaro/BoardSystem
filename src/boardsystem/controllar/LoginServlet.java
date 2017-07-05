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

import boardsystem.beans.User;
import boardsystem.service.LoginService;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException,ServletException {

		//id,passwordの値を受け取る
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();

		//LoginServiceクラスに値を送る
		LoginService loginService = new LoginService();
		User user = loginService.login(loginId,password);

		if(user != null) {
			session.setAttribute("loginUser", user);
			response.sendRedirect("./");

		} else {
			List<String> messages = new ArrayList<String>();
			messages.add("ログインに失敗しました");
			session.setAttribute("errorMessages", messages);
			request.setAttribute("loginId", loginId);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}