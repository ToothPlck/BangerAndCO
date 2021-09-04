<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Banger&Co</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
            integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp"
            crossorigin="anonymous"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
<form:form method="post" enctype="multipart/form-data" modelAttribute="userForm">
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
