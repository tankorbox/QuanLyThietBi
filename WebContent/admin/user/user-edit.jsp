<%@page import="beans.PhongBan"%>
<%@page import="beans.ChucVu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.NguoiDung"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/partial/header.jsp" %>
    <!-- Body -->
    <% NguoiDung nd = (NguoiDung) request.getAttribute("nguoidung"); %>
    <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="<%=request.getContextPath()%>/users">Người dùng</a>
        </li>
        <li class="breadcrumb-item active">Cập nhật người dùng</li>
      </ol>
      <%
      if (request.getParameter("edit") != null && request.getParameter("edit").equals("1")) {%>
		<div class="alert alert-success" style="color: red">Cập nhật người dùng thành công!</div>
    <%}if (request.getParameter("edit") != null && request.getParameter("edit").equals("0")) {%>
	<div class="alert alert-danger" style="color: red">Cập nhật người dùng không thành công!</div>
<%}
  %>
      <form method="POST" action="<%=request.getContextPath()%>/user-update?mand=<%=nd.getMaND()%>">
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <label for="tendangnhap">Tên đăng nhập</label>
                <input class="form-control" disabled="disabled" type="text" name="tendangnhap" value="<%=nd.getTenDangNhap()%>">
              </div>
              <div class="col-md-6">
                <label for="tennguoidung">Tên người dùng (Họ và tên)</label>
                <input class="form-control" type="text" name="tennguoidung" value="<%=nd.getTenND()%>" required>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <label for="GioiTinh">Giới tính</label>
                <select class="form-control" name="gioitinh" required>
                	<option <%if (nd.getGioiTinh()==1) { %> selected="selected" <%} %> value="1">Nam</option>
                	<option <%if (nd.getGioiTinh()==2) { %> selected="selected" <%} %> value="2">Nữ</option>
                </select>
              </div>
              <div class="col-md-6">
                <label for="NgaySinh">Ngày sinh</label>
                <input class="form-control" type="date" name="ngaysinh" value="<%=nd.getNgaySinh()%>" required>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <label for="ChucVu">Chức vụ</label>
                <select class="form-control" name="chucvu" required>
                	<% ArrayList<ChucVu> listCV = (ArrayList<ChucVu>) request.getAttribute("listCV");
                		for (ChucVu cv: listCV) {%>
                	<option <% if (cv.getMaChucVu() ==nd.getChucVu()) {%> selected="selected" <% }%>  value="<%=cv.getMaChucVu()%>"><%=cv.getTenChucVu() %></option>
                	<%} %>
                </select>
              </div>
              <div class="col-md-6">
                <label for="PhongBan">Phòng ban</label>
                <select class="form-control" name="phongban" required>
                	<% ArrayList<PhongBan> listPB = (ArrayList<PhongBan>) request.getAttribute("listPB");
                		for (PhongBan pb: listPB) {%>
                	<option <% if (pb.getMaPhongBan() ==nd.getPhongBan()) { %> selected="selected" <%} %> value="<%=pb.getMaPhongBan()%>"><%=pb.getTenPhongBan() %></option>
                	<%} %>
                </select>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">      
           		<div class="col-md-6">
                <label for="PhanQuyen">Phân quyền</label>
                <select class="form-control" name="phanquyen" required>
                	<option <% if (nd.getPhanQuyen()==3) { %>selected="selected" <%} %> value="1">Người mượn</option>
                	<option <% if (nd.getPhanQuyen()==2) { %>selected="selected" <%} %>value="2">Nhân Viên</option>
                </select>
              </div>
              <div class="col-md-6">
                <label for="DiaChi">Địa chỉ</label>
                <input class="form-control" type="text" name="diachi" value="<%=nd.getDiaChi()%>" required>
              </div>
            </div>
          </div>
          <div class="form-group">
          <div class="form-row">
          	  <div class="col-md-4">
                <input class="form-control btn btn-danger" type="reset" name="Reset" value="NHẬP LẠI">
              </div>
              <div class="col-md-4">
                <input class="form-control btn btn-success" type="submit" name="CapNhat" value="CẬP NHẬT">
              </div>
              <div class="col-md-4">
                <a href="<%=request.getContextPath()%>/user/reset-password?mand=<%=nd.getMaND()%>" class="btn btn-warning form-control">CẤP MẬT KHẨU MỚI</a>
              </div>
          </div>
          </div>
        </form>
    </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
    <footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
          <small>Copyright © Your Website 2017</small>
        </div>
      </div>
    
    <!-- Start footer -->
    <%@include file="/partial/footer.jsp" %>