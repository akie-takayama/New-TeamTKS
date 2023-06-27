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

		// �����������Ȃ��悤��
		request.setCharacterEncoding("utf-8");
		
		// �t�H�[������userId��password���擾����
		String userId = request.getParameter("userId");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		
		// ���̓`�F�b�N NG�Ȃ����o�^�y�[�W�ɃG���[���b�Z�[�W�ƂƂ��ɖ߂�
		// OK�Ȃ�ASyainBean�����
		SyainBean sb = new SyainBean();
		sb.setUser_id(userId);
		sb.setEmail(mail);
		sb.setPassword(password);
		
		// SyainDao�N���X��ʂ��āA�o�^����
		SyainDao sDao;
		try {
			sDao = new SyainDao();
			
			int ret = sDao.insertSyainData( sb );	//ret��1�Ȃ�1���o�^�ł����Ƃ�������
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		
		
	}

}
