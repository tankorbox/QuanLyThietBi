package userController;

import java.io.IOException;
import java.sql.Date;

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
 * Servlet implementation class UserUpdateController
 */
public class UserUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateController() {
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
		int mand = Integer.parseInt(request.getParameter("mand"));
		userModels uModel = new userModels();
		String tennguoidung = new String(request.getParameter("tennguoidung").getBytes("ISO-8859-1"),"UTF-8");
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
		NguoiDung ngEdit = ngBuilder
							.setMaND(mand)
							.setTenND(tennguoidung)
							.setGioiTinh(gioitinh)
							.setNgaySinh(ngaysinh)
							.setChucVu(chucvu)
							.setPhongBan(phongban)
							.setPhanQuyen(phanquyen)
							.setDiaChi(diachi)
							.build();
		int updateUser = uModel.updateUser(ngEdit);
		response.sendRedirect(request.getContextPath()+"/user-edit?id="+mand+"&edit="+updateUser);
		return;
	}

}
