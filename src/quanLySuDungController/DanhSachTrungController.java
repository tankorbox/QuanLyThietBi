package quanLySuDungController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryLogin;

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
		LibraryLogin mLogin = new LibraryLogin();
		if(!mLogin.Login(request,response)){
			return;
		}
		int maLoai = Integer.parseInt(request.getParameter("maLoai"));
		Long batDau = Long.parseLong(request.getParameter("batDau"));
		Long ketThuc = Long.parseLong(request.getParameter("ketThuc"));
		int soLuongKhaDung = 0;
	}

}
