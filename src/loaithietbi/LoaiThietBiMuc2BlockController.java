package loaithietbi;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.loaithietbiModels;
import beans.LoaiThietBi;
import beans.NguoiDung;
import library.LibraryLogin;

/**
 * Servlet implementation class LoaiThietBiMuc1SuaController
 */
public class LoaiThietBiMuc2BlockController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoaiThietBiMuc2BlockController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
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
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		loaithietbiModels mLoaithietbiModel = new loaithietbiModels();
		int maLoaiThietBi = Integer.parseInt(request.getParameter("maLoaiThietBi"));
		try {
			LoaiThietBi loaiThietBi = mLoaithietbiModel.getItemByMaLoai(maLoaiThietBi);
			int block = mLoaithietbiModel.blockLTB(loaiThietBi,1);
			response.sendRedirect(request.getContextPath()+"/loaithietbimuc2-danhsach?block="+block);
			return;
		}catch(Exception e) {
			response.sendRedirect(request.getContextPath()+"/loaithietbimuc2-danhsach?notfound=1");
			return;
		}
	}
}
