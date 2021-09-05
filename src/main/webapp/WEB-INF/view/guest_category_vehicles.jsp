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
                        <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/register">Register</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="${pageContext.request.contextPath}/category">Vehicles</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <nav class="navbar navbar-light" style="background-color: #282838;">
        <div class="container">
            <label style="font-size: 25px; font-weight: bold; margin: 15px auto; color: white">Our Fleets</label>
        </div>
    </nav>
</div>
<div>
    <div class="container-md">
        <form:form id="form" method="get" modelAttribute="categories">
            <div class="row my-5 align-items-center justify-content-center">
                <c:forEach items="${categories}" var="category">
                    <div onclick="loginPrompt()" class="card text-white bg-dark mb-3"
                         style="width: 18rem; min-height: 400px; margin: 25px; cursor: pointer">
                        <img src="${pageContext.request.contextPath}/images/${category.vehicleImagePath}"
                             class="card-img-top" alt="" style="margin-top: 10px;" width="200" height="200">
                        <div class="card-body">
                            <h5 class="card-title text-center">${category.model}</h5>
                        </div>
                        <ul class="list-group list-group-flush" style="margin-bottom: 10px;">
                            <li class="list-group-item">Rent : ${category.rentPerHour}$/h</li>
                            <li class="list-group-item">Engine : ${category.engineType}</li>
                            <li class="list-group-item">Transmission : ${category.transmissionType}</li>
                        </ul>
                    </div>
                </c:forEach>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
<script>
    function loginPrompt() {
        Swal.fire({
            title: "Login",
            icon: "info",
            text: "Please login to continue!",
            showCancelButton: true,
            confirmButtonText: 'Login',
            cancelButtonText: 'Maybe later...',
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = '/login';
            }
        })
    }
</script>