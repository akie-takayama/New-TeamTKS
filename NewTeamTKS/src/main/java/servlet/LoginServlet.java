
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

		// �����������Ȃ��悤��
		request.setCharacterEncoding("utf-8");
		
		// �t�H�[������userId��password���擾����
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		SyainDao sDao = null;

		try {
			//SyainDao�̌Ăяo���iDB�ڑ��܂ށj
			//�Ј��Ɋւ���f�[�^����舵���ꍇ�͎Ј�DAO���Ăяo���Đڑ�����
			sDao = new SyainDao();
			
			//�����ĎЈ��Ɋւ��錟��������ꍇ�́A�Ј�DAO�̃��\�b�h��SQL�����s����
			SyainBean sb = sDao.getByIdAndPassword(userId, password);

			// ���[�U�[���ƃp�X���[�h����v���Ă���΃f�[�^������
			if (sb.getUser_id() != null) {
				// ���O�C������
				// �Z�b�V�����Ƀ��O�C���������[�U�[ID���Z�b�g����
				HttpSession session = request.getSession();
				session.setAttribute("loginuser", sb);
				
				// top�֑J�ڂ�����
				response.sendRedirect("top");

			} else {
				
				// �G���[���b�Z�[�W
				request.setAttribute("errmessage", "UserID��PassWord���Ⴂ�܂��B�ēx���͂��ĉ������B");
				
				// ���O�C�����s�̓��O�C���y�[�W��
				ServletContext context = getServletContext();
				RequestDispatcher rd = context.getRequestDispatcher("/jsp/login.jsp");
				rd.forward(request, response);
			}

		} catch (ClassNotFoundException e) {
			

		} catch (SQLException e) {
			

		} catch (IOException e) {
			

		} catch (ServletException e) {

			
		} finally {
			// DB�N���[�Y����
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