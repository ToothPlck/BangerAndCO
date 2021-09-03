<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form:form method="post" modelAttribute="userForm" enctype="multipart/form-data">
    <label>Email</label>
    <form:input path="email" type="text"/>
    <br>
    <label>Password</label>
    <form:input path="password" type="password"/>
    <br>
    <input type="file" name="imageFile">
    <br>
    <button type="submit">Register</button>
</form:form>
</body>
</html>
