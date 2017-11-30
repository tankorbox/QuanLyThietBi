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
        <li class="breadcrumb-item active">Cấp lại mật khẩu</li>
      </ol>
		<%
		if (request.getParameter("edit") != null && request.getParameter("edit").equals("mk")) {%>
		<div class="alert alert-danger" style="color: red">Xác nhận mật khẩu không khớp!</div>
	<%}
		if (request.getParameter("edit") != null && request.getParameter("edit").equals("1")) {%>
		<div class="alert alert-success" style="color: black">Cập nhật mật khẩu thành công!</div>
	<%}
	  %>
      <form method="post" action="<%= request.getContextPath() %>/update-password">
          <div class="form-group">
              <div class="col-md-6">
                <label for="MatKhau">Mật khẩu mới</label>
                <input class="form-control" type="password" name="matkhau" required>
              </div>
          </div>
          <div class="form-group">
            <div class="col-md-6">
                <label for="XacNhanMatKhau">Xác nhận mật khẩu</label>
                <input class="form-control" type="password" name="xacnhanmatkhau" required>
              </div>
          </div>
          <div class="form-group">
          <div class="form-row">
              <div class="col-md-6">
                <input class="form-control btn btn-success" type="submit" name="capnhat" value="CẬP NHẬT">
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