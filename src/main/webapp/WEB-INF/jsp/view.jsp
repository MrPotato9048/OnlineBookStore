<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store - ${book.title}</title>
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
    <h2>${book.title}</h2>
    <security:authorize access="hasRole('ADMIN')">
        <a href="<c:url value="/book/edit/${book.id}"/>" class="btn btn-primary">Edit</a>
        <a href="<c:url value="/book/delete/${book.id}"/>" class="btn btn-danger">Delete</a>
    </security:authorize>
    <div class="d-flex justify-content-center">
        <img src="<c:url value='/book/image/${bookId}'/>" class="img-fluid" style="max-width: 300px; max-height: 300px;"/>
    </div>
    <table class="table">
        <colgroup>
            <col style="width:30%">
            <col style="width:70%">
        </colgroup>
        <tbody>
            <tr>
                <th>Author</th>
                <td>${book.author}</td>
            </tr>
            <tr>
                <th>Description</th>
                <td><c:out value="${book.description}"/></td>
            </tr>
            <tr>
                <th>Price</th>
                <td>$${book.price}</td>
            </tr>
        </tbody>
    </table>
    <c:choose>
        <c:when test="${book.stock < 1}">
            <div class="alert alert-danger" role="alert">
                Out of stock
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-success" role="alert">
                In stock
            </div>
        </c:otherwise>
    </c:choose>
    <security:authorize access="isAuthenticated()">
        <div style="display: flex; justify-content: space-between;">
            <form action="<c:url value='/shoppingCart/add/${book.id}'/>" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="quantity" value="1"/>
                <input type="submit" value="Add to Cart" class="btn btn-primary"/>
            </form>
            <c:choose>
                <c:when test="${isFavorite}">
                    <form action="<c:url value='/favorite/remove/${book.id}'/>" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="submit" value="Remove from Favorites" class="btn btn-secondary"/>
                    </form>
                </c:when>
                <c:otherwise>
                    <form action="<c:url value='/favorite/add/${book.id}'/>" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="submit" value="Add to Favorites" class="btn btn-primary"/>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </security:authorize>
    <br/><br/>
    <h2>Comments:</h2>
    <security:authorize access="isAuthenticated()">
        <form action="<c:url value='/comments/add/${book.id}'/>" method="post" class="mb-3">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
                <label for="commentText">Add a comment:</label>
                <textarea id="commentText" name="commentText" rows="4" class="form-control" style="resize: none;"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </security:authorize>
    <c:choose>
        <c:when test="${empty comments}">
            <p class="text-muted">No comments.</p>
        </c:when>
        <c:otherwise>
            <c:forEach var="comment" items="${comments}">
                <div class="row border-top border-bottom py-3">
                    <div class="col-4"><c:out value="${comment.appUser.username}"/></div>
                    <div class="col-5"><c:out value="${comment.comment}"/></div>
                    <security:authorize access="hasRole('ADMIN')">
                        <div class="col-1">
                            <a href="<c:url value="/comments/delete/${book.id}/${comment.commentId}"/>" class="btn btn-danger">Delete</a>
                        </div>
                    </security:authorize>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    <br/>
    <a href="<c:url value="/book" />" class="btn btn-secondary">Return</a>
</div>
</body>
</html>