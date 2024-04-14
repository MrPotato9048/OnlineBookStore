<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store - Add User</title>
    <style>
        .error {
            color: red;
            font-weight: bold;
            display: block;
        }
    </style>
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
            <h2 class="text-center">Add User</h2>
            <form:form method="POST" modelAttribute="appUser" class="d-flex flex-column align-items-start">
                <div class="form-group">
                    <form:label path="username">Username</form:label>
                    <form:errors path="username" cssClass="error" />
                    <form:input type="text" path="username" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="password">Password</form:label>
                    <form:errors path="password" cssClass="error" />
                    <form:input type="password" path="password" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="confirmPassword">Confirm Password</form:label>
                    <form:errors path="confirmPassword" cssClass="error" />
                    <form:input type="password" path="confirmPassword" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="fullName">Full Name</form:label>
                    <form:errors path="fullName" cssClass="error" />
                    <form:input type="text" path="fullName" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="emailAddress">Email Address</form:label>
                    <form:errors path="emailAddress" cssClass="error" />
                    <form:input type="email" path="emailAddress" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="deliveryAddress">Delivery Address</form:label>
                    <form:errors path="deliveryAddress" cssClass="error" />
                    <form:input type="text" path="deliveryAddress" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="roles">Roles</form:label>
                    <form:errors path="roles" cssClass="error" />
                    <div class="form-check">
                        <form:checkbox path="roles" value="ROLE_USER" cssClass="form-check-input"/>
                        <label>ROLE_USER</label>
                    </div>
                    <div class="form-check">
                        <form:checkbox path="roles" value="ROLE_ADMIN" cssClass="form-check-input"/>
                        <label>ROLE_ADMIN</label>
                    </div>
                </div>
                <input type="submit" value="Add User" class="btn btn-primary mt-3"/>
            </form:form>
            <a href="<c:url value="/user"/>" class="btn btn-secondary mt-3">Return</a>
        </div>
    </div>
</div>
</body>
</html>