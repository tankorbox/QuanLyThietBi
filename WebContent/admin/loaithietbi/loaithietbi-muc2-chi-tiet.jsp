<%@page import="beans.LoaiThietBi"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/partial/header.jsp" %>
    <!-- Body -->
    
    <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="#">Thiết bị</a>
        </li>
        <li class="breadcrumb-item active">Sửa thông tin thiết bị</li>
      </ol>
      <%
      	LoaiThietBi thietbi = (LoaiThietBi) request.getAttribute("thietbi");
      if (request.getParameter("edit") != null && request.getParameter("edit").equals("1")) {%>
		<div class="alert alert-success" style="color: black">Cập nhật thành công!</div>
	  <%}
	  if (request.getParameter("edit") != null && request.getParameter("edit").equals("0")) {%>
		<div class="alert alert-danger" style="color: red">Cập nhật không thành công!</div>
	  <%}
	  %>
      <div class="table-responsive">
            <table class="table table-striped" width="100%" cellspacing="0" >
            	<tr>
            		<td>Mã loại thiêt bị</td>
            		<td><%= thietbi.getMaLoai() %></td>
            	</tr>
            	<tr>
            		<td>Tên loại thiết bị</td>
            		<td><%= thietbi.getTenLoai() %></td>
            	</tr>
            	<tr>
            		<td>Thuộc danh mục</td>
            		<td><%= thietbi.getObjLoaiCha().getTenLoai() %></td>
            	</tr>
            	<tr>
            		<td>Số lượng</td>
            		<td><%= thietbi.getSoLuong() %></td>
            	</tr>
            </table>
      </div>
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