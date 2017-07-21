
package boardsystem.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boardsystem.beans.User;
import boardsystem.service.UserService;


@WebFilter({"/usercontrol","/useredit","/signup"})
public class AccessFilter implements Filter {


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();

		List<String> messages = new ArrayList<String>();

		//ログインユーザーを取得
		User user = (User)session.getAttribute("loginUser");

		if(user== null){
			messages.add("ログインしてください");
			session.setAttribute("errorMessages", messages);
			((HttpServletResponse) response).sendRedirect("login");

			return;
		}else{

			User accessCheck = new UserService().getUser(user.getId());
				//本部人事総務部以外でエラーメッセージを表示
			if(!(accessCheck.getBranchId()==1 && accessCheck.getPossitionId()==1)){

				messages.add("アクセス権限がありません");
				session.setAttribute("errorMessages",messages);
				((HttpServletResponse) response).sendRedirect("./");

				return;
			}

		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}
