<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/partial/header.jsp" %>
    <!-- Body -->
    
    <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="#">Người dùng</a>
        </li>
        <li class="breadcrumb-item active">Thêm người dùng</li>
      </ol>
      <%
      if (request.getParameter("add") != null && request.getParameter("add").equals("trung")) {%>
		<div class="alert alert-danger" style="color: red">Tên đăng nhập đã được sử dụng</div>
    <%}
      if (request.getParameter("add") != null && request.getParameter("add").equals("mk")) {%>
		<div class="alert alert-danger" style="color: red">Xác nhận mật khẩu không khớp!</div>
  <%}
      if (request.getParameter("add") != null && request.getParameter("add").equals("ns")) {%>
		<div class="alert alert-danger" style="color: red">Ngày sinh không hợp lệ!</div>
<%}
      %>
      <form method="post" action="<%= request.getContextPath() %>/user-store">
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <label for="tendangnhap">Tên đăng nhập</label>
                <input class="form-control" type="text" name="tendangnhap" required>
              </div>
              <div class="col-md-6">
                <label for="tennguoidung">Tên người dùng (Họ và tên)</label>
                <input class="form-control" type="text" name="tennguoidung" required>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <label for="MatKhau">Mật khẩu</label>
                <input class="form-control" type="password" name="matkhau" required>
              </div>
              <div class="col-md-6">
                <label for="XacNhanMatKhau">Xác nhận mật khẩu</label>
                <input class="form-control" type="password" name="xacnhanmatkhau" required>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <label for="GioiTinh">Giới tính</label>
                <select class="form-control" name="gioitinh" required>
                	<option value="1">Nam</option>
                	<option value="2">Nữ</option>
                </select>
              </div>
              <div class="col-md-6">
                <label for="ngaysinh">Ngày sinh</label>
                <input class="form-control" type="date" name="ngaysinh" required>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <label for="chucvu">Chức vụ</label>
                <select class="form-control" name="chucvu" required>
                	<option value="1">Admin</option>
                	<option value="2">Nhân viên</option>
                </select>
              </div>
              <div class="col-md-6">
                <label for="phongban">Phòng ban</label>
                <select class="form-control" name="phongban" required>
                	<option value="1">Phòng 1</option>
                	<option value="2">Phòng 2</option>
                </select>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">      
           		<div class="col-md-6">
                <label for="phanquyen">Phân quyền</label>
                <select class="form-control" name="phanquyen" required>
                	<option value="1">Người mượn</option>
                	<option value="2">Nhân Viên</option>
                </select>
              </div>
              <div class="col-md-6">
                <label for=diachi>Địa chỉ</label>
                <input class="form-control" type="text" name="diachi" required>
              </div>
            </div>
          </div>
          <div class="form-group">
          <div class="form-row">
          	  <div class="col-md-6">
                <input class="form-control btn btn-danger" type="reset" name="Reset" value="NHẬP LẠI">
              </div>
              <div class="col-md-6">
                <input class="form-control btn btn-success" type="submit" name="Them" value="THÊM">
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