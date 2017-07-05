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
import boardsystem.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		//支店・役職情報を取得
		List<Branch> branches = new BranchService().getBranch();
		request.setAttribute("branches", branches);

		List<Possition> possitions = new PossitionService().getPossition();
		request.setAttribute("possitions", possitions);
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
	ServletException{

		//ユーザーの登録処理を行う
		User user = new User();
		user.setLoginId(request.getParameter("loginId"));

		user.setPassword(request.getParameter("password"));

		user.setName(request.getParameter("name"));

		user.setBranchId(Integer.parseInt(request.getParameter("branch_id")));

		user.setPossitionId(Integer.parseInt(request.getParameter("possition_id")));

		user.setIsStopped(0);


			new UserService().register(user);
			response.sendRedirect("usercontrol");
	}

}