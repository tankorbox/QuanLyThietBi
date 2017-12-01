package thietBiController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
 * Servlet implementation class IndexController
 */
public class ThietBiIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ThietBiIndexController() {
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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LibraryLogin mLogin = new LibraryLogin();
		if (!mLogin.Login(request, response)) {
			return;
		}
		HttpSession session = request.getSession();
		NguoiDung objUser = (NguoiDung) session.getAttribute("nguoidung");
		if (objUser.getPhanQuyen() == 1) {
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		thietbiModels mThietbiModels = new thietbiModels();
		loaithietbiModels mLoaithietbiModels = new loaithietbiModels();
		if ((request.getParameter("type") != null)
				&& request.getParameter("type").equals("del")) {
			int id = Integer.parseInt(request.getParameter("id"));
			if (mThietbiModels.xoaThietBi(id) == 1) {
				response.sendRedirect(request.getContextPath()
						+ "/thietbi?delmsg=1");
			} else {
				response.sendRedirect(request.getContextPath()
						+ "/thietbi?delmsg=0");
			}
		} else {
			ArrayList<ThietBi> listThietbi = mThietbiModels.getList();
			LoaiThietBi loaiThietBi;
			for (ThietBi tb : listThietbi) {
				loaiThietBi = mLoaithietbiModels.getItemByMaLoai(tb
						.getMaLoaiTB());
				tb.setObjLoaiTB(loaiThietBi);
			}
			request.setAttribute("alThietBi", listThietbi);
			RequestDispatcher rd = request
					.getRequestDispatcher("/admin/thietbi/thietbi-danhsach.jsp");
			rd.forward(request, response);
		}
	}
}
