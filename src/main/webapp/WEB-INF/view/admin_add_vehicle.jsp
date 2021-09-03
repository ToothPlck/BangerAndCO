<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin - Add Vehicle</title>
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
<form:form method="post" enctype="multipart/form-data" modelAttribute="vehicleForm">
    <label>Engine type</label>
    <form:input path="engineType" type="text"/>
    <br>
    <label>available</label>
    <form:select path="available">
        <form:option value="true">Yes</form:option>
        <form:option value="false">No</form:option>
    </form:select>
    <br>
    <label>License plate number</label>
    <form:input path="licensePlateNumber" type="text"/>
    <br>
    <label>Model</label>
    <form:input path="model" type="text"/>
    <br>
    <label>Rent per day</label>
    <form:input path="rentPerDay" type="text"/>
    <br>
    <label>Transmission type</label>
    <form:input path="transmissionType" type="text"/>
    <br>
    <label>Type</label>
    <form:select path="vehicleType">
        <c:forEach items="${vehicleTypes}" var="type">
            <form:option value="${type.vehicleTypeId}">${type.vehicleType}</form:option>
        </c:forEach>
    </form:select>
    <br>
    <label>Vehicle Image</label>
    <input type="file" name="vehicleImage">
    <br>
    <button type="submit">Submit</button>
    <br>
    <label>error : ${error}</label>
    <label> success : ${success}</label>
</form:form>
</body>
</html>
