package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.NguoiDung;
import beans.ThietBi;
import library.LibraryConnectDB;

public class userModels {
	private LibraryConnectDB lcdb;
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	public userModels() {
		this.lcdb = new LibraryConnectDB();
		this.conn = null;
	}

	// LAY TAT CA NGUOI DUNG
	public ArrayList<NguoiDung> getList(int phanquyen) {
		int select = phanquyen - 1;
		ArrayList<NguoiDung> alND = new ArrayList<NguoiDung>();
		conn = lcdb.GetConnectMySQL();
		String query = "SELECT * FROM NguoiDung WHERE PhanQuyen="+select;
		NguoiDung.Builder builder = new NguoiDung.Builder();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				NguoiDung nguoidung = builder.setMaND(rs.getInt("MaND"))
						.setChucVu(rs.getInt("ChucVu"))
						.setDiaChi(rs.getString("DiaChi"))
						.setGioiTinh(rs.getInt("GioiTinh"))
						.setMatKhau(rs.getString("MatKhau"))
						.setNgaySinh(rs.getDate("NgaySinh"))
						.setPhanQuyen(rs.getInt("PhanQuyen"))
						.setPhongBan(rs.getInt("PhongBan"))
						.setTenDangNhap(rs.getString("TenDangNhap"))
						.setTenND(rs.getString("TenND"))
						.setBlocked(rs.getBoolean("isBlocked"))
						.build();
				alND.add(nguoidung);
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
		return alND;
	}

	// XOA MOT THIET BI
	public int deleteById(int mand) {
		int result = 0;
		conn = lcdb.GetConnectMySQL();
		String sql = "DELETE FROM NguoiDung WHERE MaND =  ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, mand);
			pst.executeUpdate();
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	//LAY THEO MA NGUOI DUNG
	public NguoiDung getById(int mand) {
		String sql = "SELECT * FROM NguoiDung WHERE MaND = " + mand;
		NguoiDung objItem = null;
		conn = lcdb.GetConnectMySQL();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {

				objItem = new NguoiDung.Builder()
						.setMaND(rs.getInt("MaND"))
						.setChucVu(rs.getInt("ChucVu"))
						.setDiaChi(rs.getString("DiaChi"))
						.setGioiTinh(rs.getInt("GioiTinh"))
						.setMatKhau(rs.getString("MatKhau"))
						.setNgaySinh(rs.getDate("NgaySinh"))
						.setPhanQuyen(rs.getInt("PhanQuyen"))
						.setPhongBan(rs.getInt("PhongBan"))
						.setTenDangNhap(rs.getString("TenDangNhap"))
						.setTenND(rs.getString("TenND"))
						.setBlocked(rs.getBoolean("isBlocked"))
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

	public NguoiDung getUserByTDN(String tendangnhap) {
		String sql = "SELECT MaND FROM NguoiDung WHERE TenDangNhap=?";
		NguoiDung objItem = null;
		conn = lcdb.GetConnectMySQL();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, tendangnhap);
			rs = pst.executeQuery();
			if(rs.next()) {
				objItem = new NguoiDung.Builder()
						.setMaND(rs.getInt("MaND"))
						.build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return objItem;
	}

	public int addUser(NguoiDung ngAdd) {
		int result = 0;
		conn = lcdb.GetConnectMySQL();
		String sql = "INSERT INTO NguoiDung(TenND,TenDangNhap,MatKhau,GioiTinh,NgaySinh,DiaChi,ChucVu,PhongBan,PhanQuyen)"
				+ " VALUES(?,?,?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, ngAdd.getTenND());
			pst.setString(2, ngAdd.getTenDangNhap());
			pst.setString(3, ngAdd.getMatKhau());
			pst.setInt(4, ngAdd.getGioiTinh());
			pst.setDate(5, ngAdd.getNgaySinh());
			pst.setString(6, ngAdd.getDiaChi());
			pst.setInt(7, ngAdd.getChucVu());
			pst.setInt(8, ngAdd.getPhongBan());
			pst.setInt(9, ngAdd.getPhanQuyen());
			pst.executeUpdate();
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateUser(NguoiDung ngEdit) {
		int result = 0;
		conn = lcdb.GetConnectMySQL();
		String sql = "UPDATE NguoiDung SET TenND=?,GioiTinh=?,NgaySinh=?,ChucVu=?,PhongBan=?,PhanQuyen=?,DiaChi=? WHERE MaND=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, ngEdit.getTenND());
			pst.setInt(2, ngEdit.getGioiTinh());
			pst.setDate(3, ngEdit.getNgaySinh());
			pst.setString(7, ngEdit.getDiaChi());
			pst.setInt(4, ngEdit.getChucVu());
			pst.setInt(5, ngEdit.getPhongBan());
			pst.setInt(6, ngEdit.getPhanQuyen());
			pst.setInt(8, ngEdit.getMaND());
			pst.executeUpdate();
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updatePassword(NguoiDung nguoiDung) {
		int result = 0;
		conn = lcdb.GetConnectMySQL();
		String sql = "UPDATE NguoiDung SET MatKhau=? WHERE MaND=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, nguoiDung.getMatKhau());
			pst.setInt(2, nguoiDung.getMaND());
			pst.executeUpdate();
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public NguoiDung getUserLogin(String tendangnhap, String matkhau) {
		NguoiDung nguoidung = null;
		conn = lcdb.GetConnectMySQL();
		String query = "SELECT * FROM NguoiDung WHERE TenDangNhap = ? AND MatKhau = ? LIMIT 1";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, tendangnhap);
			pst.setString(2, matkhau);
			rs = pst.executeQuery();
			if(rs.next()){
				nguoidung = new NguoiDung.Builder()
						.setMaND(rs.getInt("MaND"))
						.setChucVu(rs.getInt("ChucVu"))
						.setDiaChi(rs.getString("DiaChi"))
						.setGioiTinh(rs.getInt("GioiTinh"))
						.setMatKhau(rs.getString("MatKhau"))
						.setNgaySinh(rs.getDate("NgaySinh"))
						.setPhanQuyen(rs.getInt("PhanQuyen"))
						.setPhongBan(rs.getInt("PhongBan"))
						.setTenDangNhap(rs.getString("TenDangNhap"))
						.setTenND(rs.getString("TenND"))
						.setBlocked(rs.getBoolean("isBlocked"))
						.build();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return nguoidung;
	}

	public int blockUser(NguoiDung ngEdit) {
		int result = 0;
		conn = lcdb.GetConnectMySQL();
		String sql = "UPDATE NguoiDung SET isBlocked=? WHERE MaND=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setBoolean(1, ngEdit.isBlocked());
			pst.setInt(2, ngEdit.getMaND());
			pst.executeUpdate();
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}