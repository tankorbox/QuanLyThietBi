package library;

import java.sql.Timestamp;

import models.baoduongModel;
import models.loaithietbiModels;
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
}
