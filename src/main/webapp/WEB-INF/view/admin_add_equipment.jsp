<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin - Add Equipment</title>
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
<form:form method="post" enctype="multipart/form-data" modelAttribute="equipmentForm">
    <label>Rent per day</label>
    <form:input path="equipmentRentPerDay" type="text"/>
    <br>
    <label>Name</label>
    <form:input path="equipmentName" type="text"/>
    <br>
    <label>Identifier</label>
    <form:input path="equipmentIdentifier" type="text"/>
    <br>
    <label>Available</label>
    <form:select path="available">
        <form:option value="true">Yes</form:option>
        <form:option value="false">No</form:option>
    </form:select>
    <br>
    <label>type</label>
    <form:select path="equipmentType">
        <form:option value="baby thing">Baby thing</form:option>
        <form:option value="drink thing">Drink thing</form:option>
        <form:option value="other">Other</form:option>
    </form:select>
    <br>
    <label>Equipment Image</label>
    <input type="file" name="equipmentImage">
    <br>
    <button type="submit">Submit</button>
    <br>
</form:form>
</body>
</html>
