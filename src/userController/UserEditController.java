package userController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ChucVu;
import beans.NguoiDung;
import beans.PhongBan;
import library.LibraryLogin;
import models.chucvuModels;
import models.phongbanModels;
import models.userModels;

/**
 * Servlet implementation class UserCreateController
 */
public class UserEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibraryLogin mLogin = new LibraryLogin();
		if(!mLogin.Login(request,response)){
			return;
		}
		HttpSession session = request.getSession();
		NguoiDung objUser = (NguoiDung)session.getAttribute("nguoidung");
		if(objUser.getPhanQuyen() == 1) {
			response.sendRedirect(request.getContextPath()+"/index");
			return;
		}
		int id = Integer.valueOf(request.getParameter("id"));
		NguoiDung nd = new userModels().getById(id);
		if (nd!=null) {
			ArrayList<ChucVu> listCV = new chucvuModels().getList();
			ArrayList<PhongBan> listPB = new phongbanModels().getList();
			request.setAttribute("listCV", listCV);
			request.setAttribute("listPB", listPB);
			request.setAttribute("nguoidung", nd);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/user-edit.jsp");
			rd.forward(request, response);			
		}
		else {
			response.sendRedirect(request.getContextPath()+"/user-notfound");
			return;
		}
	}

}
