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
                                       href="${pageContext.request.contextPath}/admin/vehicle/view/${vehicles.vehicleTypeId}">${vehicles.vehicleType}s</a>
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
            <label style="font-size: 25px; font-weight: bold; margin: 15px auto; color: white">Rentals</label>
        </div>
    </nav>
</div>
<div>
    <div>
        <form id="accountForm" action="${pageContext.request.contextPath}/admin/update/account" method="get">
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
    <div>
        <div class="container-sm mt-5">
            <form:form id="form" modelAttribute="rentals" method="get">
                <div class="col-lg-10 col-md-10 col-sm-10 container justify-content-center">
                    <div class="text-center">
                        <p class="display-6">${type}</p>
                    </div>
                    <div class="table-responsive rounded-3 mt-3">
                        <table class="table table-dark table-hover">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>User</th>
                                <th>Amount</th>
                                <th>Vehicle</th>
                                <th>No. of equipments</th>
                                <th>Status</th>
                                <th>View</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${rentals}" var="rental">
                                <tr>
                                    <td>${rental.rentalId}</td>
                                    <td>${rental.user.firstName} ${rental.user.lastName}</td>
                                    <td>${rental.total}</td>
                                    <td>${rental.vehicle.model}</td>
                                    <td>${rental.equipment.size()}</td>
                                    <td>${rental.status}</td>
                                    <td><a type="button" id="view${rental.rentalId}" class="btn btn-outline-light"
                                           data-bs-toggle="modal"
                                           data-bs-target="#modal${rental.rentalId}">View</a>
                                    </td>
                                </tr>
                                <div class="modal fade" id="modal${rental.rentalId}" tabindex="-1"
                                     aria-labelledby="exampleModalLabel"
                                     aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <figure>
                                                    <blockquote class="blockquote">
                                                        <p>${rental.rentalId}</p>
                                                    </blockquote>
                                                    <figcaption class="blockquote-footer">
                                                        <cite>${rental.total} Receivable</cite>
                                                    </figcaption>
                                                </figure>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="mb-3">
                                                    <img class="rounded-circle mx-auto d-block"
                                                         src="${pageContext.request.contextPath}/images/${rental.user.userImagePath}"
                                                         alt="" width="200" height="200">
                                                </div>
                                                <div class="form-floating mb-3">
                                                    <input type="text" class="form-control"
                                                           id="userName${rental.user.firstName}"
                                                           value="${rental.user.firstName} ${rental.user.lastName}"
                                                           disabled>
                                                    <label for="userName${rental.user.firstName}">User</label>
                                                </div>
                                                <div class="form-floating mb-3">
                                                    <input type="text" class="form-control"
                                                           id="userEmail${rental.user.email}"
                                                           value="${rental.user.email}" disabled>
                                                    <label for="userEmail${rental.user.email}">User email</label>
                                                </div>
                                                <div class="form-floating mb-3">
                                                    <input type="text" class="form-control"
                                                           id="userBanger${rental.user.bangerScore}"
                                                           value="${rental.user.bangerScore}" disabled>
                                                    <label for="userBanger${rental.user.bangerScore}">User banger
                                                        score</label>
                                                </div>
                                                <div class="form-floating mb-3">
                                                    <input type="text" class="form-control"
                                                           id="vehicle${rental.vehicle.model}"
                                                           value="${rental.vehicle.model}" disabled>
                                                    <label for="vehicle${rental.vehicle.model}">Vehicle</label>
                                                </div>
                                                <c:forEach items="${rental.equipment}" var="vehicleEquipment">
                                                    <div class="form-floating mb-3">
                                                        <input type="text" class="form-control"
                                                               id="vehicleEquipments${vehicleEquipment.equipmentId}"
                                                               value="${vehicleEquipment.equipmentIdentifier}" disabled>
                                                        <label for="vehicleEquipments${vehicleEquipment.equipmentId}">Equipment
                                                            identifier</label>
                                                    </div>
                                                </c:forEach>
                                                <div class="form-floating mb-3">
                                                    <input type="text" class="form-control" id="status${rental.status}"
                                                           value="${rental.status}" disabled>
                                                    <label for="status${rental.status}">Status</label>
                                                </div>
                                                <div class="form-floating mb-3">
                                                    <input type="text" class="form-control"
                                                           id="created${rental.createdDate}"
                                                           value="${rental.createdDate}" disabled>
                                                    <label for="created${rental.createdDate}">Requested on</label>
                                                </div>
                                                <div class="form-floating mb-3">
                                                    <input type="text" class="form-control"
                                                           id="pickDate${rental.rentalCollectionDate}"
                                                           value="${rental.rentalCollectionDate}" disabled>
                                                    <label for="pickDate${rental.rentalCollectionDate}">Pick up
                                                        date</label>
                                                </div>
                                                <div class="form-floating mb-3">
                                                    <input type="text" class="form-control"
                                                           id="pickTime${rental.rentalCollectionTime}"
                                                           value="${rental.rentalCollectionTime}" disabled>
                                                    <label for="pickTime${rental.rentalCollectionTime}">Pick up
                                                        time</label>
                                                </div>
                                                <div class="form-floating mb-3">
                                                    <input type="text" class="form-control"
                                                           id="dropDate${rental.rentalReturnDate}"
                                                           value="${rental.rentalReturnDate}" disabled>
                                                    <label for="dropDate${rental.rentalReturnDate}">Return date</label>
                                                </div>
                                                <div class="form-floating mb-3">
                                                    <input type="text" class="form-control"
                                                           id="dropTime${rental.rentalReturnTime}"
                                                           value="${rental.rentalReturnTime}" disabled>
                                                    <label for="dropTime${rental.rentalReturnTime}">Return time</label>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" style="display: none"
                                                        class="btn btn-outline-danger"
                                                        onclick="rejectFunction('${rental.rentalId}')"
                                                        id="${rental.rentalId} reject">Reject
                                                </button>
                                                <button type="button" style="display: none"
                                                        class="btn btn-outline-primary"
                                                        onclick="approveFunction('${rental.rentalId}')"
                                                        id="${rental.rentalId} approve">Approve
                                                </button>
                                                <button type="button" style="display: none"
                                                        class="btn btn-outline-success"
                                                        onclick="completedFunction('${rental.rentalId}')"
                                                        id="${rental.rentalId} completed">Completed
                                                </button>
                                                <button type="button" style="display: none"
                                                        class="btn btn-outline-info"
                                                        onclick="pickupFunction('${rental.rentalId}')"
                                                        id="${rental.rentalId} picked">Picked
                                                </button>
                                                <button type="button" class="btn btn-outline-dark"
                                                        data-bs-dismiss="modal">
                                                    Close
                                                </button>
                                                <p id="status${rental.rentalId}"
                                                   style="display: none">${rental.status}</p>

                                                <script>
                                                    document.getElementById("view${rental.rentalId}").onclick = function () {

                                                        const status = document.getElementById("status${rental.rentalId}").innerHTML;

                                                        if (status === "pending") {
                                                            document.getElementById("${rental.rentalId} reject").style.display = "block";
                                                            document.getElementById("${rental.rentalId} approve").style.display = "block";
                                                        }
                                                        if (status === "approved") {
                                                            document.getElementById("${rental.rentalId} reject").style.display = "block";
                                                            document.getElementById("${rental.rentalId} picked").style.display = "block";
                                                        }
                                                        if (status === "onGoing") {
                                                            document.getElementById("${rental.rentalId} completed").style.display = "block";
                                                        }

                                                    }

                                                    function rejectFunction(rentalId) {
                                                        Swal.fire({
                                                            icon: 'question',
                                                            title: 'Confirm',
                                                            text: 'Please confirm the rejection of the rental!',
                                                            showCancelButton: true,
                                                            confirmButtonText: `Confirm!`,
                                                            cancelButtonText: 'Cancel!',
                                                        }).then((result) => {
                                                            if (result.isConfirmed) {
                                                                window.location.href = '/admin/rental/rejected/' + rentalId;
                                                                Swal.fire({
                                                                    title: 'Rejecting...',
                                                                    html: 'Hold on a few seconds while we reject the rental!',
                                                                    timer: 10000,
                                                                    timerProgressBar: false,
                                                                });
                                                            }
                                                        })
                                                    }

                                                    function approveFunction(rentalId) {
                                                        Swal.fire({
                                                            icon: 'question',
                                                            title: 'Confirm',
                                                            text: 'Please confirm the approval of the rental!',
                                                            showCancelButton: true,
                                                            confirmButtonText: `Confirm!`,
                                                            cancelButtonText: 'Cancel!',
                                                        }).then((result) => {
                                                            if (result.isConfirmed) {
                                                                window.location.href = '/admin/rental/approved/' + rentalId;
                                                                Swal.fire({
                                                                    title: 'Approving...',
                                                                    html: 'Hold on a few seconds while we approve the rental!',
                                                                    timer: 10000,
                                                                    timerProgressBar: false,
                                                                });
                                                            }
                                                        })
                                                    }

                                                    function pickupFunction(rentalId) {
                                                        Swal.fire({
                                                            icon: 'question',
                                                            title: 'Confirm',
                                                            text: 'Please confirm pick up of the rental!',
                                                            showCancelButton: true,
                                                            confirmButtonText: `Confirm!`,
                                                            cancelButtonText: 'Cancel!',
                                                        }).then((result) => {
                                                            if (result.isConfirmed) {
                                                                window.location.href = '/admin/rental/onGoing/' + rentalId;
                                                                Swal.fire({
                                                                    title: 'Updating...',
                                                                    html: 'Hold on a few seconds while we update the rental!',
                                                                    timer: 10000,
                                                                    timerProgressBar: false,
                                                                });
                                                            }
                                                        })
                                                    }

                                                    function completedFunction(rentalId) {
                                                        Swal.fire({
                                                            icon: 'question',
                                                            title: 'Confirm',
                                                            text: 'Please confirm completion of the rental!',
                                                            showCancelButton: true,
                                                            confirmButtonText: `Confirm!`,
                                                            cancelButtonText: 'Cancel!',
                                                        }).then((result) => {
                                                            if (result.isConfirmed) {
                                                                window.location.href = '/admin/rental/completed/' + rentalId;
                                                                Swal.fire({
                                                                    title: 'Updating...',
                                                                    html: 'Hold on a few seconds while we update the rental!',
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
    </div>
</div>
</body>
</html>
