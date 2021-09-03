<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin - Register User</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
<form:form method="post" modelAttribute="userForm" enctype="multipart/form-data">
    <label>Email</label>
    <form:input path="email" type="text"/>
    <br>
    <label>Password</label>
    <form:input path="password" type="password"/>
    <br>
    <label>Contact</label>
    <form:input path="contact" type="text"/>
    <br>
    <label>Drivers License number</label>
    <form:input path="driversLicenseNumber" type="text"/>
    <br>
    <label>Date of birth</label>
    <form:input path="dateOfBirth" type="date"/>
    <br>
    <label>First name</label>
    <form:input path="firstName" type="text"/>
    <br>
    <label>Last name</label>
    <form:input path="lastName" type="text"/>
    <br>
    <label>User Image</label>
    <input type="file" name="userImage">
    <br>
    <label>License Image</label>
    <input type="file" name="licenseImage">
    <br>
    <label>Alternate Image</label>
    <input type="file" name="alternateImage">
    <br>
    <button type="submit">Register</button>
    <label>error : ${error}</label>
    <label> success : ${success}</label>
</form:form>
</body>
</html>