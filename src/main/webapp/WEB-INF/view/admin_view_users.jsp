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
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Users
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/users/view/active">Active</a></li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/users/view/pending">Pending</a></li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/users/view/blacklisted">Blacklisted</a>
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
    <%--    <nav class="navbar navbar-light" style="background-color: #282838;">--%>
    <%--        <div class="container">--%>
    <%--            <label style="font-size: 25px; font-weight: bold; margin: 15px auto; color: white">Welcome To--%>
    <%--                Banger&CO!!!</label>--%>
    <%--        </div>--%>
    <%--    </nav>--%>
</div>
<div>
    <div class="container-sm">
        <form:form id="form" modelAttribute="users" method="get">
            <div class="col-lg-10 col-md-10 col-sm-10 container justify-content-center">
                <div class="text-center">
                    <p class="display-6">${type}</p>
                </div>
                <div class="table-responsive rounded-3">
                    <table class="table table-dark table-hover">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Firstname</th>
                            <th>Lastname</th>
                            <th>Email</th>
                            <th>View</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.userId}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.email}</td>
                                <td><a type="button" class="btn btn-outline-light" data-bs-toggle="modal"
                                       data-bs-target="#exampleModal">View</a>
                                </td>
                            </tr>
                            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                                 aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title text-center"
                                                id="exampleModalLabel">${user.firstName} ${user.lastName}</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <img class="rounded-circle mx-auto d-block"
                                                     src="${pageContext.request.contextPath}/images/${user.userImagePath}"
                                                     alt="" width="100" height="100">
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="userid"
                                                       value="${user.userId}" disabled>
                                                <label for="userid">Id</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="firstname"
                                                       value="${user.firstName}" disabled>
                                                <label for="firstname">Firstname</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="lastname"
                                                       value="${user.lastName}" disabled>
                                                <label for="lastname">Lastname</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="email"
                                                       value="${user.email}" disabled>
                                                <label for="email">Email</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="contact"
                                                       value="${user.contact}" disabled>
                                                <label for="contact">Contact</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="licenseNumber"
                                                       value="${user.driversLicenseNumber}" disabled>
                                                <label for="licenseNumber">License Number</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="dob"
                                                       value="${user.dateOfBirth}" disabled>
                                                <label for="dob">Date of Birth</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="joined"
                                                       value="${user.createdDate}" disabled>
                                                <label for="joined">Joined at</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" placeholder="License"
                                                       id="licenseImage" disabled/>
                                                <label for="licenseImage">License : </label>
                                                <br>
                                                <img class="rounded-3 mx-auto d-block"
                                                     src="${pageContext.request.contextPath}/images/${user.licenseImagePath}"
                                                     alt="" width="400" height="400">
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" placeholder="alternate"
                                                       id="altImage" disabled/>
                                                <label for="altImage">Alternate image : </label>
                                                <br>
                                                <img class="rounded-3 mx-auto d-block"
                                                     src="${pageContext.request.contextPath}/images/${user.alternateImagePath}"
                                                     alt="" width="400" height="400">
                                            </div>
                                            <div>

                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" style="display: none" class="btn btn-outline-success"
                                                    id="${user.userId} verify">Verify
                                            </button>
                                            <button type="button" style="display: none" class="btn btn-outline-danger"
                                                    id="${user.userId} blacklist">Blacklist
                                            </button>
                                            <button type="button" style="display: none" class="btn btn-outline-warning"
                                                    id="${user.userId} whitelist">Whitelist
                                            </button>
                                            <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">
                                                Close
                                            </button>
                                            <p id="verified" style="display: none">${user.verified}</p>
                                            <p id="blacklisted" style="display: none">${user.blacklisted}</p>
                                            <script>
                                                window.onload = function () {
                                                    const verified = document.getElementById("verified").innerHTML;
                                                    const blacklisted = document.getElementById("blacklisted").innerHTML;

                                                    if (verified === "false" && blacklisted === "false") {
                                                        document.getElementById("${user.userId} verify").style.display = "block";
                                                        document.getElementById("${user.userId} blacklist").style.display = "block";
                                                    }
                                                    if (verified === "true" && blacklisted === "false") {
                                                        document.getElementById("${user.userId} blacklist").style.display = "block";
                                                    }
                                                    if (blacklisted === "true") {
                                                        document.getElementById("${user.userId} whitelist").style.display = "block";
                                                    }
                                                }
                                            </script>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
<script>

</script>