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
        <li class="breadcrumb-item active">Thông tin tài khoản</li>
      </ol>
	  <%
	    if (request.getParameter("update") != null && request.getParameter("update").equals("1")) 
	  {%>
		<div class="alert alert-success" style="color: white">Cập nhật thông tin thành công!</div>
	  <%}if (request.getParameter("update") != null && request.getParameter("update").equals("0"))
	  {%>
		<div class="alert alert-danger" style="color: red">Cập nhật thông tin không thành công!</div>
	  <%}%>
      <form method="post" action="<%=request.getContextPath() %>/profile">
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <label for="tendangnhap">Tên đăng nhập</label>
                <input class="form-control" type="text" name="tendangnhap" value="<%= nguoidung.getTenDangNhap() %>" disabled="disabled">
              </div>
              <div class="col-md-6">
                <label for="tennguoidung">Tên người dùng (Họ và tên)</label>
                <input class="form-control" type="text" name="tennguoidung" value="<%= nguoidung.getTenND() %>">
              </div>
            </div>
          </div>
          <div class="form-group"> 
            <div class="form-row">
              <div class="col-md-6">
                <label for="ChucVu">Chức vụ</label>
                <select class="form-control" name="ChucVu" disabled="disabled">
                	<option value="1" <% if(nguoidung.getChucVu()==1){ %>selected="selected"<%} %> >Admin</option>
                	<option value="2" <% if(nguoidung.getChucVu()==2){ %>selected="selected"<%} %> >Nhân viên</option>
                </select>
              </div>
              <div class="col-md-6">
                <label for="PhongBan">Phòng ban</label>
                <select class="form-control" name="phongban" disabled="disabled">
                	<option value="1" <% if(nguoidung.getPhongBan()==1){ %>selected="selected"<%} %> >Phòng 1</option>
                	<option value="2" <% if(nguoidung.getPhongBan()==2){ %>selected="selected"<%} %> >Phòng 2</option>
                </select>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <label for="GioiTinh">Giới tính</label>
                <select class="form-control" name="gioitinh">
                	<option value="1" <% if(nguoidung.getGioiTinh()==1){ %>selected="selected"<%} %> >Nam</option>
                	<option value="2" <% if(nguoidung.getGioiTinh()==2){ %>selected="selected"<%} %> >Nữ</option>
                </select>
              </div>
              <div class="col-md-6">
                <label for="NgaySinh">Ngày sinh</label>
                <input class="form-control" type="date" name="ngaysinh" value="<%= nguoidung.getNgaySinh() %>" />
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">      
              <div class="col-md-12">
                <label for="DiaChi">Địa chỉ</label>
                <textarea class="form-control" name="diachi" row="10"><%=nguoidung.getDiaChi() %></textarea>
              </div>
            </div>
          </div>
          <div class="form-group">
          <div class="form-row">
              <div class="col-md-4">
                <input class="form-control btn btn-warning" type="submit" name="CapNhat" value="CẬP NHẬT">
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