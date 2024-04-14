<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store - Error ${status}</title>
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
        <div class="col-md-6">
            <h2 class="text-center">Error Page</h2>
            <c:choose>
                <c:when test="${empty message}">
                    <p class="text-danger">Something went wrong.</p>
                    <ul class="list-unstyled">
                        <li>Status Code: ${status}</li>
                        <li>Exception: ${exception}</li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <p class="text-danger">${message}</p>
                </c:otherwise>
            </c:choose>
            <a href="<c:url value="/book" />" class="btn btn-secondary mt-3">Return to book list</a>
        </div>
    </div>
</div>
</body>
</html>