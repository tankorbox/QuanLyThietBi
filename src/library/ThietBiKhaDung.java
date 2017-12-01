package library;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import beans.ThongTinDangKy;
import models.baoduongModel;
import models.loaithietbiModels;
import models.thongtindangkyModels;
import models.thietbiModels;
import models.thongtinsudungModels;

public class ThietBiKhaDung {
	public ThietBiKhaDung() {};

	public int TinhSoLuongKhaDung(int maLoai, Timestamp batDau, Timestamp ketThuc) {
		int soLuongKhaDung = 0;
		
		//Tinh so luong kha dung
		//Tong so luong
		loaithietbiModels mLoaiTB = new loaithietbiModels();
		int tongSoLuong = mLoaiTB.getSoLuong(maLoai);
		//So thiet bi dang su dung
		thongtinsudungModels mTTSD = new thongtinsudungModels();
		int soLuongDangSuDung = mTTSD.getSoLuongDangSuDung(maLoai, batDau, ketThuc);
		System.out.println(soLuongDangSuDung+"=========sudung===================================");
		//so thiet bi dang bao duong
		baoduongModel mBaoDuong = new baoduongModel();
		int soLuongBaoDuong = mBaoDuong.getSoLuongDangBaoDuong(maLoai, batDau, ketThuc);
		System.out.println(soLuongBaoDuong+"==========baoduong==================================");
		//So luong kha dung
		soLuongKhaDung = tongSoLuong - (soLuongDangSuDung + soLuongBaoDuong);
		
		return soLuongKhaDung;
	}
	
	public ArrayList<ThongTinDangKy> PheDuyetTrung(int maTTDK, int maLoai, long batDau, long ketThuc, int soLuong) {
		thongtindangkyModels mTTDK = new thongtindangkyModels();
		//danh sách đăng ký bị trùng ngày
		ArrayList<ThongTinDangKy> alTTDK = mTTDK.getListTrung(maTTDK, maLoai, new Timestamp(batDau), new Timestamp(ketThuc));
		
		int soLuongSauPheDuyet = this.TinhSoLuongKhaDung(maLoai, new Timestamp(batDau), new Timestamp(ketThuc)) - soLuong;
		
		LibraryFormatDateTime lbDateTime = new LibraryFormatDateTime();
		for (ThongTinDangKy objTTDK : alTTDK) {
			if (objTTDK.getSoLuongDK() <= soLuongSauPheDuyet) {//loai cac thiet bi khong bi anh huong khi phe duyet
				alTTDK.remove(objTTDK);
			}
		}
		return alTTDK;
	}
}
