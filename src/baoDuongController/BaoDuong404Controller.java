package baoDuongController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.NguoiDung;
import library.LibraryLogin;

/**
 * Servlet implementation class BaoDuongChiTietController
 */
public class BaoDuong404Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaoDuong404Controller() {
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
		RequestDispatcher rd = request.getRequestDispatcher("/admin/quanlybaoduong/not-found.jsp");
		rd.forward(request, response);
	}

}
