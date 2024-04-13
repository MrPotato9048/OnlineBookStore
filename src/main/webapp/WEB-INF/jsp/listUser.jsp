<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store - User Management</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Logout" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<br/><br/>
<a href="<c:url value="/book" />">Return to book list</a>
<h2>Users</h2>
<a href="<c:url value="/user/create" />">Create a User</a><br/><br/>
<c:choose>
    <c:when test="${fn:length(appUsers) == 0}">
        <i>There are no users in the system.</i>
    </c:when>
    <c:otherwise>
        <table>
            <tr><th>Username</th><th>Password</th><th>Full Name</th><th>Email Address</th><th>Delivery Address</th><th>Roles</th><th>Action</th></tr>
            <c:forEach items="${appUsers}" var="user"><tr>
                <td><a href="<c:url value='/user/edit/${user.username}' />">${user.username}</a></td>
                <td>${user.password}</td>
                <td>${user.fullName}</td>
                <td>${user.emailAddress}</td>
                <td>${user.deliveryAddress}</td>
                <td>
                    <c:forEach items="${user.roles}" var="role" varStatus="status">
                        <c:if test="${!status.first}">, </c:if>
                        ${role.role}
                    </c:forEach>
                </td>
                <td>[<a href="<c:url value="/user/delete/${user.username}" />">Delete</a>]</td>
            </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>