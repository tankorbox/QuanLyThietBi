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
 * Servlet implementation class LoaiThietBiMuc1Them
 */

public class LoaiThietBiMuc1ThemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoaiThietBiMuc1ThemController() {
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
		
		
		if (request.getParameter("type")!=null){
			loaithietbiModels mLoaithietbiModel = new loaithietbiModels();
			String tenLoaiThietBi = new String(request.getParameter("tenLoaiThietBi").getBytes("ISO-8859-1"), "UTF-8");
			LoaiThietBi.Builder builder = new LoaiThietBi.Builder();
			LoaiThietBi loaiThietBi = builder.setTenLoai(tenLoaiThietBi).build();
			int result = mLoaithietbiModel.addLoaiThietBi(loaiThietBi);
			if (result > 0){
				response.sendRedirect(request.getContextPath()+"/loaithietbimuc1-them?msgAdd=1");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/loaithietbimuc1-them?msgAdd=0");
				return;
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/loaithietbi/loaithietbi-muc1-them.jsp");
			rd.forward(request, response);
		}
	}

}
