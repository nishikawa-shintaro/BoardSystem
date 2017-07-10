package boardsystem.controllar;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardsystem.beans.Branch;
import boardsystem.beans.Possition;
import boardsystem.beans.User;
import boardsystem.service.BranchService;
import boardsystem.service.PossitionService;
import boardsystem.service.UserService;;

@WebServlet(urlPatterns = { "/usercontrol" })
	public class UserControlServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	@Override
		protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException,ServletException{

		//全ユーザー情報を取得する
		List<User> userList = new UserService().getUserAll();
		request.setAttribute("userList", userList);

		//支店情報を取得する
		List<Branch> branches = new BranchService().getBranch();
		request.setAttribute("branches", branches);

		//役職情報を取得する
		List<Possition> possitions = new PossitionService().getPossition();
		request.setAttribute("possitions", possitions);


		request.getRequestDispatcher("./usercontrol.jsp").forward(request, response);
	}

}