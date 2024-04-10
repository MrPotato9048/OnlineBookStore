<!DOCTYPE html>
<html>
<head><title>Book Store</title></head>
<body>
<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<h2>Edit Book #${book.id}</h2>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="bookForm">
    <form:label path="title">Title</form:label><br/>
    <form:input type="text" path="title"/><br/><br/>
    <form:label path="author">Author</form:label><br/>
    <form:input type="text" path="author"/><br/><br/>
    <form:label path="description">Description</form:label><br/>
    <form:textarea rows="5" cols="30" path="description"/><br/><br/>
    <form:label path="stock">Stock</form:label><br/>
    <form:input type="text" path="stock"/><br/><br/>
    <form:label path="price">Price</form:label><br/>
    <form:input type="text" path="price"/><br/><br/>
    <input type="submit" value="Save"/><br/><br/>
</form:form>
<a href="<c:url value="/book"/>">Return to book list</a>
</body>
</html>