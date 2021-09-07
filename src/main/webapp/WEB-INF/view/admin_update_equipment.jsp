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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
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
                        <a class="nav-link dropdown-toggle active" aria-current="page" href="#" id="equipmentsDropdown"
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
            <label style="font-size: 25px; font-weight: bold; margin: 15px auto;"><a
                    style="text-decoration: none; color: white;"
                    href="${pageContext.request.contextPath}/admin/equipment/view/all">
                <i class="bi bi-arrow-bar-left"></i> Update Equipment
            </a></label>
        </div>
    </nav>
</div>
<div>
    <div class="container-sm mt-5">
        <form:form modelAttribute="equipment" method="post" enctype="multipart/form-data">
            <div class="row container justify-content-center">
                <div class="col-5">
                    <img src="/images/${equipment.equipmentImagePath}" class="rounded m-auto mt-2" alt="..." width="450"
                         height="450">
                </div>
                <div class="col-5">
                    <div class="form-floating mb-3">
                        <form:input path="equipmentName" type="text" class="form-control" id="equipmentName"/>
                        <label for="equipmentName">Equipment name</label>
                    </div>
                    <div class="form-floating mb-3">
                        <form:input path="equipmentIdentifier" type="text" class="form-control"
                                    id="equipmentIdentifier"/>
                        <label for="equipmentIdentifier">Equipment Identifier</label>
                    </div>
                    <div class="form-floating mb-3">
                        <form:input path="equipmentRentPerHour" type="text" class="form-control" id="equipmentRent"/>
                        <label for="equipmentRent">Rent per hour</label>
                        <div class="form-text">
                            Prices in $
                        </div>
                    </div>
                    <div class="form-floating mb-3">
                        <form:select path="available" id="available" class="form-select">
                            <form:option value="true">Yes</form:option>
                            <form:option value="false">No</form:option>
                        </form:select>
                        <label for="available">Rent per hour</label>
                    </div>
                    <div class="form-floating mb-3">
                        <form:select path="equipmentType" id="type" class="form-select">
                            <form:option value="${equipment.equipmentType}">${equipment.equipmentType}</form:option>
                            <form:option value="Satnav">Satnav</form:option>
                            <form:option value="Baby seat">Baby seat</form:option>
                            <form:option value="Wine chiller">Wine chiller</form:option>
                        </form:select>
                        <label for="type">Rent per hour</label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="image">Equipment image</label>
                        <input name="equipmentImage" type="file" class="form-control" id="image"/>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-outline-secondary m-auto d-block">Update</button>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>