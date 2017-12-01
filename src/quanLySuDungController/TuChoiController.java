package quanLySuDungController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.NguoiDung;
import library.LibraryLogin;
import models.thongtindangkyModels;

/**
 * Servlet implementation class TuChoiController
 */
@WebServlet("/TuChoiController")
public class TuChoiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TuChoiController() {
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
		response.setCharacterEncoding("utf-8");
		
		int maTTDK = Integer.parseInt(request.getParameter("maTTDK"));
		String thongBao = new String(request.getParameter("thongBao").getBytes("ISO-8859-1"),"UTF-8");
		
		thongtindangkyModels mTTDK = new thongtindangkyModels();
		int[] arMaTTDK = {maTTDK};
		System.out.println(arMaTTDK[0]);
		if (mTTDK.TuChoi(arMaTTDK, thongBao) == 1) {
			response.sendRedirect(request.getContextPath() + "/qlsd-pheduyetdangky?msgtuchoi=1");
		} else {
			response.sendRedirect(request.getContextPath() + "/qlsd-pheduyetdangky?msgtuchoi=0");
		}
		
	}

}
