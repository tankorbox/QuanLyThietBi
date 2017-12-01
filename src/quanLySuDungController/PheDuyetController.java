package quanLySuDungController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.NguoiDung;
import beans.ThongTinDangKy;
import beans.ThongTinDangKy.Builder;
import beans.ThongTinSuDung;
import library.LibraryFormatDateTime;
import library.LibraryLogin;
import library.ThietBiKhaDung;
import models.thietbiModels;
import models.thongtindangkyModels;
import models.thongtinsudungModels;

/**
 * Servlet implementation class PheDuyetController
 */
@WebServlet("/PheDuyetController")
public class PheDuyetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PheDuyetController() {
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
		thongtindangkyModels mTTDK = new thongtindangkyModels();
		thongtinsudungModels mTTSD = new thongtinsudungModels();
		ThietBiKhaDung khadung = new ThietBiKhaDung();
		LibraryFormatDateTime lbDateTime = new LibraryFormatDateTime();
		
		int maTTDK =Integer.parseInt(request.getParameter("maTTDK"));
		int maNguoiMuon = Integer.parseInt(request.getParameter("maNguoiMuon"));
		Long dkKetThucSuDung = Long.parseLong(request.getParameter("dkKetThucSuDung"));
		Long dkBatDauSuDung = Long.parseLong(request.getParameter("dkBatDauSuDung"));
		int maLoai = Integer.parseInt(request.getParameter("maLoai"));
		int soLuong = Integer.parseInt(request.getParameter("soLuong"));
		
		int maNguoiPheDuyet = objUser.getMaND();
		
		//Từ chối các đăng ký bị trùng
		ArrayList<ThongTinDangKy> alTuChoi = khadung.PheDuyetTrung(maTTDK, maLoai, dkBatDauSuDung, dkKetThucSuDung, soLuong);
		if(alTuChoi.size() > 0) {
			String thongBao = "Đã có đơn đăng ký khác được phê duyệt và đơn đăng ký của bạn "
					+ "không đảm bảo số lượng thiết bị trong thời gian bạn đăng ký sử dụng.";
			int[] arMaTTDK = new int[alTuChoi.size()];
			for (int i=0; i<alTuChoi.size(); i++) {
				arMaTTDK[i] = alTuChoi.get(i).getMaTTDK();
			}
			mTTDK.TuChoi(arMaTTDK, thongBao);
		}
		
		//cập nhật TTDK
		mTTDK.SuaTinhTrang(2, maTTDK);
		
		//Thêm sử dụng mới vào bảng TTSD
		ThongTinSuDung.Builder TTSDBuilder = new ThongTinSuDung.Builder();
		ThongTinSuDung objTTSD = TTSDBuilder.setMaTTDK(maTTDK)
				.setMaNguoiMuon(maNguoiMuon)
				.setMaNguoiPheDuyet(maNguoiPheDuyet)
				.setBatDauSuDung(new Timestamp(dkBatDauSuDung))
				.setKetThucSuDung(new Timestamp(dkKetThucSuDung))
				.build();
				
		if (mTTSD.ThemSuDungMoi(objTTSD) == 1) {
			response.sendRedirect(request.getContextPath() + "/qlsd-pheduyetdangky?msgpheduyet=1");
		} else {
			response.sendRedirect(request.getContextPath() + "/qlsd-pheduyetdangky?msgpheduyet=0");
		}
	}

}
