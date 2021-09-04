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
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet"
          type="text/css"/>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
<div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid" style="margin: 10px 100px;">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/login">
                <img src="${pageContext.request.contextPath}/photos/logo.png" alt="" width="40" height="40"
                     style="margin: 0 20px;">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page"
                           href="${pageContext.request.contextPath}/login">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="${pageContext.request.contextPath}/register">Register</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/category">Vehicles</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <nav class="navbar navbar-light" style="background-color: #282838;">
        <div class="container">
            <label style="font-size: 25px; font-weight: bold; margin: 15px auto; color: white">Welcome To
                Banger&CO!!!</label>
        </div>
    </nav>
</div>
<div>
    <div class="container-sm">
        <form:form id="form" method="post" enctype="multipart/form-data" modelAttribute="userForm">
            <div class="col-lg-4 col-md-4 col-sm-4 container justify-content-center">
                <div class="mb-3">
                    <label class="form-label">Firstname</label>
                    <form:input path="firstName" id="firstname" maxlength="20" class="form-control" type="text"
                                autofocus="autofocus"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Lastname</label>
                    <form:input path="lastName" id="lastname" maxlength="40" class="form-control" type="text"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <form:input path="email" id="email" maxlength="12" class="form-control" type="text"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Contact</label>
                    <form:input path="contact" id="contact" maxlength="15" class="form-control" type="text"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Date of Birth</label>
                    <form:input path="dateOfBirth" id="dob" class="form-control" type="text" autocomplete="off"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">License Number</label>
                    <form:input path="driversLicenseNumber" maxlength="15" id="license" class="form-control"
                                type="text"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Password</label>
                    <form:input path="password" id="password" maxlength="20" type="password" class="form-control"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Confirm Password</label>
                    <input id="confirmPassword" maxlength="20" type="password" class="form-control"/>
                </div>


                <label>User Image</label>
                <input type="file" name="userImage">
                <label>License Image</label>
                <input type="file" name="licenseImage">
                <label>Alternate Image</label>
                <input type="file" name="alternateImage">
                <button type="submit">Register</button>
                <div>
                    <p style="display: none" id="errorMessage">${error}</p>
                </div>
                <div>
                    <p style="display: none" id="successMessage">${success}</p>
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>