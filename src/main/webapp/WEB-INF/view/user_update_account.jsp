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
                            Rentals
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="rentalsDropdown">
                            <li><a class="dropdown-item" data-bs-toggle="modal"
                                   data-bs-target="#rentalModal" style="cursor: pointer">Rent now</a>
                            </li>
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
                    <li class="nav-item">
                        <a class="nav-link" data-bs-toggle="modal"
                           data-bs-target="#rentalModal" style="cursor: pointer">Rent now</a>
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
            <label style="font-size: 25px; font-weight: bold; margin: 15px auto;"><a
                    style="text-decoration: none; color: white;"
                    href="${pageContext.request.contextPath}/user/home">
                <i class="bi bi-arrow-bar-left"></i> Update Account
            </a></label>
        </div>
    </nav>
</div>
<div>
    <div class="container-sm mt-5">
        <form:form id="form" modelAttribute="loggedUser" method="post" enctype="multipart/form-data">
            <div class="row container justify-content-center">
                <div class="col-3">
                    <img src="/images/${loggedUser.userImagePath}" class="rounded m-auto mb-3" alt="..." width="250"
                         height="250">
                    <img src="/images/${loggedUser.licenseImagePath}" class="rounded m-auto mb-3" alt="..." width="250"
                         height="250">
                    <img src="/images/${loggedUser.alternateImagePath}" class="rounded m-auto mb-3" alt="..."
                         width="250"
                         height="250">
                </div>
                <div class="col-4">
                    <form:input path="userId" cssStyle="display: none"/>
                    <div class="form-floating mt-5 mb-3">
                        <form:input path="firstName" type="text" class="form-control" id="firstname"/>
                        <label for="firstname">Firstname</label>
                    </div>
                    <div class="form-floating mb-3">
                        <form:input path="lastName" type="text" class="form-control" id="lastname"/>
                        <label for="lastname">Lastname</label>
                    </div>
                    <div class="form-floating mb-3">
                        <form:input path="contact" type="text" class="form-control" id="contact"/>
                        <label for="contact">Contact</label>
                    </div>
                    <div class="form-floating mb-3">
                        <form:input path="email" type="text" class="form-control" id="email"/>
                        <label for="email">Email</label>
                    </div>
                    <div class="form-floating mb-3">
                        <form:input path="password" type="password" class="form-control" id="password"/>
                        <label for="password">Password</label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="userImageInsert">Profile Image</label>
                        <input name="userImage" type="file" class="form-control" id="userImageInsert"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="licenseImageInsert">Drivers License Image</label>
                        <input name="licenseImage" type="file" class="form-control" id="licenseImageInsert"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="altImageInsert">Alternate image</label>
                        <input name="alternateImage" type="file" class="form-control" id="altImageInsert"/>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-outline-secondary m-auto d-block">Update</button>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
    <p style="display: none" id="successMessage">${success}</p>
    <p style="display: none" id="errorMessage">${error}</p>
    <div>
        <form id="accountForm" action="${pageContext.request.contextPath}/user/update/account" method="get">
            <div class="modal fade" id="accountModal" tabindex="-1" aria-labelledby="accountModal" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="accountModalLabel">View account</h5>
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
                                <input type="text" class="form-control" id="userLicense"
                                       value="${loggedUser.driversLicenseNumber}" disabled>
                                <label for="userLicense">License Number</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="userDob"
                                       value="${loggedUser.dateOfBirth}" disabled>
                                <label for="userDob">Date of Birth</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="userCreated"
                                       value="${loggedUser.createdDate}" disabled>
                                <label for="userCreated">Joined</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="userUpdated"
                                       value="${loggedUser.updatedDate}" disabled>
                                <label for="userUpdated">Last updated</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" placeholder="License"
                                       id="licenseImage" disabled/>
                                <label for="licenseImage">License : </label>
                                <br>
                                <img class="rounded-3 mx-auto d-block"
                                     src="${pageContext.request.contextPath}/images/${loggedUser.licenseImagePath}"
                                     alt="" width="450" height="450">
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" placeholder="alternate"
                                       id="altImage" disabled/>
                                <label for="altImage">Alternate image : </label>
                                <br>
                                <img class="rounded-3 mx-auto d-block"
                                     src="${pageContext.request.contextPath}/images/${loggedUser.alternateImagePath}"
                                     alt="" width="450" height="450">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-outline-warning">Update account</button>
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

    const form = document.getElementById('form');
    form.addEventListener('submit', function (event) {
        const mailFormat = /[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]{2,3}/;
        const nameFormat = /[^a-zA-Z\s]+/;
        const contactFormat = /^\d+$/;

        const firstname = $("#firstname").val();
        const lastname = $("#lastname").val();
        const email = $("#email").val();
        const phone = $("#contact").val();
        const password = $("#password").val();

        if (firstname.length < 2 || firstname.length > 20 || firstname.indexOf(' ') >= 0 || firstname.match(nameFormat)) {
            event.preventDefault();
            Swal.fire({
                title: "Error in first name!!!",
                text: "-> First name cannot be kept empty!" +
                    "-> First name cannot contain white spaces." +
                    "-> First name cannot contain numbers or symbols." +
                    "-> First name should contain 2 to 20 characters!",
                icon: "error",
            });
        } else if (lastname.length < 2 || lastname.length > 40 || lastname.match(nameFormat)) {
            event.preventDefault();
            Swal.fire({
                title: "Error in last name!!!",
                text: "-> Last name cannot be kept empty!" +
                    "-> Last name cannot contain numbers or symbols!" +
                    "-> Last name cannot should contain 2 to 40 characters!",
                icon: "error",
            });
        } else if (email.length < 12 || email.length > 25 || email.indexOf(' ') >= 0 || !email.match(mailFormat)) {
            event.preventDefault();
            Swal.fire({
                title: "Error in email!!!",
                text: "-> Email cannot be kept empty!" +
                    "-> Email should contain between 12 to 25 characters!" +
                    "-> Email cannot contain whitespaces!" +
                    "-> Example email format: youremail@email.com",
                icon: "error",
            });
        } else if (phone.length !== 10 || phone.indexOf(' ') >= 0 || !phone.match(contactFormat)) {
            event.preventDefault();
            Swal.fire({
                title: "Error in Phone number!!!",
                text: "->Phone number cannot be kept empty" +
                    "-> Phone number cannot contain white spaces!" +
                    "-> Phone number should contain 10 characters!",
                icon: "error",
            });
        } else if (password.length !== 0) {
            if (password.length < 3 || password.length > 25) {
                event.preventDefault();
                Swal.fire({
                    title: "Error in Password!!!",
                    text: "-> Password should contain 3 to 25 characters!",
                    icon: "error",
                });
            }
        } else {
            Swal.fire({
                title: 'Updating...',
                html: 'Hold on a few seconds while we update your account!',
                timer: 10000,
                timerProgressBar: false,
            });
        }
    });
</script>