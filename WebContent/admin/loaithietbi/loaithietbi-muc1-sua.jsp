<%@page import="beans.LoaiThietBi"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/partial/header.jsp" %>
    <!-- Body -->
    
    <div class="content-wrapper">
    <div class="container-fluid">
    <%LoaiThietBi loaiThietBi = (LoaiThietBi) request.getAttribute("objLoaiThietBiMuc1");%>
    <%if (request.getParameter("msgEdit") != null && request.getParameter("msgEdit").equals("1")){ %>
		<h3>Sửa thành công</h3>
		<%} else if(request.getParameter("msgEdit") != null && request.getParameter("msgEdit").equals("0")) { %>
								<h3>Sửa thất bại</h3>
							<%} %>
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="#">Thiết bị</a>
        </li>
        <li class="breadcrumb-item active">Sửa thông tin thiết bị</li>
      </ol>
       
      <form action="<%=request.getContextPath() %>/loaithietbimuc1-sua?type=edit&maLoaiThietBi=<%=loaiThietBi.getMaLoai() %>" id="suaLoaiThietBiMuc1" method="POST" enctype="application/x-www-form-urlencoded">
           <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <label for="loaiThietBi">Tên loại thiết bị</label>
                <input class="form-control" type="text" name="tenLoaiThietBi" value="<%=loaiThietBi.getTenLoai()%>">
              </div>
            </div>
          </div>
          
          <div class="form-group">
          <div class="form-row">
          	  <div class="col-md-2">
                <a href="" class="form-control btn btn-danger">HỦY</a>
              </div>
          	  <div class="col-md-2">
                <input class="form-control btn btn-warning" type="reset" name="Reset" value="NHẬP LẠI">
              </div>
              <div class="col-md-2">
                <input class="form-control btn btn-success" type="submit" name="Them" value="SỬA">
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