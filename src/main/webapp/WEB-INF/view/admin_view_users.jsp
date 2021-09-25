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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/users/view/active">
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
                            <c:forEach items="${vehicleNav}" var="vehicles">
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/admin/vehicle/view/${vehicles.vehicleType}">${vehicles.vehicleType}s</a>
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
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#"
                           id="rentalsDropdown"
                           role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Rentals
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="rentalDropdown">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/rental/view/all">All</a></li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/rental/view/pending">Pending</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/rental/view/onGoing">On-Going</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/rental/view/approved">Approved</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/rental/view/rejected">Rejected</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/rental/view/completed">Completed</a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/admin/competitors">Competitors</a>
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
                            <li><a class="dropdown-item" data-bs-toggle="modal"
                                   data-bs-target="#accountModal" style="cursor: pointer">View Account</a>
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
            <label style="font-size: 25px; font-weight: bold; margin: 15px auto; color: white">Our Loyal
                Customers</label>
        </div>
    </nav>
</div>
<div>
    <div class="container-sm mt-5">
        <form:form id="form" modelAttribute="users" method="get">
            <div class="col-lg-10 col-md-10 col-sm-10 container justify-content-center">
                <div class="text-center">
                    <p class="display-6">${type}</p>
                </div>
                <div class="table-responsive rounded-3 mt-3">
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
                            <tr id="tr${user.userId}">
                                <td>${user.userId}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.email}</td>
                                <td><a type="button" id="view${user.userId}" class="btn btn-outline-light"
                                       data-bs-toggle="modal"
                                       data-bs-target="#modal${user.userId}">View</a>
                                </td>
                            </tr>
                            <div class="modal fade" id="modal${user.userId}" tabindex="-1"
                                 aria-labelledby="exampleModalLabel"
                                 aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <figure>
                                                <blockquote class="blockquote">
                                                    <p>${user.firstName} ${user.lastName}</p>
                                                </blockquote>
                                                <figcaption class="blockquote-footer">
                                                    <cite>${user.bangerScore} Banger Score</cite>
                                                </figcaption>
                                            </figure>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <img class="rounded-circle mx-auto d-block"
                                                     src="${pageContext.request.contextPath}/images/${user.userImagePath}"
                                                     alt="" width="200" height="200">
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="userid${user.userId}"
                                                       value="${user.userId}" disabled>
                                                <label for="userid${user.userId}">Id</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="firstname${user.userId}"
                                                       value="${user.firstName}" disabled>
                                                <label for="firstname${user.userId}">Firstname</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="lastname${user.userId}"
                                                       value="${user.lastName}" disabled>
                                                <label for="lastname${user.userId}">Lastname</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="email${user.userId}"
                                                       value="${user.email}" disabled>
                                                <label for="email${user.userId}">Email</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="contact${user.userId}"
                                                       value="${user.contact}" disabled>
                                                <label for="contact${user.userId}">Contact</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="licenseNumber${user.userId}"
                                                       value="${user.driversLicenseNumber}" disabled>
                                                <label for="licenseNumber${user.userId}">License Number</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="dob${user.userId}"
                                                       value="${user.dateOfBirth}" disabled>
                                                <label for="dob${user.userId}">Date of Birth</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="joined${user.userId}"
                                                       value="${user.createdDate}" disabled>
                                                <label for="joined${user.userId}">Joined at</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" placeholder="License"
                                                       id="licenseImage${user.userId}" disabled/>
                                                <label for="licenseImage${user.userId}">License : </label>
                                                <br>
                                                <img class="rounded-3 mx-auto d-block"
                                                     src="${pageContext.request.contextPath}/images/${user.licenseImagePath}"
                                                     alt="" width="450" height="450">
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" placeholder="alternate"
                                                       id="altImage${user.userId}" disabled/>
                                                <label for="altImage${user.userId}">Alternate image : </label>
                                                <br>
                                                <img class="rounded-3 mx-auto d-block"
                                                     src="${pageContext.request.contextPath}/images/${user.alternateImagePath}"
                                                     alt="" width="450" height="450">
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" style="display: none" class="btn btn-outline-success"
                                                    onclick="verifyFunction('${user.userId}')"
                                                    id="${user.userId} verify">Verify
                                            </button>
                                            <button type="button" style="display: none" class="btn btn-outline-danger"
                                                    onclick="blacklistFunction('${user.userId}')"
                                                    id="${user.userId} blacklist">Blacklist
                                            </button>
                                            <button type="button" style="display: none" class="btn btn-outline-warning"
                                                    onclick="whitelistFunction('${user.userId}')"
                                                    id="${user.userId} whitelist">Whitelist
                                            </button>
                                            <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">
                                                Close
                                            </button>
                                            <p id="verified${user.userId}" style="display: none">${user.verified}</p>
                                            <p id="blacklisted${user.userId}"
                                               style="display: none">${user.blacklisted}</p>

                                            <script>

                                                document.getElementById("view${user.userId}").onclick = function () {

                                                    const verified = document.getElementById("verified${user.userId}").innerHTML;
                                                    const blacklisted = document.getElementById("blacklisted${user.userId}").innerHTML;

                                                    if (verified === "false" && blacklisted === "false") {
                                                        document.getElementById("${user.userId} verify").style.display = "block";
                                                        document.getElementById("${user.userId} blacklist").style.display = "block";
                                                        <%--document.getElementById("tr${user.userId}").className = "table-primary";--%>
                                                    }
                                                    if (verified === "true" && blacklisted === "false") {
                                                        document.getElementById("${user.userId} blacklist").style.display = "block";
                                                    }
                                                    if (blacklisted === "true") {
                                                        document.getElementById("${user.userId} whitelist").style.display = "block";
                                                    }
                                                }

                                                function verifyFunction(userId) {
                                                    Swal.fire({
                                                        icon: 'question',
                                                        title: 'Confirm',
                                                        text: 'Please confirm the verification of the user!',
                                                        showCancelButton: true,
                                                        confirmButtonText: `Confirm!`,
                                                        cancelButtonText: 'Cancel!',
                                                    }).then((result) => {
                                                        if (result.isConfirmed) {
                                                            window.location.href = '/admin/users/verify/' + userId;
                                                            Swal.fire({
                                                                title: 'Verifying...',
                                                                html: 'Hold on a few seconds while we verify the user!',
                                                                timer: 10000,
                                                                timerProgressBar: false,
                                                            });
                                                        }
                                                    })
                                                }

                                                function blacklistFunction(userId) {
                                                    Swal.fire({
                                                        icon: 'question',
                                                        title: 'Confirm',
                                                        text: 'Please confirm blacklisting of the user!',
                                                        showCancelButton: true,
                                                        confirmButtonText: `Confirm!`,
                                                        cancelButtonText: 'Cancel!',
                                                    }).then((result) => {
                                                        if (result.isConfirmed) {
                                                            window.location.href = '/admin/users/blacklist/' + userId;
                                                            Swal.fire({
                                                                title: 'Blacklisting...',
                                                                html: 'Hold on a few seconds while we blacklist the user!',
                                                                timer: 10000,
                                                                timerProgressBar: false,
                                                            });
                                                        }
                                                    })
                                                }

                                                function whitelistFunction(userId) {
                                                    Swal.fire({
                                                        icon: 'question',
                                                        title: 'Confirm',
                                                        text: 'Please confirm whitelisting of the user!',
                                                        showCancelButton: true,
                                                        confirmButtonText: `Confirm!`,
                                                        cancelButtonText: 'Cancel!',
                                                    }).then((result) => {
                                                        if (result.isConfirmed) {
                                                            window.location.href = '/admin/users/whitelist/' + userId;
                                                            Swal.fire({
                                                                title: 'Whitelisting...',
                                                                html: 'Hold on a few seconds while we whitelist the user!',
                                                                timer: 10000,
                                                                timerProgressBar: false,
                                                            });
                                                        }
                                                    })
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
    <div>
        <form id="accountForm" method="get">
            <div class="modal fade" id="accountModal" tabindex="-1" aria-labelledby="accountModal" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <figure>
                                <blockquote class="blockquote">
                                    <p>${loggedUser.firstName} ${loggedUser.lastName}</p>
                                </blockquote>
                                <figcaption class="blockquote-footer">
                                    <cite>Administrator</cite>
                                </figcaption>
                            </figure>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <img class="rounded-circle mx-auto d-block"
                                     src="${pageContext.request.contextPath}/images/${loggedUser.userImagePath}"
                                     alt="" width="200" height="200">
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="userFirstname"
                                       value="${loggedUser.firstName}" disabled>
                                <label for="userFirstname">Firstname</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="userLastname"
                                       value="${loggedUser.lastName}" disabled>
                                <label for="userLastname">Lastname</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="userEmail"
                                       value="${loggedUser.email}" disabled>
                                <label for="userEmail">Email</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="userContact"
                                       value="${loggedUser.contact}" disabled>
                                <label for="userContact">Contact</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="userDob"
                                       value="${loggedUser.dateOfBirth}" disabled>
                                <label for="userDob">Date of Birth</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="userUpdated"
                                       value="${loggedUser.updatedDate}" disabled>
                                <label for="userUpdated">Last updated</label>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>