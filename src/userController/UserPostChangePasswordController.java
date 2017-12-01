package userController;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.NguoiDung;
import library.LibraryLogin;
import library.LibraryMD5;
import models.userModels;

/**
 * Servlet implementation class UserCreateController
 */
public class UserPostChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPostChangePasswordController() {
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
		NguoiDung nd = new userModels().getById(objUser.getMaND());
		if(nd == null) {
			response.sendRedirect(request.getContextPath()+"/user-notfound");
			return;
		}
		String matkhau = request.getParameter("matkhau");
		String xacnhanmatkhau = request.getParameter("xacnhanmatkhau");
		if(!matkhau.equals(xacnhanmatkhau)) {
			response.sendRedirect(request.getContextPath()+"/change-password?edit=mk");
			return;
		}
		matkhau = LibraryMD5.GetStringMD5(matkhau);
		userModels uModel = new userModels();
		NguoiDung.Builder ngBuilder = new NguoiDung.Builder();
		NguoiDung nguoiDung = ngBuilder
							.setMaND(objUser.getMaND())
							.setMatKhau(matkhau)
							.build();
		int update = uModel.updatePassword(nguoiDung);
		response.sendRedirect(request.getContextPath()+"/change-password?edit="+update);
		return;
	}

}
