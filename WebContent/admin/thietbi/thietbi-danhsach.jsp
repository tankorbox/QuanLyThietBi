<%@page import="beans.LoaiThietBi"%>
<%@page import="beans.ThietBi"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/partial/header.jsp"%>
<!-- Body -->
<%
	ArrayList<ThietBi> alThietBi = (ArrayList<ThietBi>) request.getAttribute("alThietBi");
%>

<div class="content-wrapper">
	<div class="container-fluid">
		<!-- Breadcrumbs-->
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#">Thiết bị</a></li>
			<li class="breadcrumb-item active">Danh sách</li>
		</ol>
		<%if (request.getParameter("addmsg") != null && request.getParameter("addmsg").equals("1")) {%>
				<div class="alert alert-success">Thêm thành công</div>
			<%}%>
		<%if (request.getParameter("delmsg") != null && request.getParameter("delmsg").equals("1")) {%>
				<div class="alert alert-success">Xóa thành công</div>
			<%}%>
		<%if (request.getParameter("delmsg") != null && request.getParameter("delmsg").equals("0")) {%>
				<div class="alert alert-danger" style="color: red">Xóa thất bại</div>
			<%}%>
		<%if (request.getParameter("editmsg") != null && request.getParameter("editmsg").equals("1")) {%>
				<div class="alert alert-success">Chỉnh sửa thành công</div>
			<%}
		if (request.getParameter("block") != null && request.getParameter("block").equals("1")) {%>
		<div class="alert alert-success" style="color: black">Cập nhật thành công!</div>
	  	<%}
	      if (request.getParameter("block") != null && request.getParameter("block").equals("0")) {%>
			<div class="alert alert-danger" style="color: red">Cập nhật không thành công!</div>
		<%}
			%>
		<div class="col-md-12" style="margin-bottom: 10px">
			<div class="col-md-6" style="margin-bottom: 10px">
				<a href="<%=request.getContextPath()%>/thietbi-them?type=load"
					class="btn btn-success">THÊM THIẾT BỊ</a> <a
					href="<%=request.getContextPath()%>/thietbi-timkiem?type=load"
					class="btn btn-primary"><i class="fa fa-search"
					aria-hidden="true"></i>TÌM KIẾM</a>
			</div>
		</div>

		<div class="table-responsive">
			<table class="table table-striped" width="100%" cellspacing="0" id="dataTable">
				<thead>
					<tr>
						<th class="text-center">#</th>
						<th class="text-center">Tên thiết bị</th>
						<th class="text-center" width="30%">Loại thiết bị</th>
						<th class="text-center" width="20%">Ngày nhập</th>
						<th class="text-center" colspan="3" width="30%">Chi tiết</th>
					</tr>
				</thead>
				<tbody>
				<% for(ThietBi item: alThietBi) { %>
					<tr>
						<td class="text-center"><%=item.getMaTB() %></td>
						<td class="text-center"><%=item.getTenTB() %></td>
						<td class="text-center"><%=item.getObjLoaiTB().getTenLoai() %></td>
						<td class="text-center"><%=item.getNgayNhap() %></td>
						<td>
							<a href="<%=request.getContextPath()%>/thietbi-sua?type=load&id=<%=item.getMaTB() %>"
							class="btn btn-primary">Sửa</a> 
							<a href="<%=request.getContextPath()%>/thietbi-chitiet?id=<%=item.getMaTB() %>"
							class="btn btn-success">Chi tiết</a>
							<a href="<%=request.getContextPath()%>/thietbi-block?maTB=<%=item.getMaTB()%>">
			                 	<%if(!item.isBlocked()) {%>
			          			<span class="btn btn-danger">Xóa</span>
			          			<%}%>
                 			</a>
					</tr>
					<%} %>
				</tbody>
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
		<%@include file="/partial/footer.jsp"%>
	</footer>
</div>