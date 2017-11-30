package userController;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.NguoiDung;
import library.LibraryMD5;
import models.userModels;

/**
 * Servlet implementation class IndexController
 */
public class LoginPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginPostController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tendangnhap = request.getParameter("tendangnhap");
		String matkhau = request.getParameter("matkhau");
		System.out.println(tendangnhap);
		System.out.println(matkhau);
		LibraryMD5 md5 = new LibraryMD5();
		matkhau = md5.GetStringMD5(matkhau);
		userModels mUser = new userModels();
		NguoiDung nguoidung = mUser.getUserLogin(tendangnhap,matkhau);
		if(nguoidung==null){
			response.sendRedirect(request.getContextPath()+"/login?msg=0");
			return;
		}else{
			if(nguoidung.isBlocked()) {
				response.sendRedirect(request.getContextPath()+"/login?msg=block");
				return;
			}
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(86400);
			session.setAttribute("nguoidung", nguoidung);
			response.sendRedirect(request.getContextPath()+"/index");
		}
	}

}
