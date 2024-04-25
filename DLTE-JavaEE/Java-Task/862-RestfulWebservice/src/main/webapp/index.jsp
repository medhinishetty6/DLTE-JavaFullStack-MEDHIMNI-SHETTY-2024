<%--
  Created by IntelliJ IDEA.
  User: xxshetvm
  Date: 4/24/2024
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<form method="post" action="login">
    <div class="container login-container">
        <div class="row justify-content-center">
            <div class="col-lg-4 col-md-6 col-sm-8 col-10">
                <div class="login-box">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" id="username" name="username" class="form-control" placeholder="Enter Username">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" id="password" name="password" class="form-control" placeholder="Enter Password">
                    </div>
                    <div class="row justify-content-around">
                        <button id="loginbutton" type="submit" class="col-4 btn login-button bi bi-box-arrow-in-right"> Login</button>
                        <button id="cancel" class="col-4 btn btn-secondary bi bi-x-circle-fill"> Cancel</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
