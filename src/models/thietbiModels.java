package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import beans.ThietBi;
import library.LibraryConnectDB;

public class thietbiModels {
	private LibraryConnectDB lcdb;
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public thietbiModels() {
		this.lcdb = new LibraryConnectDB();
		this.conn = null;
	}

	// LAY TAT CA THIET BI
	public ArrayList<ThietBi> getList() {
		ArrayList<ThietBi> alTB = new ArrayList<ThietBi>();
		conn = lcdb.GetConnectMySQL();
		String query = "SELECT * FROM ThietBi WHERE isBlocked=0";
		ThietBi.Builder builder = new ThietBi.Builder();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				ThietBi thietbi = builder
						.setMaTB(rs.getInt("MaTB"))
						.setTenTB(rs.getString("TenTB"))
						.setMaLoaiTB(rs.getInt("MaLoaiTB"))
						.setNgayNhap(rs.getDate("NgayNhap"))
						.setIsBlocked(rs.getBoolean("isBlocked"))
						.build();
				alTB.add(thietbi);
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
		return alTB;
	}
	
	// LAY DANH SACH THIET BI KHA DUNG
			public ArrayList<ThietBi> getListKhaDung(int maLoaiTB) {
				ArrayList<ThietBi> alThietBi = new ArrayList<ThietBi>();
				conn = lcdb.GetConnectMySQL();
				String query = "SELECT * FROM ThietBi \n" + 
						"WHERE ThietBi.MaLoaiTB = ? AND isBlocked = 0 AND ThietBi.MaTB NOT IN \n" + 
						"	(SELECT ChiTietTTSD.MaTB FROM ChiTietTTSD \n" + 
						" 	INNER JOIN ThongTinSuDung on ThongTinSuDung.MaTTSD = ChiTietTTSD.MaTTSD\n" + 
						" 	WHERE ThongTinSuDung.TinhTrang = 2)\n" + 
						"AND ThietBi.MaTB NOT IN \n" + 
						"	(SELECT ThongTinBaoDuong.MaTB FROM ThongTinBaoDuong \n" + 
						"     INNER JOIN ThietBi ON ThongTinBaoDuong.MaTB = ThietBi.MaTB\n" + 
						"     WHERE ThongTinBaoDuong.TinhTrang <> 3 AND ThongTinBaoDuong.MaLoaiTB = ?)";
				ThietBi.Builder builder = new ThietBi.Builder();
				try {
					pst = conn.prepareStatement(query);
					pst.setInt(1, maLoaiTB);
					pst.setInt(2, maLoaiTB);
					rs = pst.executeQuery();
					while (rs.next()) {
						ThietBi objThietBi = builder.setMaTB(rs.getInt("ThietBi.MaTB"))
								.setMaLoaiTB(rs.getInt("ThietBi.MaLoaiTB"))
								.setTenTB(rs.getString("TenTB"))
								.build();
						
						alThietBi.add(objThietBi);
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
				return alThietBi;
			}

			public ThietBi getThietBi(int maTB) {
				conn = lcdb.GetConnectMySQL();
				String query = "SELECT * FROM ThietBi WHERE MaTB = ?";
				ThietBi thietBi = null;
				ThietBi.Builder builder = new ThietBi.Builder();
				try {
					pst = conn.prepareStatement(query);
					pst.setInt(1, maTB);
					rs = pst.executeQuery();
					if(rs.next()){
						thietBi = builder.setMaTB(rs.getInt("MaTB")).setTenTB(rs.getString("TenTB")).setMaLoaiTB(rs.getInt("MaLoaiTB")).setNgayNhap(rs.getDate("NgayNhap")).build();
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
				return thietBi;
			}


//TINH SO THIET BI KHA DUNG
	public int getSoLuongKhaDung(int maLoai, Timestamp batDau, Timestamp ketThuc) {
		int soLuongKhaDung = 0;
		conn = lcdb.GetConnectMySQL();
		String query = "SELECT * FROM ThongTinDangKy WHERE MaLoaiTB = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, maLoai);
			rs = pst.executeQuery();
			while (rs.next()) {

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

		return soLuongKhaDung;
	}

	// THEM MOT THIET BI MOI
	public int themThietBi(ThietBi thietBi) {
		int result = 0;
		conn = lcdb.GetConnectMySQL();
		String query = "INSERT INTO ThietBi (TenTB, MaLoaiTB, NgayNhap) VALUES (?, ?, ?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, thietBi.getTenTB());
			pst.setInt(2, thietBi.getMaLoaiTB());
			pst.setDate(3, thietBi.getNgayNhap());
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

	// CHINH SUA MOT THIET BI
	public int chinhSuaThietBi(ThietBi thietBi) {
		int result = 0;
		conn = lcdb.GetConnectMySQL();
		String query = "UPDATE ThietBi SET TenTB=?, MaLoaiTB=?,NgayNhap=? WHERE MaTB = ? LIMIT 1";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, thietBi.getTenTB());
			pst.setInt(2, thietBi.getMaLoaiTB());
			pst.setDate(3, thietBi.getNgayNhap());
			pst.setInt(4, thietBi.getMaTB());
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

	// XOA MOT THIET BI
	public int xoaThietBi(int maTB) {
		int result = 0;
		conn = lcdb.GetConnectMySQL();
		String sql = "DELETE FROM ThietBi WHERE MaTB =  ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, maTB);
			pst.executeUpdate();
			result = 1;
			System.out.println(result);
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

	// LAY THEO MA THIET BI
	public ThietBi getById(int matb) {
		String sql = "SELECT * FROM ThietBi WHERE MaTB = " + matb;
		ThietBi objItem = null;
		conn = lcdb.GetConnectMySQL();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				int MaTB = rs.getInt("MaTB");
				String TenTB = rs.getString("TenTB");
				int MaLoaiTB = rs.getInt("MaLoaiTB");
				Date NgayNhap = rs.getDate("NgayNhap");
				boolean isBlocked = rs.getBoolean("isBlocked");
				
				ThietBi.Builder tbBuilder = new ThietBi.Builder();
				tbBuilder.setMaTB(MaTB);
				tbBuilder.setTenTB(TenTB);
				tbBuilder.setMaLoaiTB(MaLoaiTB);
				tbBuilder.setNgayNhap(NgayNhap);
				tbBuilder.setIsBlocked(isBlocked);
				objItem = tbBuilder.build();
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

	// TIM KIEM THIET BI
		public ArrayList<ThietBi> search(int matb, String tentb, int maloaitb, Date ngaynhap) {
			ArrayList<ThietBi> alTB = new ArrayList<ThietBi>();
			conn = lcdb.GetConnectMySQL();
			String query = "SELECT * FROM ThietBi WHERE ";
			boolean before = false;
			if (matb!=-1) {
				before = true;
				query+= "MaTB=?";
			}
			if (tentb!=null) {
				if (before) query+=" AND ";
				query+= "TenTB LIKE ?";
				before = true;
			}
			if (maloaitb!=-1) {
				if (before) query+=" AND ";
				query+= "MaLoaiTB=?";
				before = true;
			}
			
			if (ngaynhap!=null) {
				if (before) query+=" AND ";
				query+= "NgayNhap=?";
				before = true;
			}
			ThietBi.Builder builder = new ThietBi.Builder();
			try {
				int i = 1;
				pst = conn.prepareStatement(query);
				if (matb!=-1) {
					pst.setInt(i++, matb);
				}
				if (tentb!=null) {
					pst.setString(i++,"%"+ tentb +"%");
				}
				if (maloaitb!=-1) {
					pst.setInt(i++, maloaitb);
				}
				if (ngaynhap!=null) {
					pst.setDate(i, ngaynhap);
				}
				rs = pst.executeQuery();
				while (rs.next()) {
					ThietBi thietbi = builder.setMaTB(rs.getInt("MaTB")).setTenTB(rs.getString("TenTB"))
							.setMaLoaiTB(rs.getInt("MaLoaiTB")).setNgayNhap(rs.getDate("NgayNhap")).build();
					alTB.add(thietbi);
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
			return alTB;
		}

			public ArrayList<ThietBi> getListDangKy() {
				ArrayList<ThietBi> alTB = new ArrayList<ThietBi>();
				conn = lcdb.GetConnectMySQL();
				String query = "SELECT * FROM ThietBi tb WHERE tb.MaTB NOT IN (SELECT MaTB FROM ThongTinBaoDuong WHERE TinhTrang <> 3) AND tb.isBlocked=0";
				ThietBi.Builder builder = new ThietBi.Builder();
				try {
					st = conn.createStatement();
					rs = st.executeQuery(query);
					while(rs.next()){
						
						ThietBi thietbi = builder
								.setMaTB(rs.getInt("MaTB"))
								.setTenTB(rs.getString("TenTB"))
								.setMaLoaiTB(rs.getInt("MaLoaiTB"))
								.setNgayNhap(rs.getDate("NgayNhap"))
								.setIsBlocked(rs.getBoolean("isBlocked"))
								.build();
						alTB.add(thietbi);
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
				return alTB;
			}

			public int blockThietBi(ThietBi thietBi) {
				int result = 0;
				conn = lcdb.GetConnectMySQL();
				String query = "UPDATE ThietBi SET isBlocked=? WHERE MaTB = ? LIMIT 1";
				try {
					pst = conn.prepareStatement(query);
					pst.setBoolean(1, !thietBi.isBlocked());
					pst.setInt(2, thietBi.getMaTB());
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