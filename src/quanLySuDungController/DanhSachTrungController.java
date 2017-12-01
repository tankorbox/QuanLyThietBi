package quanLySuDungController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.NguoiDung;
import beans.ThongTinDangKy;
import library.LibraryFormatDateTime;
import library.LibraryLogin;
import library.ThietBiKhaDung;
import models.thongtindangkyModels;

/**
 * Servlet implementation class DanhSachTrungController
 */
@WebServlet("/DanhSachTrungController")
public class DanhSachTrungController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DanhSachTrungController() {
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
		response.setCharacterEncoding("utf-8");
		
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
		
		int maTTDK = Integer.parseInt(request.getParameter("maTTDK"));
		int maLoai = Integer.parseInt(request.getParameter("maLoai"));
		Long batDau = Long.parseLong(request.getParameter("batDau"));
		Long ketThuc = Long.parseLong(request.getParameter("ketThuc"));
		int soLuong = Integer.parseInt(request.getParameter("soLuong"));
		
		ThietBiKhaDung ttkd = new ThietBiKhaDung();
		ArrayList<ThongTinDangKy> alTTDK = ttkd.PheDuyetTrung(maTTDK, maLoai, batDau, ketThuc, soLuong);
		
		PrintWriter out = response.getWriter();
		LibraryFormatDateTime lbDateTime = new LibraryFormatDateTime();
		if(alTTDK.size()>0) {
			for (ThongTinDangKy objTTDK : alTTDK) {
					out.println("<tr>");
			        out.println("<td class=\"text-center\">" + objTTDK.getMaTTDK() +"</td>");
			        out.println("<td>" + lbDateTime.TimestamptoString(objTTDK.getDKBatDauSuDung()) + "</td>");
			        out.println("<td>" + lbDateTime.TimestamptoString(objTTDK.getDKKetThucSuDung()) + "</td>");
			        out.println("<td class=\"text-center\"><button type=\"button\" class=\"btn btn-outline-info\"" +
						"data-toggle=\"modal\" data-target=\"#ModelChiTiet\"" +
						"onclick=\"ChiTiet(" + objTTDK.getMaNguoiMuon() +", '" + objTTDK.getObjNguoiDung().getTenND() + "', '" + objTTDK.getObjNguoiDung().getObjPhongBan().getTenPhongBan() + "', '" + objTTDK.getObjNguoiDung().getObjChucVu().getTenChucVu() + "', '" + objTTDK.getObjLoaiTB().getTenLoai() + "', '" + objTTDK.getObjLoaiTB().getObjLoaiCha().getTenLoai() + "', " + objTTDK.getSoLuongDK() + ", '" + lbDateTime.TimestamptoString(objTTDK.getThoiGianDangKy()) + "', '" + lbDateTime.TimestamptoString(objTTDK.getDKBatDauSuDung()) + "', '" + lbDateTime.TimestamptoString(objTTDK.getDKKetThucSuDung()) + "', '" + objTTDK.getMucDichSuDung() + "');\">"
						+ "Chi tiết</button></td>");
			      out.println("</tr>");
			}
		}else {
			out.println("<tr>");
			out.print("<td colspan=\"4\" class=\"text-center\">Không có đăng ký bị ảnh hưởng</td>");
			out.println("</tr>");
		}
	}

}
