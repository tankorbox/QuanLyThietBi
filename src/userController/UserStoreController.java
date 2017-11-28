package userController;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.NguoiDung;
import library.LibraryLogin;
import library.LibraryMD5;
import models.userModels;

/**
 * Servlet implementation class UserStoreController
 */
public class UserStoreController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserStoreController() {
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
		String tendangnhap = request.getParameter("tendangnhap");
		//kiem tra user bi trung
		userModels uModel = new userModels();
		NguoiDung nguoiDung = uModel.getUserByTDN(tendangnhap);
		if( nguoiDung != null ) {
			response.sendRedirect(request.getContextPath()+"/user-create?add=trung");
			return;
		}
		String tennguoidung = new String(request.getParameter("tennguoidung").getBytes("ISO-8859-1"),"UTF-8");
		String matkhau = request.getParameter("matkhau");
		String xacnhanmatkhau = request.getParameter("xacnhanmatkhau");
		if(!matkhau.equals(xacnhanmatkhau)) {
			response.sendRedirect(request.getContextPath()+"/user-create?add=mk");
			return;
		}
		matkhau = LibraryMD5.GetStringMD5(matkhau);
		int gioitinh = Integer.parseInt(request.getParameter("gioitinh"));
		Date ngaysinh = Date.valueOf(request.getParameter("ngaysinh"));
		if(ngaysinh.after(new java.util.Date())) {
			response.sendRedirect(request.getContextPath()+"/user-create?add=ns");
			return;
		}
		int chucvu = Integer.parseInt(request.getParameter("chucvu"));
		int phongban = Integer.parseInt(request.getParameter("phongban"));
		int phanquyen = Integer.parseInt(request.getParameter("phanquyen"));
		String diachi = new String(request.getParameter("diachi").getBytes("ISO-8859-1"),"UTF-8");
		NguoiDung.Builder ngBuilder = new NguoiDung.Builder();
		NguoiDung ngAdd = ngBuilder
							.setTenDangNhap(tendangnhap)
							.setTenND(tennguoidung)
							.setMatKhau(matkhau)
							.setGioiTinh(gioitinh)
							.setNgaySinh(ngaysinh)
							.setChucVu(chucvu)
							.setPhongBan(phongban)
							.setPhanQuyen(phanquyen)
							.setDiaChi(diachi)
							.build();
		int addUser = uModel.addUser(ngAdd);
		response.sendRedirect(request.getContextPath()+"/users?add="+addUser);
		return;
	}

}
