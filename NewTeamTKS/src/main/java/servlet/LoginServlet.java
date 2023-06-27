
package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import dao.LoginBean;
import dao.SyainDao;
import model.SyainBean;


@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/jsp/login.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// 文字化けしないように
		request.setCharacterEncoding("utf-8");
		
		// フォームからuserIdとpasswordを取得する
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		SyainDao sDao = null;

		try {
			//SyainDaoの呼び出し（DB接続含む）
			//社員に関するデータを取り扱う場合は社員DAOを呼び出して接続する
			sDao = new SyainDao();
			
			//そして社員に関する検索をする場合は、社員DAOのメソッドでSQLを実行する
			SyainBean sb = sDao.getByIdAndPassword(userId, password);

			// ユーザー名とパスワードが一致していればデータがある
			if (sb.getUser_id() != null) {
				// ログイン成功
				// セッションにログインしたユーザーIDをセットする
				HttpSession session = request.getSession();
				session.setAttribute("loginuser", sb);
				
				// topへ遷移させる
				response.sendRedirect("top");

			} else {
				
				// エラーメッセージ
				request.setAttribute("errmessage", "UserIDかPassWordが違います。再度入力して下さい。");
				
				// ログイン失敗はログインページへ
				ServletContext context = getServletContext();
				RequestDispatcher rd = context.getRequestDispatcher("/jsp/login.jsp");
				rd.forward(request, response);
			}

		} catch (ClassNotFoundException e) {
			

		} catch (SQLException e) {
			

		} catch (IOException e) {
			

		} catch (ServletException e) {

			
		} finally {
			// DBクローズ処理
			sDao.close();
		}

	}

	private List<LoginBean> toUserList(ResultSet rs) throws SQLException {
		List<LoginBean> ret = new ArrayList<LoginBean>();
		try {
			while (rs.next()) {
				String name = rs.getString("user_id");
				LoginBean user = new LoginBean();
				user.setUserId(name);
				ret.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ret;
	}

}