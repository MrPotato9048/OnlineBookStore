<!DOCTYPE html>
<html>
<head><title>Book Store</title></head>
<body>
<c:url var="logoutUrl" value="/logout" />
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Logout" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<h2>Books</h2>
<security:authorize access="hasRole('ADMIN')">
    <a href="<c:url value="/user/" />">Manage User Accounts</a><br/><br/>
</security:authorize>
<a href="<c:url value="/book/create" />">Add a Book</a><br/><br/>
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