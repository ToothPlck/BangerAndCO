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
            <label style="font-size: 25px; font-weight: bold; margin: 15px auto; color: white">Register with
                Banger</label>
        </div>
    </nav>
</div>
<div>
    <div class="container-sm mt-5">
        <form:form id="form" method="post" enctype="multipart/form-data" modelAttribute="userForm">
            <div class="col-lg-4 col-md-4 col-sm-4 container justify-content-center">
                <div class="form-floating mt-5 mb-3">
                    <form:input path="firstName" id="firstname" maxlength="20" class="form-control" type="text"
                                autofocus="autofocus"/>
                    <label for="firstname">Firstname</label>
                </div>
                <div class="form-floating mb-3">
                    <form:input path="lastName" id="lastname" maxlength="40" class="form-control" type="text"/>
                    <label for="lastname">Lastname</label>
                </div>
                <div class="form-floating mb-3">
                    <form:input path="email" id="email" maxlength="40" class="form-control" type="text"/>
                    <label for="email">Email</label>
                </div>
                <div class="form-floating mb-3">
                    <form:input path="contact" id="contact" maxlength="10" class="form-control" type="text"/>
                    <label for="email">Contact</label>
                </div>
                <div class="form-floating mb-3">
                    <form:input path="dateOfBirth" id="dob" class="form-control" type="text" autocomplete="off"/>
                    <label for="dob">Date of Birth</label>
                </div>
                <div class="form-floating mb-3">
                    <form:input path="driversLicenseNumber" maxlength="8" id="license" class="form-control"
                                type="text"/>
                    <label for="license">License Number</label>
                </div>
                <div class="form-floating mb-3">
                    <form:input path="password" id="password" maxlength="25" type="password" class="form-control"/>
                    <label for="password">Password</label>
                </div>
                <div class="form-floating mb-3">
                    <input id="confirmPassword" maxlength="25" type="password" class="form-control"/>
                    <label for="confirmPassword">Confirm Password</label>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="userImage">User image</label>
                    <input name="userImage" type="file" class="form-control" id="userImage"/>
                    <div class="form-text">Please insert a recent image of you.</div>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="licenseImage">Drivers license image</label>
                    <input name="licenseImage" type="file" class="form-control" id="licenseImage"/>
                    <div class="form-text">Please insert a recent image of your current drivers license</div>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="alternateImage">Alternative image</label>
                    <input name="alternateImage" type="file" class="form-control" id="alternateImage"/>
                    <div class="form-text">Please insert an image of a recent utility bill (Within 3 months)</div>
                </div>
                <div>
                    <button type="submit" class="btn btn-outline-secondary m-auto d-block mb-5">Register</button>
                </div>
                <div>
                    <p style="display: none" id="successMessage">${success}</p>
                    <p style="display: none" id="errorMessage">${error}</p>
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
<script>
    window.onload = function () {
        const errorMessage = document.getElementById("errorMessage").innerHTML;
        const successMessage = document.getElementById("successMessage").innerHTML;

        if (errorMessage !== "") {
            Swal.fire({
                title: "Error occurred while updating!!!",
                text: errorMessage,
                icon: "error",
            });
        }
        if (successMessage !== "") {
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: successMessage,
                showConfirmButton: false,
                timer: 3000
            });
        }
    }

    $(function () {
        $("#dob").datepicker({dateFormat: "yy-mm-dd", minDate: "-100Y", maxDate: "-15Y"}).val()
    });

    const form = document.getElementById('form');

    form.addEventListener('submit', function (event) {
        const mailFormat = /[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]{2,3}/;
        const nameFormat = /[^a-zA-Z\s]+/;
        const contactFormat = /^\d+$/;

        const firstname = $("#firstname").val();
        const lastname = $("#lastname").val();
        const email = $("#email").val();
        const phone = $("#contact").val();
        const license = $("#license").val();
        const password = $("#password").val();
        const confirmPassword = $("#confirmPassword").val();
        const userImage = $("#userImage").val();
        const licenseImage = $("#licenseImage").val();
        const alternateImage = $("#alternateImage").val();

        if (firstname.length < 2 || firstname.length > 20 || firstname.indexOf(' ') >= 0 || firstname.match(nameFormat)) {
            event.preventDefault();
            Swal.fire({
                title: "Error in first name!!!",
                html: '<div> Firstname cannot be kept empty : </div>' +
                    '<div> <br> Firstname cannot contain white spaces </div>' +
                    '<div> <br> Firstname cannot contain numbers or symbols </div>' +
                    '<div> <br> Firstname should contain 2 to 20 characters </div>',
                icon: "error",
            });
        } else if (lastname.length < 2 || lastname.length > 40 || lastname.match(nameFormat)) {
            event.preventDefault();
            Swal.fire({
                title: "Error in last name!!!",
                html: '<div> Lastname cannot be kept empty : </div>' +
                    '<div> <br> Lastname cannot contain numbers or symbols </div>' +
                    '<div> <br> Firstname should contain 2 to 40 characters </div>',
                icon: "error",
            });
        } else if (email.length < 12 || email.length > 40 || email.indexOf(' ') >= 0 || !email.match(mailFormat)) {
            event.preventDefault();
            Swal.fire({
                title: "Error in email!!!",
                html: '<div> Email cannot be kept empty : </div>' +
                    '<div> <br> Email cannot contain white spaces </div>' +
                    '<div> <br> Example email format: youremail@email.com </div>' +
                    '<div> <br> Email should contain 12 to 25 characters </div>',
                icon: "error",
            });
        } else if (phone.length !== 10 || phone.indexOf(' ') >= 0 || !phone.match(contactFormat)) {
            event.preventDefault();
            Swal.fire({
                title: "Error in Phone number!!!",
                html: '<div> Phone number cannot be kept empty : </div>' +
                    '<div> <br> Phone number cannot contain white spaces </div>' +
                    '<div> <br> Phone number should contain 10 characters </div>',
                icon: "error",
            });
        } else if (password.length < 3 || password.length > 25) {
            event.preventDefault();
            Swal.fire({
                title: "Error in Password!!!",
                text: "-> Password should contain 3 to 25 characters!",
                icon: "error",
            });
        } else if (confirmPassword !== password) {
            event.preventDefault();
            Swal.fire({
                title: "Error in password confirm!!!",
                text: "-> The password and confirm password does not match!",
                icon: "error",
            });
        } else if (license.length < 8 || license.length > 8) {
            event.preventDefault();
            Swal.fire({
                title: "Error in license!!!",
                html: '<div> Drivers license number cannot be kept empty : </div>' +
                    '<div> <br> Drivers license number cannot contain numbers or symbols </div>' +
                    '<div> <br> Drivers license number should contain 2 to 40 characters </div>',
                icon: "error",
            });
        } else if (userImage.length < 1) {
            event.preventDefault();
            Swal.fire({
                title: "Error in user image!!!",
                html: '<div> The user image cannot be kept empty </div>',
                icon: "error",
            });
        } else if (licenseImage.length < 1) {
            event.preventDefault();
            Swal.fire({
                title: "Error in license image!!!",
                html: '<div> The license image cannot be kept empty </div>',
                icon: "error",
            });
        } else if (alternateImage < 1) {
            event.preventDefault();
            Swal.fire({
                title: "Error in alternate image!!!",
                html: '<div> The alternate image cannot be kept empty </div>',
                icon: "error",
            });
        } else {
            Swal.fire({
                title: 'Updating...',
                html: 'Hold on a few seconds while we register you!',
                timer: 10000,
                timerProgressBar: false,
            });
        }
    });
</script>