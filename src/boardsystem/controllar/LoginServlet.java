package boardsystem.controllar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardsystem.beans.User;
import boardsystem.service.LoginService;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// ログイン情報の取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

		// ユーザーのログイン
		User user = new LoginService().login(loginId, password);

		// ログイン情報のバリデーション
		if (isValid(request, user) == true) {
			// ログインユーザーをセッションにセット
			request.getSession().setAttribute("loginUser", user);

			// ホーム画面へのリダイレクト
			response.sendRedirect("./");
		} else {
			// ログイン情報をリクエストにセット
			request.setAttribute("loginId", loginId);
			request.setAttribute("password", password);

			// ログイン画面へ転送
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, User user) {

		List<String> messages = new ArrayList<String>();

		if (user == null) {
			// エラーメッセージをセッションにセット
			messages.add("ログインに失敗しました");
			request.getSession().setAttribute("errorMessages", messages);

			return false;
		} else {
			return true;
		}
	}
}