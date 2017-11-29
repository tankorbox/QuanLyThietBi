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
import models.userModels;

/**
 * Servlet implementation class UserProfileController
 */
public class UserPostProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPostProfileController() {
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
		userModels uModel = new userModels();
		String tennguoidung = new String(request.getParameter("tennguoidung").getBytes("ISO-8859-1"),"UTF-8");
		int gioitinh = Integer.parseInt(request.getParameter("gioitinh"));
		Date ngaysinh = Date.valueOf(request.getParameter("ngaysinh"));
		if(ngaysinh.after(new java.util.Date())) {
			response.sendRedirect(request.getContextPath()+"/user-create?add=ns");
			return;
		}
		String diachi = new String(request.getParameter("diachi").getBytes("ISO-8859-1"),"UTF-8");
		NguoiDung.Builder ngBuilder = new NguoiDung.Builder();
		NguoiDung ngEdit = ngBuilder
							.setMaND(objUser.getMaND())
							.setTenND(tennguoidung)
							.setGioiTinh(gioitinh)
							.setNgaySinh(ngaysinh)
							.setDiaChi(diachi)
							.setChucVu(objUser.getChucVu())
							.setPhongBan(objUser.getPhongBan())
							.setPhanQuyen(objUser.getPhanQuyen())
							.build();
		int updateUser = uModel.updateUser(ngEdit);
		response.sendRedirect(request.getContextPath()+"/user-profile?update="+updateUser);
		return;
	}

}
