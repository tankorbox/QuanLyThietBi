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
import beans.ThongTinBaoDuong;
import library.LibraryLogin;
import models.baoduongModel;
import models.thietbiModels;

/**
 * Servlet implementation class BaoDuongIndexController
 */

public class BaoDuongHuyDangKyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaoDuongHuyDangKyController() {
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
		baoduongModel bdModel = new baoduongModel();
		int ttbd = Integer.parseInt(request.getParameter("ttbd"));
		ThongTinBaoDuong baoDuong = bdModel.getTTBD(ttbd);
		if(baoDuong == null) {
			response.sendRedirect(request.getContextPath()+"/baoduong?err=0");
			return;
		}
		if(baoDuong.getTinhTrang() != 1) {
			response.sendRedirect(request.getContextPath()+"/baoduong-capnhat?ttbd="+ttbd+"&err=1");
			return;
		}
	
		boolean huy = bdModel.huyDKBD(ttbd);
		response.sendRedirect(request.getContextPath()+"/baoduong?huy="+huy);
		return;
	}

}
