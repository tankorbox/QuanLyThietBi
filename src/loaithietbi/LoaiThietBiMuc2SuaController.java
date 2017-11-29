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

public class LoaiThietBiMuc2SuaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoaiThietBiMuc2SuaController() {
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
		loaithietbiModels mLoaithietbiModel = new loaithietbiModels();
		int maloaithietbi = Integer.parseInt(request.getParameter("maloaithietbi"));
		if (request.getParameter("submit")!=null){			
			int loaithietbi = Integer.parseInt(request.getParameter("loaithietbi"));
			String tenhang = new String(request.getParameter("tenhang").getBytes("ISO-8859-1"), "UTF-8");
			int soluong = Integer.parseInt(request.getParameter("soluong"));
			LoaiThietBi.Builder builder = new LoaiThietBi.Builder();
			LoaiThietBi loaiThietBi = builder
							.setMaLoai(maloaithietbi)
							.setMaLoaiCha(loaithietbi)
							.setTenLoai(tenhang)
							.setSoLuong(soluong)
							.build();
			int result = mLoaithietbiModel.editLoaiThietBiMuc2(loaiThietBi);
			response.sendRedirect(request.getContextPath()+"/loaithietbimuc2-sua?maloaithietbi="+maloaithietbi+"&edit="+result);
			return;
		} else {
			ArrayList<LoaiThietBi> alTbM1 = mLoaithietbiModel.getListThoaiThietBiMuc1();
			request.setAttribute("alTbM1", alTbM1);
			try {
				LoaiThietBi thietbi = mLoaithietbiModel.getItemByMaLoai(maloaithietbi);
				request.setAttribute("thietbi", thietbi);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/loaithietbi/loaithietbi-muc2-sua.jsp");
				rd.forward(request, response);
			}catch(Exception e) {
				request.setAttribute("notfound",1);
				response.sendRedirect(request.getContextPath()+"/loaithietbimuc2-danhsach?notfound=1");
				return;
			}			
		}
	}

}
