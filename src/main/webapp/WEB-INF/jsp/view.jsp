<!DOCTYPE html>
<html>
<head><title>Book - ${book.title}</title></head>
<body>
<h2>Book: ${book.title}</h2>
[<a href="<c:url value="/book/delete/${book.id}"/>">Delete</a>]<br/><br/>
<i>Author: ${book.author}</i><br/>
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
<br/><br/>
<a href="<c:url value="/book" />">Return</a>
</body>
</html>