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
                            <hr class="dropdown-divider">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/user/rentals/all">All</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/user/rentals/onGoing">On Going</a>
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
            <label style="font-size: 25px; font-weight: bold; margin: 15px auto; color: white">Bookings</label>
        </div>
    </nav>
</div>
<div>
    <div class="container-sm mt-5">
        <form:form id="bookingsForm" modelAttribute="bookings" method="get">
            <div class="row my-5 align-items-center justify-content-center">
                <div class="text-center">
                    <p class="display-6">${type}</p>
                </div>
                <c:forEach items="${bookings}" var="booking">
                    <div class="card text-white bg-dark mb-3" id="view${booking.rentalId}"
                         style="width: 18rem; min-height: 300px; margin: 25px; cursor: pointer" data-bs-toggle="modal"
                         data-bs-target="#modal${booking.rentalId}">
                        <img src="/images/${booking.vehicle.vehicleImagePath}" class="card-img-top mt-3 rounded-3"
                             alt=""
                             width="200" height="200">
                        <div class="card-body">
                            <figure>
                                <blockquote class="blockquote card-title text-center">
                                    <p>${booking.vehicle.model}</p>
                                </blockquote>
                                <figcaption class="blockquote-footer text-center mt-1">
                                    <cite>${booking.status}</cite>
                                </figcaption>
                            </figure>
                        </div>
                    </div>
                    <div class="modal fade" id="modal${booking.rentalId}" tabindex="-1"
                         aria-labelledby="exampleModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <figure>
                                        <blockquote class="blockquote">
                                            <p>${booking.vehicle.model}</p>
                                        </blockquote>
                                        <figcaption class="blockquote-footer">
                                            <cite>${booking.status}</cite>
                                        </figcaption>
                                    </figure>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="mb-3">
                                        <img class="border-1 mx-auto d-block"
                                             src="${pageContext.request.contextPath}/images/${booking.vehicle.vehicleImagePath}"
                                             alt="" width="200" height="200">
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control"
                                               id="bookingVehicleModel${booking.rentalId}"
                                               value="${booking.vehicle.model}" disabled>
                                        <label for="bookingVehicleModel${booking.rentalId}">Vehicle model</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="bookingTotal${booking.rentalId}"
                                               value="${booking.total}" disabled>
                                        <label for="bookingTotal${booking.rentalId}">Total amount</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control"
                                               id="bookingCreatedDate${booking.rentalId}"
                                               value="${booking.createdDate}" disabled>
                                        <label for="bookingCreatedDate${booking.rentalId}">Requested date</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control"
                                               id="bookingCollectionDate${booking.rentalId}"
                                               value="${booking.rentalCollectionDate}" disabled>
                                        <label for="bookingCollectionDate${booking.rentalId}">Collection date</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control"
                                               id="bookingCollectionTime${booking.rentalId}"
                                               value="${booking.rentalCollectionTime}" disabled>
                                        <label for="bookingCollectionTime${booking.rentalId}">Collection time</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control"
                                               id="bookingReturnDate${booking.rentalId}"
                                               value="${booking.rentalReturnDate}" disabled>
                                        <label for="bookingReturnDate${booking.rentalId}">Return date</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control"
                                               id="bookingReturnTime${booking.rentalId}"
                                               value="${booking.rentalReturnTime}" disabled>
                                        <label for="bookingReturnTime${booking.rentalId}">Return time</label>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" style="display: none"
                                            class="btn btn-outline-warning"
                                            onclick="cancelFunction('${booking.rentalId}')"
                                            id="${booking.rentalId} cancel">Cancel booking
                                    </button>
                                    <button type="button" style="display: none"
                                            class="btn btn-outline-info"
                                            onclick="updateFunction('${booking.rentalId}')"
                                            id="${booking.rentalId} update">Update rental
                                    </button>
                                    <a type="button" style="display: none"
                                       class="btn btn-outline-success" data-bs-toggle="modal"
                                       data-bs-target="#extendModal"
                                       onclick="extendBooking('${booking.rentalReturnDate}', '${booking.rentalReturnTime}', '${booking.rentalId}')"
                                       id="${booking.rentalId} extend">Extend rental
                                    </a>
                                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                                        Close
                                    </button>
                                    <p id="status${booking.rentalId}"
                                       style="display: none">${booking.status}</p>
                                    <p id="extended${booking.rentalId}"
                                       style="display: none">${booking.extended}</p>

                                    <script>
                                        document.getElementById("view${booking.rentalId}").onclick = function () {

                                            const status = document.getElementById("status${booking.rentalId}").innerHTML;
                                            const extended = document.getElementById("extended${booking.rentalId}").innerHTML;

                                            if (status === "pending") {
                                                document.getElementById("${booking.rentalId} cancel").style.display = "block";
                                                document.getElementById("${booking.rentalId} update").style.display = "block";
                                            }
                                            if (status === "approved") {
                                                document.getElementById("${booking.rentalId} cancel").style.display = "block";
                                                document.getElementById("${booking.rentalId} update").style.display = "block";
                                            }
                                            if (status === "onGoing" && extended === "false") {
                                                document.getElementById("${booking.rentalId} extend").style.display = "block";
                                            }

                                        }

                                        function cancelFunction(rentalId) {
                                            Swal.fire({
                                                icon: 'question',
                                                title: 'Confirm',
                                                text: 'Please confirm cancellation of the rental!',
                                                showCancelButton: true,
                                                confirmButtonText: `Confirm!`,
                                                cancelButtonText: 'Cancel!',
                                            }).then((result) => {
                                                if (result.isConfirmed) {
                                                    window.location.href = '/user/rental/cancel/' + rentalId;
                                                    Swal.fire({
                                                        title: 'Rejecting...',
                                                        html: 'Hold on a few seconds while we cancel the rental!',
                                                        timer: 10000,
                                                        timerProgressBar: false,
                                                    });
                                                }
                                            })
                                        }

                                        function updateFunction(rentalId) {
                                            Swal.fire({
                                                icon: 'question',
                                                title: 'Update booking',
                                                text: 'Add new equipments for the selected booking?',
                                                showCancelButton: true,
                                                confirmButtonText: `Update!`,
                                                cancelButtonText: 'Cancel!',
                                            }).then((result) => {
                                                if (result.isConfirmed) {
                                                    window.location.href = '/user/rental/update/' + rentalId;
                                                    Swal.fire({
                                                        title: 'Getting booking details...',
                                                        html: 'Hold on a few seconds while we find your booking!',
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
            </div>
        </form:form>
    </div>
    <div>
        <form id="extendForm" method="post">
            <div class="modal fade" id="extendModal" tabindex="-1"
                 aria-labelledby="extendModal"
                 aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header bg-dark text-white">
                            <h5 class="modal-title" id="extendFormTitle">Extend booking</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body bg-dark">
                            <div class="input-group mb-3">
                                <div class="form-floating mb-3">
                                    <input name="extendDropDate" id="extendDropOffDate" type="text" autocomplete="off"
                                           class="form-control" style="margin-right: 25px; width: 220px;"/>
                                    <label for="extendDropOffDate" class="form-label">Drop-off date</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input name="extendDropTime" id="extendDropOffTime" type="time" autocomplete="off"
                                           class="form-control" style="width: 220px"/>
                                    <label for="extendDropOffTime" class="form-label">drop-off time</label>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer bg-dark text-white">
                            <button type="submit" id="extendFormSubmitButton" style="display: none;">Submit</button>
                            <button type="button" onclick="extendBookingPost()" class="btn btn-primary">Request extend
                                booking
                            </button>
                            <button type="button" class="btn btn-outline-light" data-bs-dismiss="modal">
                                Close
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div>
        <form id="accountForm" action="${pageContext.request.contextPath}/user/update/account" method="get">
            <div class="modal fade" id="accountModal" tabindex="-1" aria-labelledby="accountModal" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <figure>
                                <blockquote class="blockquote">
                                    <p>${loggedUser.firstName} ${loggedUser.lastName}</p>
                                </blockquote>
                                <figcaption class="blockquote-footer">
                                    <cite>${loggedUser.bangerScore} Banger Score</cite>
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

    <p style="display: none" id="returning">${loggedUser.returningCustomer}</p>
    <p style="display: none" id="successMessage">${success}</p>
    <p style="display: none" id="errorMessage">${error}</p>
    <div>
        <form id="availabilityForm" action="${pageContext.request.contextPath}/user/search/available" method="post">
            <div class="modal fade" id="rentalModal" tabindex="-1"
                 aria-labelledby="rentalModal"
                 aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header bg-dark text-white">
                            <h5 class="modal-title" id="availabilityFormTitle">Place booking</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body bg-dark">
                            <div class="form-floating mb-3">
                                <input name="pickDate" id="pickupDate" type="text" autocomplete="off"
                                       class="form-control"/>
                                <label for="pickupDate" class="form-label">Pick-up date</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input name="dropDate" id="dropOffDate" type="text" autocomplete="off"
                                       class="form-control"/>
                                <label for="dropOffDate" class="form-label">Drop-off date</label>
                            </div>
                            <div class="input-group mb-3">
                                <div class="form-floating mb-3">
                                    <input name="pickTime" id="pickupTime" type="time" autocomplete="off"
                                           class="form-control" style="width: 220px; margin-right: 25px;"/>
                                    <label for="pickupTime" class="form-label">Pick-up time</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input name="dropTime" id="dropOffTime" type="time" autocomplete="off"
                                           class="form-control" style="width: 220px"/>
                                    <label for="dropOffTime" class="form-label">drop-off time</label>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer bg-dark text-white">
                            <button type="submit" class="btn btn-primary">View Availability</button>
                            <button type="button" class="btn btn-outline-light" data-bs-dismiss="modal">
                                Close
                            </button>
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
                title: "Error!!!",
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
        const pickUpDate = $("#pickupDate");
        const dropOffDate = $("#dropOffDate");
        pickUpDate.datepicker({
            defaultDate: "+1w",
            minDate: 2,
            dateFormat: "yy-mm-dd",
            changeMonth: true,
            numberOfMonths: 1,
            onClose: function (selectedDate) {
                const startingDate = new Date();
                const endingDate = new Date(selectedDate);
                const total = Math.abs(endingDate - startingDate) / 1000;
                const difference = Math.floor(total / (60 * 60 * 24));
                let range = difference + 12;
                dropOffDate.datepicker("option", "minDate", selectedDate);
                dropOffDate.datepicker("option", "maxDate", range);
            }
        });
        dropOffDate.datepicker({
            defaultDate: "+1w",
            dateFormat: "yy-mm-dd",
            minDate: "+14D",
            changeMonth: true,
            numberOfMonths: 1,
            onClose: function (selectedDate) {
                const startingDate = new Date();
                const endingDate = new Date(selectedDate);
                const total = Math.abs(endingDate - startingDate) / 1000;
                const difference = Math.floor(total / (60 * 60 * 24));
                console.log(difference)
                let overflow = difference - 12;
                console.log(overflow);
                pickUpDate.datepicker("option", "minDate", overflow);
                pickUpDate.datepicker("option", "maxDate", selectedDate);
            }
        });
    });

    let setCurrentReturnDate = new Date();
    let setCurrentReturnTime;
    let rentalId;

    function extendBooking(currentReturnDate, currentReturnTime, bookingId) {
        setCurrentReturnDate = currentReturnDate;
        setCurrentReturnTime = currentReturnTime;
        rentalId = bookingId;

        const setMinDate = new Date(currentReturnDate);
        const setMaxDate = new Date();
        setMaxDate.setDate(setMinDate.getDate() + 1);

        $("#extendDropOffDate").datepicker({
            changeMonth: true,
            numberOfMonths: 1,
            dateFormat: "yy-mm-dd",
            defaultDate: setMinDate,
            minDate: setMinDate,
            maxDate: setMaxDate
        }).val()
    }

    function extendBookingPost() {
        const extendForm = document.getElementById("extendForm");

        const extendDropOffDate = $("#extendDropOffDate").val();
        const extendDropOffTime = $("#extendDropOffTime").val();
        const returning = document.getElementById("returning").innerHTML;

        if (extendDropOffDate === "") {
            Swal.fire({
                title: "We may need that back",
                text: "Please enter a drop-off date to check the vehicles availability!",
                icon: "error",
            });
        } else if (extendDropOffTime === "") {
            Swal.fire({
                title: "We ain't got all day yk",
                text: "Please enter a drop-off time to check the vehicles availability!",
                icon: "error",
            });
        } else if (extendDropOffDate !== setCurrentReturnDate && extendDropOffTime.substring(0, 2) > 16) {
            Swal.fire({
                title: "Max limit",
                text: "Bookings can be extended until 4.00pm only!",
                icon: "error",
            });
        } else if (extendDropOffDate !== setCurrentReturnDate && extendDropOffTime.substring(0, 2) === "16" && extendDropOffTime.substring(3, 5) > 0) {
            Swal.fire({
                title: "Max limit",
                text: "Bookings can be extended until 4.00pm only!",
                icon: "error",
            });
        } else if (extendDropOffDate === setCurrentReturnDate && extendDropOffTime.substring(0, 2) < setCurrentReturnTime.substring(0, 2)) {
            Swal.fire({
                title: "Extensions only",
                text: "Bookings new return time cannot be set before the current return time!",
                icon: "error",
            });
        } else if (extendDropOffDate === setCurrentReturnDate && extendDropOffTime.substring(0, 2) === setCurrentReturnTime.substring(0, 2) && extendDropOffTime.substring(3, 5) < setCurrentReturnTime.substring(3, 5)) {
            Swal.fire({
                title: "Extensions only",
                text: "Bookings new return time cannot be set before the current return time!",
                icon: "error",
            });
        } else if (returning === "false" && extendDropOffTime.substring(0, 2) > 18) {
            Swal.fire({
                title: "Garage closes at 6.00p.m.",
                text: "Late returns after 6.00pm are allowed only for returning customers!",
                icon: "error",
            });
        } else if (returning === "false" && extendDropOffTime.substring(0, 2) === "18" && extendDropOffTime.substring(3, 5) > 0) {
            Swal.fire({
                title: "Garage closes at 6.00p.m.",
                text: "Late returns after 6.00pm are allowed only for returning customers!",
                icon: "error",
            });
        } else if (returning === "false" && extendDropOffTime.substring(0, 2) < 8) {
            Swal.fire({
                title: "Garage opens at 8.00a.m.",
                text: "Late returns after 6.00pm are allowed only for returning customers!",
                icon: "error",
            });
        } else {
            extendForm.action = '/user/rental/extend/' + rentalId;
            document.getElementById('extendFormSubmitButton').click();
        }
    }

    const availabilityForm = document.getElementById('availabilityForm');

    availabilityForm.addEventListener('submit', function (event) {
        const returning = document.getElementById("returning").innerHTML;

        const startDate = $("#pickupDate").val();
        const startTime = $("#pickupTime").val();
        const endDate = $("#dropOffDate").val();
        const endTime = $("#dropOffTime").val();

        if (startDate === "") {
            event.preventDefault();
            Swal.fire({
                title: "No Pick-Up?",
                text: "Please enter a pick-up date to check available vehicles!",
                icon: "error",
            });
        } else if (startTime === "") {
            event.preventDefault();
            Swal.fire({
                title: "8? 12? 4? When?",
                text: "Please enter a pick-up time to check available vehicles!",
                icon: "error",
            });
        } else if (endDate === "") {
            event.preventDefault();
            Swal.fire({
                title: "We may need that back",
                text: "Please enter a drop-off date to check available vehicles!",
                icon: "error",
            });
        } else if (endTime === "") {
            event.preventDefault();
            Swal.fire({
                title: "We ain't got all day yk",
                text: "Please enter a drop-off to check available vehicles!",
                icon: "error",
            });
        } else if (startTime.substring(0, 2) < 8) {
            event.preventDefault();
            Swal.fire({
                title: "Top of the Mornin to ya Lad",
                text: "Rental pick-up time can be between 8.00am and 6.00pm only!",
                icon: "error",
            });
        } else if (startTime.substring(0, 2) > 18) {
            event.preventDefault();
            Swal.fire({
                title: "We got a night owl here",
                text: "Rental pick-up time can be between 8.00am and 6.00pm only!",
                icon: "error",
            });
        } else if (startTime.substring(0, 2) === "18" && startTime.substring(3, 5) > 0) {
            event.preventDefault();
            Swal.fire({
                title: "We got a night owl here",
                text: "Rental pick-up time can be between 8.00am and 6.00pm only!",
                icon: "error",
            });
        } else if (startDate === endDate &&
            ((endTime.substring(0, 2) - startTime.substring(0, 2)) === 5) &&
            (endTime.substring(3, 5) - startTime.substring(3, 5) > 0)) {
            event.preventDefault();
            Swal.fire({
                title: "Just another hour to go",
                text: "Minimum rent period is five hours!",
                icon: "error",
            });
        } else if (startDate === endDate && (endTime.substring(0, 2) - startTime.substring(0, 2)) < 5) {
            event.preventDefault();
            Swal.fire({
                title: "Too short",
                text: "Minimum rent period is five hours!",
                icon: "error",
            });
        } else if (returning === "false" && endTime.substring(0, 2) > 18) {
            event.preventDefault();
            Swal.fire({
                title: "Maybe next time",
                text: "Late returns after 6.00pm are allowed only for returning customers!",
                icon: "error",
            });
        } else if (returning === "false" && endTime.substring(0, 2) === "18" && endTime.substring(3, 5) > 0) {
            event.preventDefault();
            Swal.fire({
                title: "Maybe next time",
                text: "Late returns after 6.00pm are allowed only for returning customers!",
                icon: "error",
            });
        } else if (returning === "false" && endTime.substring(0, 2) < 8) {
            event.preventDefault();
            Swal.fire({
                title: "Maybe next time",
                text: "Late returns after 6.00pm are allowed only for returning customers!",
                icon: "error",
            });
        }
    });

</script>