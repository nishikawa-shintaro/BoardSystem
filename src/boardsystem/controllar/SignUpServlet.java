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

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		//支店･役職情報の取得
		List<Branch> branches = new BranchService().getBranch();
		List<Possition> possitions = new PossitionService().getPossition();


			//ユーザーの登録処理を行う
			User user = new User();
			user.setLoginId(request.getParameter("loginId"));

			user.setPassword(request.getParameter("password"));

			user.setName(request.getParameter("name"));

			user.setBranchId(Integer.parseInt(request.getParameter("branchId")));

			user.setPossitionId(Integer.parseInt(request.getParameter("possitionId")));

			user.setIsStopped(0);

		if(isValid(request, messages)==true){

			new UserService().register(user);
			response.sendRedirect("usercontrol");

		}else{

			request.setAttribute("possitions", possitions);
			request.setAttribute("branches", branches);
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("signup.jsp").forward(request,response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");
		int branch = Integer.parseInt(request.getParameter("branchId"));
		int possition = Integer.parseInt(request.getParameter("possitionId"));

		User checkUser = new UserService().checkUser(loginId);

		if(StringUtils.isBlank(loginId) == true) {
			messages.add("ログインIDを入力してください");

		}else if(loginId.matches("\\w{6,20}")!=true){
			messages.add("ログインIDは6～20文字の半角英数字で入力してください");

		}else if (checkUser != null){
			messages.add("指定されたログインIDは既に使用されています");

		}
		if(StringUtils.isBlank(name) == true){
			messages.add("名前を入力してください");

		}else if(10 < name.length()) {
			messages.add("名前は10字以下で入力してください");
		}

		if(StringUtils.isBlank(password)==true){
			messages.add("パスワードを入力してください");

		}else if(((password.matches("\\w{6,20}")) !=true) ){
			messages.add("パスワードは6～20文字の半角英数字で入力してください");

		}
		if (!checkPassword.equals(password)){
			messages.add("パスワード（確認用）が正しくありません");

		}
		if(branch==0){
			messages.add("支店を選択してください");

		}
		if(possition==0){
			messages.add("役職を選択してください");
		}
		if(branch != 1 && possition <= 2){
			messages.add("支店と部署の組み合わせが正しくありません");

		}
		if(branch == 1 && possition > 2){
			messages.add("支店と部署の組み合わせが正しくありません");

		}
		if(messages.size() ==0){
			return true;
		}else{

		return false;
		}
	}
}