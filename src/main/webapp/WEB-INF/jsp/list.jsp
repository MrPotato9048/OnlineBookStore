<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store - Book List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        .card-img-top {
            object-fit: cover;
            height: 15rem;
        }
    </style>
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
    <h2>Books</h2>
    <security:authorize access="hasRole('ADMIN')">
        <a href="<c:url value="/user/" />" class="btn btn-primary">Manage User Accounts</a><br/><br/>
        <a href="<c:url value="/book/create" />" class="btn btn-success">Add a Book</a><br/><br/>
    </security:authorize>
    <c:choose>
        <c:when test="${fn:length(bookDatabase) == 0}">
            <i>There are no books in the store.</i>
        </c:when>
        <c:otherwise>
            <div class="card-deck">
                <c:forEach items="${bookDatabase}" var="entry" varStatus="loop">
                    <div class="col-md-4 mb-4">
                        <div class="card h-100">
                            <a href="<c:url value="/book/view/${entry.id}"/>">
                                <img src="<c:url value='/book/image/${entry.id}'/>" class="card-img-top img-fluid" alt="${entry.title}">
                                <div class="card-body">
                                    <h5 class="card-title">${entry.title}</h5>
                                </div>
                            </a>
                            <security:authorize access="hasRole('ADMIN')">
                                <div class="card-footer">
                                    <a href="<c:url value="/book/edit/${entry.id}"/>" class="btn btn-primary">Edit</a>
                                    <a href="<c:url value="/book/delete/${entry.id}"/>" class="btn btn-danger">Delete</a>
                                </div>
                            </security:authorize>
                        </div>
                    </div>
                    <c:if test="${loop.index % 3 == 2}">
                        </div><div class="card-deck">
                    </c:if>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>