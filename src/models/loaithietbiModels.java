package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.LoaiThietBi;
import beans.ThietBi;
import library.LibraryConnectDB;

public class loaithietbiModels {
	private LibraryConnectDB lcdb;
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	public loaithietbiModels() {
		this.lcdb = new LibraryConnectDB();
		this.conn = null;
	}

	// LAY TAT CA LOAI THIET BI
	public ArrayList<LoaiThietBi> getList() {
		ArrayList<LoaiThietBi> alLoaiTB = new ArrayList<LoaiThietBi>();
		conn = lcdb.GetConnectMySQL();
		String query = "SELECT * FROM LoaiTB";
		LoaiThietBi.Builder builder = new LoaiThietBi.Builder();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				LoaiThietBi loaiTB = builder.setMaLoai(rs.getInt("MaLoai"))
						.setTenLoai(rs.getString("TenLoai"))
						.setMaLoaiCha(rs.getInt("MaLoaiCha"))
						.setSoLuong(rs.getInt("SoLuong")).build();
				alLoaiTB.add(loaiTB);
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
		return alLoaiTB;
	}

	// lay loai thiet bi muc 1
	public ArrayList<LoaiThietBi> getListThoaiThietBiMuc1() {
		ArrayList<LoaiThietBi> alLoaiTB = new ArrayList<LoaiThietBi>();
		conn = lcdb.GetConnectMySQL();
		String query = "SELECT * FROM LoaiTB WHERE MaLoaiCha is null";
		LoaiThietBi.Builder builder = new LoaiThietBi.Builder();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				LoaiThietBi loaiTB = builder.setMaLoai(rs.getInt("MaLoai"))
						.setTenLoai(rs.getString("TenLoai"))
						.setMaLoaiCha(rs.getInt("MaLoaiCha"))
						.setSoLuong(rs.getInt("SoLuong")).build();
				alLoaiTB.add(loaiTB);
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
		return alLoaiTB;
	}

	// lay loai thiet bi muc 2
	public ArrayList<LoaiThietBi> getListThoaiThietBiMuc2(int maLoaiCha) {
		ArrayList<LoaiThietBi> alLoaiTB = new ArrayList<LoaiThietBi>();
		conn = lcdb.GetConnectMySQL();
		String query = "SELECT * FROM LoaiTB WHERE MaLoaiCha = " + maLoaiCha;
		LoaiThietBi.Builder builder = new LoaiThietBi.Builder();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				LoaiThietBi loaiTB = builder.setMaLoai(rs.getInt("MaLoai"))
						.setTenLoai(rs.getString("TenLoai"))
						.setMaLoaiCha(rs.getInt("MaLoaiCha"))
						.setSoLuong(rs.getInt("SoLuong")).build();
				alLoaiTB.add(loaiTB);
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
		return alLoaiTB;
	}

	public Map<LoaiThietBi, List<LoaiThietBi>> getMapLoaiThietBiMuc2() {
		Map<LoaiThietBi, List<LoaiThietBi>> loaiThietiMuc2Map = new HashMap<LoaiThietBi, List<LoaiThietBi>>();
		List<LoaiThietBi> loaiThietBiMuc1List = getListThoaiThietBiMuc1();

		for (LoaiThietBi loaiThietBi : loaiThietBiMuc1List) {
			loaiThietiMuc2Map.put(loaiThietBi,
					getListThoaiThietBiMuc2(loaiThietBi.getMaLoai()));
		}

		return loaiThietiMuc2Map;
	}

	// them
	public int addLoaiThietBi(LoaiThietBi loaiThietBi) {
		conn = lcdb.GetConnectMySQL();
		int result = 0;
		String query = "INSERT INTO LoaiTB(TenLoai) VALUES (?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, loaiThietBi.getTenLoai());
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

	// LAY LOAI THIET BI THEO MALOAI
	public LoaiThietBi getItemByMaLoai(int maLoai) {
		LoaiThietBi.Builder builder = new LoaiThietBi.Builder();
		LoaiThietBi loaiTB = null, loaiCha = null;
		conn = lcdb.GetConnectMySQL();
		String query = "SELECT * FROM LoaiTB WHERE MaLoai in (SELECT MaLoaiCha FROM LoaiTB WHERE LoaiTB.MaLoai=?) OR MaLoai=?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, maLoai);
			pst.setInt(2, maLoai);
			rs = pst.executeQuery();
			while (rs.next()) {
				if (rs.getInt("MaLoai") == maLoai) { // Doi tuong con
					loaiTB = builder.setMaLoai(rs.getInt("MaLoai"))
							.setTenLoai(rs.getString("TenLoai"))
							.setMaLoaiCha(rs.getInt("MaLoaiCha")).build();
				} else { // Doi tuong cha
					loaiCha = builder.setMaLoai(rs.getInt("MaLoai"))
							.setTenLoai(rs.getString("TenLoai"))
							.setMaLoaiCha(rs.getInt("MaLoaiCha")).build();
				}
			}
			loaiTB.setObjLoaiCha(loaiCha);
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
		return loaiTB;
	}

	// LAY SO LUONG THEO MA LOAI
	public int getSoLuong(int maLoai) {
		int soLuong = 0;
		conn = lcdb.GetConnectMySQL();
		String query = "SELECT SoLuong FROM LoaiTB WHERE MaLoai = ? LIMIT 1";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, maLoai);
			rs = pst.executeQuery();
			while (rs.next()) {
				soLuong = rs.getInt("SoLuong");
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
		return soLuong;
	}

	public int updateTenMaLoaiMuc1(LoaiThietBi loaiThietBi) {
		int result = 0;
		conn = lcdb.GetConnectMySQL();
		String query = "UPDATE LoaiTB SET TenLoai= ? WHERE MaLoai = ? LIMIT 1";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, loaiThietBi.getTenLoai());
			pst.setInt(2, loaiThietBi.getMaLoai());
			pst.executeUpdate();
			result = 1;
			System.out.println(loaiThietBi.getTenLoai() + " "
					+ loaiThietBi.getMaLoai());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}