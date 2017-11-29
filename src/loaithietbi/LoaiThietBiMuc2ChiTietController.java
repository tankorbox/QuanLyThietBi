package loaithietbi;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class LoaiThietBiMuc1Them
 */

public class LoaiThietBiMuc2ChiTietController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoaiThietBiMuc2ChiTietController() {
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
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		
		
		int maloaithietbi = Integer.parseInt(request.getParameter("maloaithietbi"));
		loaithietbiModels mLoaithietbiModel = new loaithietbiModels();
		try {
			LoaiThietBi thietbi = mLoaithietbiModel.getItemByMaLoai(maloaithietbi);
			request.setAttribute("thietbi", thietbi);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/loaithietbi/loaithietbi-muc2-chi-tiet.jsp");
			rd.forward(request, response);
		}catch(Exception e) {
			request.setAttribute("notfound",1);
			response.sendRedirect(request.getContextPath()+"/loaithietbimuc2-danhsach?notfound=1");
			return;
		}
		
	}

}
