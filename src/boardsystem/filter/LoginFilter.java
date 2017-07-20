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


@WebFilter("/*")
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException{

		//対象のサーブレットパスを文字列として取得
		String url = ((HttpServletRequest) request).getServletPath();
		HttpSession session = ((HttpServletRequest)request).getSession();
		User user = (User)session.getAttribute("loginUser");

		List<String> messages = new ArrayList<String>();

		if(url.equals("/login") || url.equals("/login.jsp") || url.contains("css")){
			chain.doFilter(request, response);   //条件と一致した場合サーブレッドの実行

		}else{
			if(user == null){
				System.out.println(url);
				messages.add("ログインしてください");
				session.setAttribute("errorMessages", messages);
				((HttpServletResponse) response).sendRedirect("login");

			}else{
				//アカウント停止チェック]
				User loginCheck = new UserService().getUser(user.getId());
				if(loginCheck.getIsStopped()==0){
					chain.doFilter(request, response);
				}else{

					messages.add("現在このアカウントは使用できません");
					session.setAttribute("errorMessages", messages);
					((HttpServletResponse) response).sendRedirect("login");
				}
			}
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
