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

@WebServlet(urlPatterns = { "/useredit" })
	public class UserEditServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	@Override
		protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,
		ServletException{


		//編集するユーザーIdを取得する処理
		String editUser = request.getParameter("useredit");
		System.out.println(request.getParameter("useredit"));
		List<User> userList = new UserService().getUserAll();



		//支店情報を取得
		List<Branch> branches = new BranchService().getBranch();

		//役職情報を取得
		List<Possition> possitions = new PossitionService().getPossition();

		request.setAttribute("editUser", editUser);

		request.setAttribute("branches", branches);

		request.setAttribute("possitions", possitions);

		request.getRequestDispatcher("./useredit.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
	ServletException{




		//ユーザーの登録変更を行う
		User editUser = new User();
		editUser.setLoginId(request.getParameter("loginId"));

		editUser.setPassword(request.getParameter("password"));

		editUser.setName(request.getParameter("name"));

		editUser.setBranchId(Integer.parseInt(request.getParameter("branch_id")));

		editUser.setPossitionId(Integer.parseInt(request.getParameter("possition_id")));

		response.sendRedirect("usercontrol");

	}
}