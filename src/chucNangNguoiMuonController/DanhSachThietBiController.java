package chucNangNguoiMuonController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryLogin;
import models.loaithietbiModels;
import models.thietbiModels;
import beans.LoaiThietBi;;
/**
 * Servlet implementation class DanhSachThietBiController
 */
public class DanhSachThietBiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DanhSachThietBiController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibraryLogin mLogin = new LibraryLogin();
		if(!mLogin.Login(request,response)){
			return;
		}
		loaithietbiModels mLoaiThietBi = new loaithietbiModels();
		ArrayList<LoaiThietBi> alTB2 = mLoaiThietBi.getList();
		for (LoaiThietBi loaiThietBi : alTB2) {
			if(loaiThietBi.getMaLoaiCha() != 0) {
				LoaiThietBi tb1 = mLoaiThietBi.getThietBiMuc1(loaiThietBi.getMaLoaiCha());
				System.out.println(tb1.toString());
				loaiThietBi.setObjLoaiCha(tb1);
			}
		}
		request.setAttribute("alLoaiThietBi", alTB2);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/chucnangnguoimuon/danhsachthietbi.jsp");
		rd.forward(request, response);
	}
}
