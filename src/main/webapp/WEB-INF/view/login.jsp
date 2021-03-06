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
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/login">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/register">Register</a>
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
    <div style="height: 100%;
      overflow: hidden;
      width: 100%;">
        <img src="${pageContext.request.contextPath}/photos/login_background_2.jpg" alt=""
             style="height: 85%; width: 100%"/>
    </div>
    <div class="container" style="position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-15%, -20%);">
        <form:form id="form" method="post" action="${contextPath}/login">
            <div class="col-lg-4 col-md-4 col-sm-4 container justify-content-center">
                <div class="form-floating mb-3">
                    <input class="form-control" autofocus="autofocus" name="email" type="text" id="email"/>
                    <label for="email">Email</label>
                </div>
                <div class="form-floating mb-3">
                    <input class="form-control" name="password" type="password" id="password"/>
                    <label for="password">Password</label>
                </div>
                <div class="mb-3">
                    <button type="submit" class="form-control"
                            style="color: floralwhite; border-color: #414141; background-color: #414141">LOGIN
                    </button>
                    <div class="text-center">
                        <div class="form-text" style="color: black">
                            Not registered with us?
                        </div>
                        <div class="form-text" style="color: black">
                            Click here <a style="color: #282838" href="${pageContext.request.contextPath}/register">Register</a>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
    <div>
        <p style="display: none" id="successMessage">${success}</p>
        <p style="display: none" id="errorMessage">${error}</p>
    </div>
</div>
</body>
</html>
<script>
    window.onload = function () {
        const errorMessage = document.getElementById("errorMessage").innerHTML;
        const successMessage = document.getElementById("successMessage").innerHTML;

        if (errorMessage !== "" && errorMessage !== "black") {
            Swal.fire({
                title: "Error occurred while logging in!!!",
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
        if (errorMessage === "black") {
            Swal.fire({
                icon: 'error',
                title: 'User blacklisted',
                html: '<div> User has been blacklisted and is restricted from receiving services from BangerAndCo</div>' +
                    '<div> <br> This action may have been caused by the following reasons : </div>' +
                    '<div> <br> 1. The administrators of Banger manually blacklisting you for violating our term and conditions </div>' +
                    '<div> <br> 2. Users failure to collect a booked vehicle </div>',
                showCancelButton: true,
                confirmButtonText: `Yes!`,
                cancelButtonText: 'Nope!',
            }).then((result) => {
                if (!result.isConfirmed) {
                    window.location.href = '/logout';
                } else {
                    window.location.href = '/logout';
                }
            })
        }
    }
</script>