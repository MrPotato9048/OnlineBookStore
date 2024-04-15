<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store - Personal Information (${principal.name})</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/OnlineBookStore/">Online Book Store</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <security:authorize access="isAuthenticated()">
                <li class="nav-item">
                    <c:url var="userUrl" value="/user/own/${principal.name}" />
                    <a class="nav-link" href="${userUrl}">User Page</a>
                </li>
                <li class="nav-item">
                    <c:url var="cartUrl" value="/shoppingCart" />
                    <a class="nav-link" href="${cartUrl}">Shopping Cart</a>
                </li>
                <li class="nav-item">
                    <c:url var="ordersUrl" value="/orders" />
                    <a class="nav-link" href="${ordersUrl}">Orders</a>
                </li>
                <li class="nav-item">
                    <c:url var="logoutUrl" value="/logout" />
                    <form class="form-inline" action="${logoutUrl}" method="post">
                        <input class="btn btn-outline-danger my-2 my-sm-0" type="submit" value="Logout" />
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                </li>
            </security:authorize>
            <security:authorize access="isAnonymous()">
                <li class="nav-item">
                    <c:url var="loginUrl" value="/login" />
                    <a class="btn btn-outline-success my-2 my-sm-0" href="${loginUrl}">Login</a>
                </li>
            </security:authorize>
        </ul>
    </div>
</nav>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <h2 class="text-center">${appUser.fullName}'s Personal Information</h2>
            <a href="<c:url value="/user/edit/${appUser.username}"/>" class="btn btn-primary mb-3">Edit</a>
            <table class="table">
                <tbody>
                    <tr>
                        <th>Username</th>
                        <td>${appUser.username}</td>
                    </tr>
                    <tr>
                        <th>Email Address</th>
                        <td>${appUser.emailAddress}</td>
                    </tr>
                    <tr>
                        <th>Delivery Address</th>
                        <td>${appUser.deliveryAddress}</td>
                    </tr>
                    <security:authorize access="hasRole('ADMIN')">
                        <tr>
                            <th>Roles</th>
                            <td>
                                <ul class="list-unstyled">
                                    <c:forEach var="role" items="${appUser.roles}">
                                        <li>${role.role}</li>
                                    </c:forEach>
                                </ul>
                            </td>
                        </tr>
                    </security:authorize>
                </tbody>
            </table>

            <p><strong>Favorite Books:</strong></p>
            <c:choose>
                <c:when test="${fn:length(favoriteBooks) == 0}">
                    <p class="text-muted">There are no books in the favorite Books.</p>
                </c:when>
                <c:otherwise>
                    <ul class="list-unstyled">
                        <c:forEach items="${favoriteBooks}" var="favoriteBook">
                            <li>${favoriteBook.book.title}</li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>