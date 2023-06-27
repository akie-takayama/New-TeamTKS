package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SyainDao;
import model.SyainBean;


@WebServlet("/kaiin")
public class KaiinTorokuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/newRegister.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 文字化けしないように
		request.setCharacterEncoding("utf-8");
		
		// フォームからuserIdとpasswordを取得する
		String userId = request.getParameter("userId");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		
		// 入力チェック NGなら会員登録ページにエラーメッセージとともに戻す
		// OKなら、SyainBeanを作る
		SyainBean sb = new SyainBean();
		sb.setUser_id(userId);
		sb.setEmail(mail);
		sb.setPassword(password);
		
		// SyainDaoクラスを通じて、登録する
		SyainDao sDao;
		try {
			sDao = new SyainDao();
			
			int ret = sDao.insertSyainData( sb );	//retが1なら1件登録できたということ
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		
		
	}

}
