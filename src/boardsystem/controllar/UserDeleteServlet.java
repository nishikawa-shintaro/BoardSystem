package boardsystem.controllar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardsystem.service.UserService;

@WebServlet(urlPatterns = { "/delete" })
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse	response) throws ServletException, IOException {


			request.getRequestDispatcher("usercontrol.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//消去するユーザーのID情報を取得する
		int editUserId = Integer.parseInt(request.getParameter("editUserId"));
		new UserService().deleteId(editUserId);

		response.sendRedirect("./usercontrol");

	}
}
