<%--
  Created by IntelliJ IDEA.
  User: xxshetvm
  Date: 4/24/2024
  Time: 2:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    if (session.getAttribute("username") != null) {
%>

<%
    String info = (String) request.getAttribute("info");
%>

<div class="container">
    <% if (info != null && info != "") { %>
    <h1 class="text-center text-success"><%= info %></h1>
    <% } %>
    <h1>Create Account</h1>
    <form action="CreateAccountServlet" method="post">
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="text" id="password" name="password" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" id="email" name="email" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="phoneNumber" class="form-label">Phone Number</label>
            <input type="number" id="phoneNumber" name="phoneNumber" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="balance" class="form-label">Balance</label>
            <input type="text" id="balance" name="balance" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<% } else {
    response.sendRedirect("index.jsp");
} %>

</body>
</html>
