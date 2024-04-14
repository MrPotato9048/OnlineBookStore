<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store - Book List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<nav>
    <security:authorize access="isAuthenticated()">
        <c:url var="logoutUrl" value="/logout" />
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Logout" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </security:authorize>

    <security:authorize access="isAnonymous()">
        <c:url var="loginUrl" value="/login" />
        <form action="${loginUrl}" method="get">
            <input type="submit" value="Login" />
        </form>
    </security:authorize>
    
    <security:authorize access="isAuthenticated()">
        <c:url var="userUrl" value="/user/own/${principal.name}" />
        <a href="${userUrl}">User Page</a>
    </security:authorize>
</nav>
<h2>Books</h2>
<security:authorize access="hasRole('ADMIN')">
    <a href="<c:url value="/user/" />">Manage User Accounts</a><br/><br/>
    <a href="<c:url value="/book/create" />">Add a Book</a><br/><br/>
</security:authorize>
<c:choose>
    <c:when test="${fn:length(bookDatabase) == 0}">
        <i>There are no books in the store.</i>
    </c:when>
    <c:otherwise>
        <c:forEach items="${bookDatabase}" var="entry">
            <a href="<c:url value="/book/view/${entry.id}"/>">${entry.title}</a>
            <security:authorize access="hasRole('ADMIN')">
                [<a href="<c:url value="/book/delete/${entry.id}"/>">Delete</a>]<br/>
            </security:authorize>
        </c:forEach>
    </c:otherwise>
</c:choose>
</body>
</html>