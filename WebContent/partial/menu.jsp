<%@page import="beans.NguoiDung"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <ul class="navbar-nav ml-auto">
        <%
		NguoiDung nguoidung = (NguoiDung)session.getAttribute("nguoidung");
		%>
        <li>
        	<a href="<%=request.getContextPath() %>/user-profile" class="btn btn-primary">
        		<span style="color:#fbcb00"><%= nguoidung.getTenND() %></span>
        	</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<%=request.getContextPath() %>/logout">
            <i class="fa fa-fw fa-sign-out" style="color:#db0041"></i><span style="color:#fffff0">Logout</span>
          </a>
        </li>
      </ul>