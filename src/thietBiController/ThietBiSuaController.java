package thietBiController;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LoaiThietBi;
import beans.NguoiDung;
import beans.ThietBi;
import library.LibraryLogin;
import models.loaithietbiModels;
import models.thietbiModels;

/**
 * Servlet implementation class ThietBiThemController
 */
public class ThietBiSuaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThietBiSuaController() {
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
		thietbiModels thModels = new thietbiModels();
		
		
		if (request.getParameter("type").equals("load")) {
			int id = Integer.parseInt(request.getParameter("id"));
			ThietBi thietBi = thModels.getById(id);
			ArrayList<LoaiThietBi> listLoaiTB = new loaithietbiModels().getList();
			
			ArrayList<LoaiThietBi> sortedListLoaiTB = new ArrayList<>();
			for (LoaiThietBi loaicha : listLoaiTB) {
				if (loaicha.getMaLoaiCha() == 0) {
					sortedListLoaiTB.add(loaicha);
					for (LoaiThietBi loaicon : listLoaiTB) {
						if (loaicon.getMaLoaiCha() == loaicha.getMaLoai())
							sortedListLoaiTB.add(loaicon);
					}
				}
			}
			request.setAttribute("tb", thietBi);
			request.setAttribute("listLoaiTB", sortedListLoaiTB);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/thietbi/thietbi-sua.jsp");
			rd.forward(request, response);
		}	
		else if (request.getParameter("type").equals("edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String tentb = request.getParameter("tentb");
			int maloaitb = Integer.parseInt(request.getParameter("maloaitb"));
			Date date = Date.valueOf(request.getParameter("ngaynhap"));
			ThietBi.Builder tbBuilder = new ThietBi.Builder();
			tbBuilder.setTenTB(tentb);
			tbBuilder.setMaTB(id);
			tbBuilder.setMaLoaiTB(maloaitb);
			tbBuilder.setNgayNhap(date);
			if (thModels.chinhSuaThietBi(tbBuilder.build())==1) {
				response.sendRedirect(request.getContextPath()+"/thietbi?editmsg=1");
				return;
			}
			else {
				response.sendRedirect(request.getContextPath()+"/thietbi-sua?type=load&editmsg=0");
				return;
			}
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/thietbi/thietbi-sua.jsp");
			rd.forward(request, response);
		}
	}

}
