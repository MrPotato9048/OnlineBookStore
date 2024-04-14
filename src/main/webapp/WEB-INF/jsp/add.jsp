<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store - Add Book</title>
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
            <h2 class="text-center">Add a Book</h2>
            <form:form method="POST" enctype="multipart/form-data" modelAttribute="bookForm" class="d-flex flex-column align-items-start">
                <div class="form-group">
                    <form:label path="title">Title</form:label>
                    <form:input type="text" path="title" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="author">Author</form:label>
                    <form:input type="text" path="author" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="description">Description</form:label>
                    <form:textarea rows="5" cols="30" path="description" cssClass="form-control" style="resize: none;"/>
                </div>
                <div class="form-group">
                    <form:label path="stock">Stock</form:label>
                    <form:input type="text" path="stock" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="price">Price</form:label>
                    <form:input type="text" path="price" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="image">Cover image</form:label>
                    <form:input type="file" path="image" accept="image/*" cssClass="form-control-file"/>
                </div>
                <input type="submit" value="Submit" class="btn btn-primary mt-3"/>
            </form:form>
            <a href="<c:url value="/book/list"/>" class="btn btn-secondary mt-3">Return</a>
        </div>
    </div>
</div>
</body>
</html>