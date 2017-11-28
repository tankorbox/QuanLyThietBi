package baoDuongController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.NguoiDung;
import beans.ThietBi;
import library.LibraryLogin;
import models.thietbiModels;

/**
 * Servlet implementation class BaoDuongIndexController
 */

public class BaoDuongThemThietBiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaoDuongThemThietBiController() {
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
		//lay danh sach thiet bi dang bao duong
		//chua co trong bang dang ky
		//da tung bao duong <=> tinh trang = 3
		thietbiModels tbModel = new thietbiModels();
		ArrayList<ThietBi> alThietBi = tbModel.getListDangKy();
				
		request.setAttribute("alThietBi", alThietBi);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/quanlybaoduong/baoduong-themthietbi.jsp");
		rd.forward(request, response);
	}

}
