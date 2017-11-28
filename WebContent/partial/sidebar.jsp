<%@page import="beans.NguoiDung"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        <li class="nav-item active" data-toggle="tooltip" data-placement="right" title="" data-original-title="Dashboard">
          <a class="nav-link" href="index.html">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Trang chủ</span>
          </a>
        </li>
        <%
			NguoiDung ndung = (NguoiDung)session.getAttribute("nguoidung");
	        if(ndung.getPhanQuyen() == 1){
        %>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="">
          <a class="nav-link" href="<%= request.getContextPath() %>/cnnm-danhsachthietbi">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Danh sách thiết bị</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="">
          <a class="nav-link" href="<%= request.getContextPath() %>/cnnm-yeucaudagui">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Yêu cầu đã gửi</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="">
          <a class="nav-link" href="<%= request.getContextPath() %>/cnnm-thietbidangsudung">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Thiết bị đang sử dụng</span>
          </a>
        </li>
        <%} 
	        if(ndung.getPhanQuyen() == 3){
        %>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="">
          <a class="nav-link" href="<%= request.getContextPath() %>/users">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Quản lý nhân viên</span>
          </a>
        </li>
        <%}
	     if (ndung.getPhanQuyen() > 1){%>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="">
          <a class="nav-link" href="<%= request.getContextPath() %>/users">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Quản lý người dùng	</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="">
            <a class="nav-link" href="<%= request.getContextPath() %>/qlsd-pheduyetdangky">
            <i class="fa fa-fw fa-dashboard"></i><span> Phê duyệt đăng ký</span>
            </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="">
            <a class="nav-link" href="<%= request.getContextPath() %>/qlsd-capnhatsudung">
            <i class="fa fa-fw fa-dashboard"></i><span> Cập nhật sử dụng</span>
            </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="">
          <a class="nav-link" href="<%= request.getContextPath() %>/thietbi">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Quản lý thiết bị</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="">
          <a class="nav-link" href="<%= request.getContextPath() %>/baoduong">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Quản lý bảo dưỡng</span>
          </a>
        </li>
        <%} %>
      </ul>
      <ul class="navbar-nav sidenav-toggler">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler">
            <i class="fa fa-fw fa-angle-left"></i>
          </a>
        </li>
      </ul>