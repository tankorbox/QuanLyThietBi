<%@page import="beans.LoaiThietBi"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/partial/header.jsp"%>
<!-- Body -->

<div class="content-wrapper">
	<div class="container-fluid">
		<!-- Breadcrumbs-->
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#">Loại thiết bị</a></li>
			<li class="breadcrumb-item active">Thiết bị mức 1</li>
		</ol>
		<%
		if(request.getParameter("notfound") != null){
		%>
		<div class="alert alert-danger" style="color: red">Không tìm thấy!</div>
		<%}
		if (request.getParameter("block") != null && request.getParameter("block").equals("1")) {%>
		<div class="alert alert-success" style="color: black">Loại thiêt bị này đã câp nhật!</div>
	  	<%}
	      if (request.getParameter("block") != null && request.getParameter("block").equals("0")) {%>
			<div class="alert alert-danger" style="color: red">Cập nhật không thành công!</div>
		<%}
		%>
		<div class="col-md-12" style="margin-bottom: 10px ">
			<div class="col-md-3" style="margin-bottom: 10px">
				<a href="<%=request.getContextPath()%>/loaithietbimuc1-them"
					class="btn btn-success">THÊM DANH MỤC</a> 
			</div>
		</div>

		<div class="table-responsive">
			<table class="table table-striped" width="100%" cellspacing="0">
				<thead>
					<tr>
						<th>#</th>
						<th>Tên danh mục</th>
						<th class="text-center" colspan="2">Thao tác</th>
					</tr>
				</thead>
				<tbody>
				 <% 
		try{		 
              ArrayList<LoaiThietBi> alBaoDuongMuc1 = (ArrayList<LoaiThietBi>) request.getAttribute("alLoaiThietBiMuc1");
		
              for(LoaiThietBi loaiThietBi : alBaoDuongMuc1) {%>
                <tr>
                  <td><%= loaiThietBi.getMaLoai()%></td>
                  <td><%= loaiThietBi.getTenLoai() %></td>
                  <td class="text-center">
                 		<a href="<%=request.getContextPath()%>/loaithietbimuc1-sua?maLoaiThietBi=<%=loaiThietBi.getMaLoai()%>" class="btn btn-warning">
                 		Sửa
                 		</a>
                 		<a href="<%=request.getContextPath()%>/loaithietbimuc1-block?maLoaiThietBi=<%=loaiThietBi.getMaLoai()%>">
		                 	<%if(!loaiThietBi.isBlocked()) {%>
		          			<span class="btn btn-danger">Khóa</span>
		          			<%}else{ %>
		          			<span class="btn btn-warning">Mở khóa</span>
		          			<%} %>
                 		</a>
				</td>
                </tr>
              <% }}catch(Exception e) {
      			System.out.print(e.getMessage());
      		} %>
				</tbody>

			</table>
		</div>
	</div>
	    <script type="text/javascript">
		function confirmDeleteNews(maLoaiThietBi){
            var retVal = confirm("Bạn có chắc chắn muốn xóa loại thiết bị này?");
            if( retVal == true ){
            	  window.location="<%=request.getContextPath()%>/loaithietbimuc1-sua?maLoaiThietBi=" + maLoaiThietBi;
               return true;
            }
            else{
               return false;
            }
         }
		
		
		</script>
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