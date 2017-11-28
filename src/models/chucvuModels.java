package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.ChucVu;
import beans.NguoiDung;
import beans.PhongBan;
import beans.ThongTinBaoDuong;
import library.LibraryConnectDB;

public class chucvuModels {
	private Connection conn;
	private LibraryConnectDB lcdb;
	private PreparedStatement pst;
	private ResultSet rs;
	private Statement st;

	public chucvuModels() {
		lcdb = new LibraryConnectDB();
	}

	// LAY TAT CA NGUOI DUNG
	public ArrayList<ChucVu> getList() {
		ArrayList<ChucVu> alCV = new ArrayList<ChucVu>();
		conn = lcdb.GetConnectMySQL();
		String query = "SELECT * FROM ChucVu";
		ChucVu.Builder builder = new ChucVu.Builder();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				ChucVu pban = builder.setMaChucVu(rs.getInt("MaChucVu"))
						.setTenChucVu(rs.getString("TenChucVu"))
						.build();
				alCV.add(pban);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return alCV;
	}
	
	
	public ChucVu getById(int macv) {
		String sql = "SELECT * FROM ChucVu WHERE MaChucVu = " + macv;
		ChucVu objItem = null;
		conn = lcdb.GetConnectMySQL();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {

				objItem = new ChucVu.Builder()
						.setMaChucVu(rs.getInt("MaChucVu"))
						.setTenChucVu(rs.getString("TenChucVu"))
						.build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return objItem;
	}
}
