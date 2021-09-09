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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/user/home">
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
                        <a class="nav-link" href="${pageContext.request.contextPath}/user/home">Home</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#"
                           id="rentalsDropdown"
                           role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            My Rentals
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="rentalsDropdown">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/user/rentals/on-going">On Going</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/user/rentals/pending">Pending</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/user/rentals/rejected">Rejected</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/user/rentals/approved">Approved</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/user/rentals/completed">Completed</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <ul class="navbar-nav mb-2 mb-lg-0 d-flex">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#"
                           id="accountDropdown"
                           role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="${pageContext.request.contextPath}/images/${loggedUser.userImagePath}"
                                 class="rounded-circle" alt=""
                                 width="40" height="40"
                                 style="margin: 0 20px;">${loggedUser.firstName}
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="accountDropdown">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/user/account/view">View Account</a>
                            </li>
                            <hr class="dropdown-divider">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/logout">Logout</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <nav class="navbar navbar-light" style="background-color: #282838;">
        <div class="container">
            <label style="font-size: 25px; font-weight: bold; margin: 15px auto; color: white">Welcome to
                Banger&Co, ${loggedUser.firstName}</label>
        </div>
    </nav>
</div>
<div>
    <div id="carouselSlide" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="${pageContext.request.contextPath}/photos/login_background_3.jpg"
                     class="d-block h-75 w-100 img-fluid" alt="...">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/photos/home_image_1.jpg" class="d-block h-75 w-100"
                     alt="...">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/photos/home_image_2.jpg" class="d-block h-75 w-100"
                     alt="...">
            </div>
        </div>
    </div>
    <nav class="navbar navbar-light" style="background-color: #282838;">
        <div class="container">
            <label style="font-size: 25px; font-weight: bold; margin: 15px auto; color: white">Our Vehicles</label>
        </div>
    </nav>
    <div class="container-sm mt-5">
        <form:form id="form" method="get" modelAttribute="vehicles">
            <div class="row my-5 align-items-center justify-content-center">
                <c:forEach items="${vehicles}" var="vehicle">
                    <div class="card text-white bg-dark mb-3"
                         style="width: 18rem; min-height: 300px; margin: 25px; cursor: pointer" data-bs-toggle="modal"
                         data-bs-target="#modal${vehicle.vehicleId}">
                        <img src="/images/${vehicle.vehicleImagePath}" class="card-img-top mt-3 rounded-3" alt=""
                             width="200" height="200">
                        <div class="card-body">
                            <figure>
                                <blockquote class="blockquote card-title text-center">
                                    <p>${vehicle.model}</p>
                                </blockquote>
                                <figcaption class="blockquote-footer text-center mt-1">
                                    <cite>${vehicle.rentPerHour}$/h</cite>
                                </figcaption>
                            </figure>
                        </div>
                    </div>
                    <div class="modal fade" id="modal${vehicle.vehicleId}" tabindex="-1"
                         aria-labelledby="exampleModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <figure>
                                        <blockquote class="blockquote">
                                            <p>${vehicle.model}</p>
                                        </blockquote>
                                        <figcaption class="blockquote-footer">
                                            <cite>${vehicle.vehicleType.vehicleType}</cite>
                                        </figcaption>
                                    </figure>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="mb-3">
                                        <img class="border-1 mx-auto d-block"
                                             src="${pageContext.request.contextPath}/images/${vehicle.vehicleImagePath}"
                                             alt="" width="200" height="200">
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="vehicleModel"
                                               value="${vehicle.model}" disabled>
                                        <label for="vehicleModel">Model</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="vehicleType"
                                               value="${vehicle.vehicleType.vehicleType}" disabled>
                                        <label for="vehicleType">Category</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="vehicleRent"
                                               value="${vehicle.rentPerHour}$" disabled>
                                        <label for="vehicleRent">Rent per hour</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="vehicleEngine"
                                               value="${vehicle.engineType}" disabled>
                                        <label for="vehicleEngine">Engine</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="vehicleTransmission"
                                               value="${vehicle.transmissionType}" disabled>
                                        <label for="vehicleTransmission">Transmission</label>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-outline-dark" id="rentButton"
                                            data-bs-dismiss="modal" data-bs-toggle="modal"
                                            data-bs-target="#rentalModal">Rent
                                    </button>
                                    <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">
                                        Close
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </form:form>
    </div>
    <nav class="navbar navbar-light" style="background-color: #282838;">
        <div class="container">
            <label style="font-size: 25px; font-weight: bold; margin: 15px auto; color: white">Add-On Equipments for
                Rentals</label>
        </div>
    </nav>
    <div class="container-sm mt-5">
        <form:form id="form" method="get" modelAttribute="equipments">
            <div class="row my-5 align-items-center justify-content-center">
                <c:forEach items="${equipments}" var="equipment">
                    <div class="card text-white bg-dark mb-3"
                         style="width: 18rem; min-height: 300px; margin: 25px; cursor: pointer" data-bs-toggle="modal"
                         data-bs-target="#modal${equipment.equipmentId}">
                        <img src="/images/${equipment.equipmentImagePath}" class="card-img-top mt-3 rounded-3" alt=""
                             width="200" height="200">
                        <div class="card-body">
                            <figure>
                                <blockquote class="blockquote card-title text-center">
                                    <p>${equipment.equipmentType}</p>
                                </blockquote>
                                <figcaption class="blockquote-footer text-center mt-1">
                                    <cite>${equipment.equipmentRentPerHour}$/h</cite>
                                </figcaption>
                            </figure>
                        </div>
                    </div>
                    <div class="modal fade" id="modal${equipment.equipmentId}" tabindex="-1"
                         aria-labelledby="exampleModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <figure>
                                        <blockquote class="blockquote">
                                            <p>${equipment.equipmentName}</p>
                                        </blockquote>
                                        <figcaption class="blockquote-footer">
                                            <cite>${equipment.equipmentType}</cite>
                                        </figcaption>
                                    </figure>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="mb-3">
                                        <img class="border-1 mx-auto d-block"
                                             src="${pageContext.request.contextPath}/images/${equipment.equipmentImagePath}"
                                             alt="" width="200" height="200">
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="equipmentName"
                                               value="${equipment.equipmentName}" disabled>
                                        <label for="equipmentName">Name</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="equipmentType"
                                               value="${equipment.equipmentType}" disabled>
                                        <label for="equipmentType">Category</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="equipmentRent"
                                               value="${equipment.equipmentRentPerHour}$" disabled>
                                        <label for="equipmentRent">Rent per hour</label>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-outline-dark" id="rentButton2"
                                            data-bs-dismiss="modal" data-bs-toggle="modal"
                                            data-bs-target="#rentalModal">Rent
                                    </button>
                                    <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">
                                        Close
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </form:form>
    </div>


    <div>
        <div class="modal fade" id="rentalModal" tabindex="-1"
             aria-labelledby="rentalModal"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">
                            Close
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>