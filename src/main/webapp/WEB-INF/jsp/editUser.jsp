<!DOCTYPE html>
<html>
<head>
    <title>Book Store</title>
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
            <h2 class="text-center">Edit User Account</h2>
            <form:form method="POST" action="/OnlineBookStore/user/update" modelAttribute="registerUserForm" class="form-group">
                <div class="form-group">
                    <form:label path="username">Username</form:label>
                    <form:errors path="username" cssClass="error" />
                    <security:authorize access="hasRole('ADMIN')">
                        <form:input type="text" value="${appUser.username}" path="username" class="form-control"/>
                    </security:authorize>
                    <security:authorize access="!hasRole('ADMIN')">
                        ${appUser.username}
                        <form:hidden path="username" value="${appUser.username}" />
                    </security:authorize>
                </div>
                <div class="form-group">
                    <form:label path="currentPassword">Current Password</form:label>
                    <form:errors path="currentPassword" cssClass="error" />
                    <form:password path="currentPassword" class="form-control" />
                </div>
                <div class="form-group">
                    <form:label path="newPassword">New Password</form:label>
                    <form:errors path="newPassword" cssClass="error" />
                    <form:password path="newPassword" class="form-control" />
                </div>
                <div class="form-group">
                    <form:label path="confirmPassword">Confirm New Password</form:label>
                    <form:errors path="confirmPassword" cssClass="error" />
                    <form:password path="confirmPassword" class="form-control" />
                </div>
                <div class="form-group">
                    <form:label path="fullName">Full Name</form:label><br/>
                    <form:errors path="fullName" cssClass="error" />
                    <form:input type="text" path="fullName" value="${appUser.fullName}" class="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="emailAddress">Email Address</form:label><br/>
                    <form:errors path="emailAddress" cssClass="error" />
                    <form:input type="email" path="emailAddress" value="${appUser.emailAddress}" class="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="deliveryAddress">Delivery Address</form:label><br/>
                    <form:errors path="deliveryAddress" cssClass="error" />
                    <form:input type="text" path="deliveryAddress" value="${appUser.deliveryAddress}" class="form-control"/>
                </div>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="form-group">
                        <form:label path="roles">Roles</form:label>
                        <form:errors path="roles" cssClass="error" />
                        <div class="form-check">
                            <form:checkbox path="roles" value="ROLE_USER" checked="checked"/>ROLE_USER
                            <form:checkbox path="roles" value="ROLE_ADMIN"/>ROLE_ADMIN
                        </div>
                    </div>
                </security:authorize>
                <security:authorize access="!hasRole('ROLE_ADMIN')">
                    <form:hidden path="roles" value="ROLE_USER" />
                </security:authorize>
                <div class="form-group">
                    <input type="submit" value="Update User" class="btn btn-primary"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </div>
            </form:form>
            <a href="<c:url value="/user/own/${appUser.username}"/>" class="btn btn-secondary mt-3">Return</a>
        </div>
    </div>
</div>
</body>
</html>