package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PostScreamLogic;
import model.Scream;
import model.User;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 保存のリストをアプリケーションスコープから取得
		ServletContext application = this.getServletContext();
		List<Scream> screamList = (List<Scream>) application.getAttribute("screamList");

		// リストが取得できなかった場合は新規作成してアプリケーションスコープに保存
		if(screamList == null)
		{
			screamList = new ArrayList<>();
			application.setAttribute("screamList", screamList);
		}

		// ログイン状態を確認
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if(loginUser == null)
		{
			// リダイレクト
			response.sendRedirect("/TheScream/");
		}
		else
		{
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");

		// 入力値チェック
		if(text != null && text.length() != 0)
		{
			// アプリケーションスコープに保存済みのScreamリストを取得する
			ServletContext application = this.getServletContext();
			List<Scream> screamList = (List<Scream>) application.getAttribute("screamList");

			// セッションスコープに保存済みのユーザ情報を取得する
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			// Screamをリストに追加する
			Scream scream = new Scream(loginUser.getName(), text);
			PostScreamLogic postScreamLogic = new PostScreamLogic();
			postScreamLogic.execute(scream, screamList);

			// アプリケーションスコープにScreamリストを保存
			application.setAttribute("screamList", screamList);
		}

		// メイン画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
}
