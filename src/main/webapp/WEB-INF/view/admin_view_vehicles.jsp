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
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="usersDropDown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Users
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="usersDropDown">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/register">Register</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/users/view/active">Active</a></li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/users/view/pending">Pending</a></li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/users/view/blacklisted">Blacklisted</a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#"
                           id="vehiclesDropdown"
                           role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Vehicles
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="vehiclesDropdown">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/vehicleType/add">Add new category</a>
                            </li>
                            <li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/vehicleType/view/all">Categories</a>
                            </li>
                            <hr class="dropdown-divider">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/vehicle/add">Add new vehicle</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/vehicle/view/all">All Vehicles</a>
                            </li>
                            <c:forEach items="${vehiclesNav}" var="vehicles">
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/admin/vehicle/view/${vehicles.vehicleType.vehicleTypeId}">${vehicles.vehicleType.vehicleType}s</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#"
                           id="equipmentsDropdown"
                           role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Equipments
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="equipmentsDropdown">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/equipment/add">Add</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/equipment/view/all">All</a></li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/equipment/view/Satnav">Satnavs</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/equipment/view/Baby seat">Baby
                                seats</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/equipment/view/Wine chiller">Wine
                                Chillers</a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/register">Register</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/category">Vehicles</a>
                    </li>
                </ul>
                <ul class="navbar-nav mb-2 mb-lg-0 d-flex">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <nav class="navbar navbar-light" style="background-color: #282838;">
        <div class="container">
            <label style="font-size: 25px; font-weight: bold; margin: 15px auto; color: white">Our Vehicles</label>
        </div>
    </nav>
</div>
<div>

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
                            <h5 class="card-title text-center">${vehicle.model}</h5>
                        </div>
                        <ul class="list-group list-group-flush rounded-3" style="margin-bottom: 10px;">
                            <li class="list-group-item">Rent : ${vehicle.rentPerHour}$/h</li>
                            <li class="list-group-item">Engine : ${vehicle.engineType}</li>
                            <li class="list-group-item">Transmission : ${vehicle.transmissionType}</li>
                        </ul>
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
                                               value="${vehicle.rentPerHour}" disabled>
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
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="vehicleLicense"
                                               value="${vehicle.licensePlateNumber}" disabled>
                                        <label for="vehicleLicense">License Plate Number</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="vehicleAvailability"
                                               value="${vehicle.available}" disabled>
                                        <label for="vehicleAvailability">Availability</label>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-outline-warning"
                                            onclick="updateVehicle('${vehicle.vehicleId}')"
                                            id="${vehicle.vehicleId}">Update
                                    </button>
                                    <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">
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
</div>
</body>
</html>
<script>
    function updateVehicle(vehicleId) {
        document.getElementById('form').action = '/admin/vehicle/update/' + vehicleId;
    }
</script>