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
public class ThietBiBlockController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThietBiBlockController() {
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
			int maTB = Integer.parseInt(request.getParameter("maTB"));
			ThietBi thietBi = thModels.getById(maTB);
			int block = thModels.blockThietBi(thietBi);
			response.sendRedirect(request.getContextPath()+"/thietbi?block="+block);
			return;
	}

}
