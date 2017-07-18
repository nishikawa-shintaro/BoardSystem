package boardsystem.controllar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardsystem.beans.User;
import boardsystem.service.UserService;



@WebServlet(urlPatterns = { "/stop" })
public class IsStoppedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse	response) throws ServletException, IOException {


			request.getRequestDispatcher("usercontrol.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//復活停止するユーザーのID情報を取得する
		int editUserId = Integer.parseInt(request.getParameter("editUserId"));
		int isStopped = Integer.parseInt(request.getParameter("isStopped"));

		//isStopped情報 editUser情報を格納しDBに保存
		User editUser = new UserService().getUser(editUserId);
		editUser.setIsStopped(isStopped);

		new UserService().getStopId(editUserId, isStopped);

		response.sendRedirect("./usercontrol");

	}

}

