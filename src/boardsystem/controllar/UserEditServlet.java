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

import boardsystem.beans.Branch;
import boardsystem.beans.Possition;
import boardsystem.beans.User;
import boardsystem.exception.NoRowsUpdatedRuntimeException;
import boardsystem.service.BranchService;
import boardsystem.service.PossitionService;
import boardsystem.service.UserService;



@WebServlet(urlPatterns = { "/useredit" })
	public class UserEditServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	@Override
		protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,
		ServletException{

		//編集するユーザーIDの情報を取得し保持する
		//System.out.println(request.getParameter("editUserId"));
		int editUserId = Integer.parseInt(request.getParameter("editUserId"));
		User editUser = new UserService().getUser(editUserId);

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

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();


		User user = new User();
		user.setId(Integer.parseInt(request.getParameter("id")));
		//System.out.println(Integer.parseInt(request.getParameter("id")));
		user.setLoginId(request.getParameter("loginId"));
		//System.out.println(request.getParameter("loginId"));
		user.setName(request.getParameter("name"));
		//System.out.println(request.getParameter("name"));
		user.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		//System.out.println(Integer.parseInt(request.getParameter("branchId")));
		user.setPossitionId(Integer.parseInt(request.getParameter("possitionId")));
		//System.out.println(Integer.parseInt(request.getParameter("possitionId")));
		user.setPassword(request.getParameter("password"));

		//	エラーメッセージ、リダイレクト
		if (isValid(request, messages) == true) {
			try {

				UserService userService = new UserService();
				userService.update(user);
				System.out.println(user);
			} catch (NoRowsUpdatedRuntimeException e) {
				messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				session.setAttribute("errorMessages", messages);

				request.getRequestDispatcher("/useredit.jsp");
			}
			response.sendRedirect("usercontrol");

		}else{
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("/useredit.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		//バリデーションチェック用の値を取得
		int id = Integer.parseInt(request.getParameter("id"));
		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");
		int branch = Integer.parseInt(request.getParameter("branchId"));
		int possition = Integer.parseInt(request.getParameter("possitionId"));


		if(StringUtils.isBlank(loginId) == true) {
			messages.add("ログインIDを入力してください");

		}else if(loginId.matches("\\w{6,20}")!=true){
			messages.add("ログインIDは6～20文字の半角英数字で入力してください");

		}
		if(StringUtils.isNotBlank(name)== true){
			messages.add("名前を入力してください");

		}else if(10 < name.length()){
			messages.add("名前は10文字以内で入力してください");

		}
		if(StringUtils.isBlank(password)==true){
			messages.add("パスワードを入力してください");

		}else if(((password.matches("\\w{6,20}")) !=true) ){
			messages.add("パスワードは6～20文字の半角英数字で入力してください");

		}


		}if(messages.size() ==0){
			return true;
		}else{
			return false;
		}
	}
}