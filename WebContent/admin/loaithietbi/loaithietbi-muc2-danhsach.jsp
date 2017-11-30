<%@page import="beans.LoaiThietBi"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/partial/header.jsp" %>
    <!-- Body -->
    
    <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#">Loại thiết bị</a></li>
			<li class="breadcrumb-item active">Thiết bị mức 2</li>
		</ol>
		<%
		if(request.getParameter("notfound") != null){
		%>
		<div class="alert alert-danger" style="color: red">Không tìm thấy!</div>
		<%}
      if (request.getParameter("add") != null && request.getParameter("add").equals("1")) {%>
		<div class="alert alert-success" style="color: black">Thêm thành công!</div>
  <%}
      if (request.getParameter("add") != null && request.getParameter("add").equals("0")) {%>
		<div class="alert alert-danger" style="color: red">Thêm không thành công!</div>
<%}
      if (request.getParameter("block") != null && request.getParameter("block").equals("1")) {%>
		<div class="alert alert-success" style="color: black">Loại thiêt bị này đã câp nhật!</div>
	  	<%}
	      if (request.getParameter("block") != null && request.getParameter("block").equals("0")) {%>
			<div class="alert alert-danger" style="color: red">Cập nhật không thành công!</div>
		<%}
      %>
      <div class="col-md-12" style="margin-bottom: 10px">
      	<div class="col-md-6" style="margin-bottom: 10px">
      			<a href="<%=request.getContextPath()%>/loaithietbimuc2-them" class="btn btn-success">THÊM THIẾT BỊ</a>
      	</div>
      </div>
  
      <div class="table-responsive">
            <table class="table table-striped" width="100%" cellspacing="0" >
              <thead>
                <tr>
                  <th>#</th>
                  <th>Tên tiểu mục</th>
                  <th>Thuộc danh mục</th>
                  <th>Số lượng</th>
                  <th class="text-center" colspan="3">Chi tiết</th>
                </tr>
              </thead>
              <tbody>
             		 <% 
		try{		 
              Map<LoaiThietBi,List<LoaiThietBi>> mapThietBiMuc2 = (HashMap<LoaiThietBi,List<LoaiThietBi>>) request.getAttribute("mapLoaiThietBiMuc2");
              for(Map.Entry<LoaiThietBi, List<LoaiThietBi>> entry : mapThietBiMuc2.entrySet()) {
            	  LoaiThietBi loaiThietBiMuc1 = entry.getKey();
            	  List<LoaiThietBi> loaiThietBiMuc2s = entry.getValue();
					for(LoaiThietBi loaiThietBi : loaiThietBiMuc2s) {%>
	                <tr>
	                  <td><%= loaiThietBi.getMaLoai()%></td>
	                  <td><%= loaiThietBiMuc1.getTenLoai() %> <%= loaiThietBi.getTenLoai() %></td>
	                  <td><%= loaiThietBiMuc1.getTenLoai() %></td>
	                  <td><%= loaiThietBi.getSoLuong() %></td>
	                <td class="text-center">
          			<a href="<%=request.getContextPath()%>/loaithietbimuc2-sua?maloaithietbi=<%=loaiThietBi.getMaLoai() %>" class="btn btn-warning">Sửa</a>
                  </td>
                  <td class="text-center">
          			<a href="<%=request.getContextPath()%>/loaithietbimuc2-block?maLoaiThietBi=<%=loaiThietBi.getMaLoai()%>">
		                 	<%if(!loaiThietBi.isBlocked()) {%>
		          			<span class="btn btn-danger">Khóa</span>
		          			<%}else{ %>
		          			<span class="btn btn-warning">Mở khóa</span>
		          			<%} %>
                 		</a>
                  </td>
                  <td class="text-center">
          			<a href="<%= request.getContextPath() %>/loaithietbimuc2-chi-tiet?maloaithietbi=<%=loaiThietBi.getMaLoai() %>" class="btn btn-success">Chi tiết</a>
                  </td>
	                </tr>
	              <% 
						
					}
            	}
              }catch(Exception e) {
      			System.out.print(e.getMessage());
      		} %>
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
    <%@include file="/partial/footer.jsp" %>