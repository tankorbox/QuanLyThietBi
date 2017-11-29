<%@page import="java.util.ArrayList"%>
<%@page import="beans.LoaiThietBi"%>
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
        <li class="breadcrumb-item active">Thêm thông tin thiết bị</li>
      </ol>
      <%
      	ArrayList<LoaiThietBi> alTbM1 = (ArrayList<LoaiThietBi>) request.getAttribute("alTbM1");
      %>
      <form action="" method="<%=request.getContextPath()%>/loaithietbimuc2-them">
         <div class="form-group">
            <div class="form-row">
            <div class="col-sm-12">
                <label for="loaiThietBi">Loại thiết bị</label>
              	<select class="form-control" name="loaithietbi" required>
              	<%
              	for(LoaiThietBi tb : alTbM1){
              	%>
	              	<option value="<%=tb.getMaLoai()%>"><%=tb.getTenLoai() %></option>
	            <%} %>
              	</select>
              </div>
            </div>
          </div>
          <div class="form-group">
          		<div class="form-row">
	          		<div class="col-sm-12">
		                <label for="tenThietBi">Tên hãng<span style="color:red">(*)</span><span style="color:green"> (vd: LG, Sony, .vv..)</span></label>
		                <input class="form-control" type="text" name="tenhang" required>
	              	</div>
          		</div>
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-sm-12">
                <label for="soLuong">Số lượng</label>
                <input class="form-control" type="number" name="soluong" required>
              </div>
              
            </div>
          </div>      
          <div class="form-group">
          <div class="form-row">
          	  <div class="col-sm-4">
                <a href="<%=request.getContextPath() %>/loaithietbimuc2-danhsach" class="form-control btn btn-danger">HỦY</a>
              </div>
          	  <div class="col-sm-4">
                <input class="form-control btn btn-warning" type="reset" name="Reset" value="NHẬP LẠI">
              </div>
              <div class="col-sm-4">
                <input class="form-control btn btn-success" type="submit" name="submit" value="THÊM">
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