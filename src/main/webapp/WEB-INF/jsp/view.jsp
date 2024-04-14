<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store - ${book.title}</title>
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
<h2>Book: ${book.title}</h2>
<security:authorize access="hasRole('ADMIN')">
    [<a href="<c:url value="/book/delete/${book.id}"/>">Delete</a>]<br/><br/>
    [<a href="<c:url value="/book/edit/${book.id}"/>">Edit</a>]<br/><br/>
</security:authorize>
<i>Author: ${book.author}</i><br/>
<img src="<c:url value='/book/image/${bookId}'/>" class="img-fluid" style="max-width: 300px; max-height: 300px;"/><br/>
Description: <c:out value="${book.description}"/><br/>
Price: ${book.price}<br/>
<c:choose>
    <c:when test="${book.stock < 1}">
        Out of stock
    </c:when>
    <c:otherwise>
        In stock
    </c:otherwise>
</c:choose>



<c:choose>
    <c:when test="${isFavorite}">
        <form action="<c:url value='/favorite/remove/${book.id}'/>" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Remove from Favorites"/>
        </form>
    </c:when>
    <c:otherwise>
        <form action="<c:url value='/favorite/add/${book.id}'/>" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Add to Favorites"/>
        </form>
    </c:otherwise>
</c:choose>



<br/><br/>
Comments:<br/>
<security:authorize access="hasAnyRole('USER', 'ADMIN')">
    <form action="<c:url value='/comments/add/${book.id}'/>" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <label for="commentText">Add a comment:</label><br/>
        <textarea id="commentText" name="commentText" rows="4" cols="50"></textarea><br/>
        <input type="submit" value="Submit"/>
    </form>
</security:authorize>
<c:choose>
    <c:when test="${empty comments}">
        No comments.<br/>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <th>User</th>
                <th>Comment</th>
                <security:authorize access="hasRole('ADMIN')">
                    <th>Delete</th>
                </security:authorize>
            </tr>
            <c:forEach var="comment" items="${comments}">
                <tr>
                    <td><c:out value="${comment.appUser.username}"/></td>
                    <td><c:out value="${comment.comment}"/></td>
                    <security:authorize access="hasRole('ADMIN')">
                        <td>
                            <a href="<c:url value="/comments/delete/${book.id}/${comment.commentId}"/>">Delete</a>
                        </td>
                    </security:authorize>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
<br/>
<a href="<c:url value="/book" />">Return</a>
</body>
</html>