<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store - User Page (${principal.name})</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<nav>
    <c:url var="logoutUrl" value="/logout" />
    <form action="${logoutUrl}" method="post">
        <input type="submit" value="Logout" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>

    <security:authorize access="isAuthenticated()">
        <c:url var="userUrl" value="/user/own/${principal.name}" />
        <a href="${userUrl}">User Page</a>
    </security:authorize>
</nav>

<h1>${appUser.fullName}'s User Page</h1>
[<a href="<c:url value="/user/edit/${principal.name}"/>">Edit</a>]
<p><strong>Username:</strong> ${appUser.username}</p>
<p><strong>Email Address:</strong> ${appUser.emailAddress}</p>
<p><strong>Delivery Address:</strong> ${appUser.deliveryAddress}</p>
<security:authorize access="hasRole('ADMIN')">
    <p><strong>Roles:</strong></p>
    <ul>
        <c:forEach var="role" items="${appUser.roles}">
            <li>${role}</li>
        </c:forEach>
    </ul>
</security:authorize>
</body>
</html>