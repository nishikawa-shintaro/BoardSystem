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

import com.mysql.jdbc.StringUtils;

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

		List<Branch> branches = new BranchService().getBranch();
		request.setAttribute("branches", branches);

		List<Possition> possitions = new PossitionService().getPossition();
		request.setAttribute("possitions", possitions);

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
	ServletException{
		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		User differentUser = new UserService().select(request.getParameter("login_id"));

		if(isValid(request, messages, differentUser) == true) {

			User user = new User();
			user.setName(request.getParameter("name"));
			user.setLoginId(request.getParameter("login_id"));
			user.setPassword(request.getParameter("password"));
			user.setBranchId(Integer.parseInt(request.getParameter("branch_id")));
			user.setPossitionId(Integer.parseInt(request.getParameter("possition_id")));


			new UserService().register(user);

			response.sendRedirect("./manage");
		}else{

			List<Branch> branches = new BranchService().getBranch();
			request.setAttribute("branches", branches);

			List<Possition> possitions = new PossitionService().getPossition();
			request.setAttribute("departments", possitions);

			User newUser = getNewUser(request);
			request.setAttribute("newUser", newUser);

			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("./signup.jsp").forward(request, response);
		}
	}
	private User getNewUser(HttpServletRequest request)
			throws IOException, ServletException {

		User newUser = new User();

		newUser.setName(request.getParameter("name"));
		newUser.setLoginId(request.getParameter("login_id"));
		newUser.setPassword(request.getParameter("password"));
		newUser.setBranchId(Integer.parseInt(request.getParameter("branch_id")));
		newUser.setPossitionId(Integer.parseInt(request.getParameter("possition_id")));

		return newUser;
	}


	private boolean isValid(HttpServletRequest request, List<String> messages, User differentUser){
		String name = request.getParameter("name");
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		int branch_id = Integer.parseInt(request.getParameter("branch_id"));
		int possition_id = Integer.parseInt(request.getParameter("possition_id"));
		String checkPassword =request.getParameter("checkPassword");

		if(StringUtils.isBlank(name) == true) {
			messages.add("名前を入力してください");
		}
		if (10 < name.length()) {
			messages.add("名前は10字以下で入力してください");
		}
		if(StringUtils.isBlank(login_id) == true) {
			messages.add("ログインIDを入力してください");
		}else if((login_id.matches("\\w{6,20}")) != true) {
			messages.add("ログインIDは半角英数字の6～20文字で入力してください");
		}
		if(differentUser != null){
			messages.add("ログインIDが重複しています");
		}
		if(StringUtils.isBlank(password) == true) {
			messages.add("パスワードを入力してください");
		}else if((password.matches("\\w{6,20}")) !=true) {
			messages.add("パスワードは半角英数字の6～20文字で入力してください");
		}
		if(StringUtils.equals(password, checkPassword) ==false) {
			messages.add("パスワードを確認用と同一のものにしてください");
		}
		if(branch_id == 0) {
			messages.add("支店名を選択してください");
		}
		if(possition_id == 0) {
			messages.add("部署・役職名を選択してください");
		}

		if(messages.size() ==0) {
			return true;
		}else {
			return false;
		}
	}
	
}