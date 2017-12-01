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
public class LoaiThietBiMuc1SuaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoaiThietBiMuc1SuaController() {
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
			if(request.getParameter("type") != null){ //click button edit
				String tenLoaiThietBi = new String(request.getParameter("tenLoaiThietBi").getBytes("ISO-8859-1"), "UTF-8");
				loaiThietBi.setTenLoai(tenLoaiThietBi);
				if(mLoaithietbiModel.updateTenMaLoaiMuc1(loaiThietBi)>0) {
					response.sendRedirect(request.getContextPath()+"/loaithietbimuc1-sua?msgEdit=1&maLoaiThietBi=" + maLoaiThietBi);
					return;
				}else {
					response.sendRedirect(request.getContextPath()+"/loaithietbimuc1-sua?msgEdit=0&maLoaiThietBi=" + maLoaiThietBi);
					return;
				}
			}else{
				request.setAttribute("objLoaiThietBiMuc1", loaiThietBi);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/loaithietbi/loaithietbi-muc1-sua.jsp");
				rd.forward(request, response);
			}
		}catch(Exception e) {
			response.sendRedirect(request.getContextPath()+"/loaithietbimuc1-danhsach?notfound=1");
			return;
		}
	}
}
