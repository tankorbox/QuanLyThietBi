package userController;

import java.io.IOException;
import java.sql.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.NguoiDung;
import library.LibraryLogin;
import models.userModels;

/**
 * Servlet implementation class IndexController
 */
public class UserBlockController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserBlockController() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		userModels uModel = new userModels();
		NguoiDung nd = uModel.getById(id);
		if(nd == null ) {
			response.sendRedirect(request.getContextPath()+"/user-notfound");
			return;
		}
		NguoiDung.Builder ngBuilder = new NguoiDung.Builder();
		NguoiDung ngEdit = ngBuilder
							.setMaND(nd.getMaND())
							.setBlocked(!nd.isBlocked())
							.build();
		int updateUser = uModel.blockUser(ngEdit);
		response.sendRedirect(request.getContextPath()+"/users?block="+updateUser);
		return;
	}

}
