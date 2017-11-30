<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <html>
    	<head>
    	<style>
    		@import url(https://fonts.googleapis.com/css?family=Roboto:300);

			.login-page {
			  width: 360px;
			  padding: 8% 0 0;
			  margin: auto;
			}
			.form {
			  position: relative;
			  z-index: 1;
			  background: #FFFFFF;
			  max-width: 360px;
			  margin: 0 auto 100px;
			  padding: 45px;
			  text-align: center;
			  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
			}
			.form input {
			  font-family: "Roboto", sans-serif;
			  outline: 0;
			  background: #f2f2f2;
			  width: 100%;
			  border: 0;
			  margin: 0 0 15px;
			  padding: 15px;
			  box-sizing: border-box;
			  font-size: 14px;
			}
			.form button {
			  font-family: "Roboto", sans-serif;
			  text-transform: uppercase;
			  outline: 0;
			  background: #4CAF50;
			  width: 100%;
			  border: 0;
			  padding: 15px;
			  color: #FFFFFF;
			  font-size: 14px;
			  -webkit-transition: all 0.3 ease;
			  transition: all 0.3 ease;
			  cursor: pointer;
			}
			.form button:hover,.form button:active,.form button:focus {
			  background: #43A047;
			}
			.form .message {
			  margin: 15px 0 0;
			  color: #b3b3b3;
			  font-size: 12px;
			}
			.form .message a {
			  color: #4CAF50;
			  text-decoration: none;
			}
			.form .register-form {
			  display: none;
			}
			.container {
			  position: relative;
			  z-index: 1;
			  max-width: 300px;
			  margin: 0 auto;
			}
			.container:before, .container:after {
			  content: "";
			  display: block;
			  clear: both;
			}
			.container .info {
			  margin: 50px auto;
			  text-align: center;
			}
			.container .info h1 {
			  margin: 0 0 15px;
			  padding: 0;
			  font-size: 36px;
			  font-weight: 300;
			  color: #1a1a1a;
			}
			.container .info span {
			  color: #4d4d4d;
			  font-size: 12px;
			}
			.container .info span a {
			  color: #000000;
			  text-decoration: none;
			}
			.container .info span .fa {
			  color: #EF3B3A;
			}
			body {
			  background: #f0f0f0; /* fallback for old browsers */
			  background: -webkit-linear-gradient(right, #f0f0f0, #8DC26F);
			  background: -moz-linear-gradient(right, #f0f0f0, #8DC26F);
			  background: -o-linear-gradient(right, #f0f0f0, #8DC26F);
			  background: linear-gradient(to left, #f0f0f0, #8DC26F);
			  font-family: "Roboto", sans-serif;
			  -webkit-font-smoothing: antialiased;
			  -moz-osx-font-smoothing: grayscale;      
			}
    	</style>
    		<meta charset="utf-8">
		  <meta http-equiv="X-UA-Compatible" content="IE=edge">
		  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		  <meta name="description" content="">
		  <meta name="author" content="">
		  <title>SB Admin - Start Bootstrap Template</title>
		  <!-- Bootstrap core CSS-->
		  <link href="<%=request.getContextPath() %>/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		  <!-- Custom fonts for this template-->
		  <link href="<%=request.getContextPath() %>/assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		  <!-- Page level plugin CSS-->
		  <link href="<%=request.getContextPath() %>/assets/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
		  <!-- Custom styles for this template-->
		  <link href="<%=request.getContextPath() %>/assets/css/sb-admin.css" rel="stylesheet">
    	</head>
    	<body>
    		<div class="login-page">
				<form class="register-form" method="post" action="<%= request.getContextPath()%>/dang-nhap">
					<div class="form-group row">
						<div class="col-sm-12 text-center">
							<h2>LOGIN</h2>
						</div>
					</div>
					<div class="form-group row">
						<label>Tên đăng nhập</label>
						<input type="text" class="form-control" name="tendangnhap" required/>
					</div>
					<div class="form-group row">
						<label>Mật khẩu</label>
						<input type="password" class="form-control" name="matkhau" required/>
					</div>
					<div class="form-group row">
						<input type="submit" class="btn btn-primary form-control" name="submit" value="ĐĂNG NHẬP"/>
					</div>
				</form>
			</div>
			<div class="col-md-12 text-center">
    		<%
    		if (request.getParameter("msg") != null && request.getParameter("msg").equals("0")) {
    		%>
    		<div class="alert alert-danger"><p><strong>Tên đăng nhập hoặc mật khẩu không chính xác</strong></p></div>
    		<%
    		}
    		if (request.getParameter("msg") != null && request.getParameter("msg").equals("block")) {
        	%>
        	<div class="alert alert-danger"><p><strong>Tài khoản của bạn hiện đang bị tạm khóa!</strong></p></div>
        	<%
        	}
    		%>
    		</div>
    	</body>
    </html>