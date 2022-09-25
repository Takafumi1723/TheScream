package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		User user = new User(name, pass);

		LoginLogic l = new LoginLogic();
		boolean isLogin = l.excuteLogin(user);

		if(isLogin)
		{
			//ユーザ情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
		}
//		else
//		{
//			HttpSession session = request.getSession();
//			if(session.getAttribute("loginUser") != null)
//			{
//				session.removeAttribute("loginUser");
//			}
//		}
		RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
		disp.forward(request, response);;
	}

}
