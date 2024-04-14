<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store - Order</title>
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
    <div class="d-flex flex-column">
        <h2>Your Orders</h2>
        <c:if test="${not empty orders}">
            <c:forEach items="${orders}" var="order">
                <h3>Order ID: ${order.id}</h3>
                <p>Date: <fmt:formatDate value="${order.date}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Book</th>
                        <th>Quantity</th>
                        <th>Total Price</th>
                    </tr>
                    </thead>
                    <c:forEach items="${order.shoppingCartItems}" var="item">
                        <tr>
                            <td>${item.book.title}</td>
                            <td>${item.quantity}</td>
                            <td><${item.totalPrice}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th colspan="2">Order Total:</th>
                        <td>${order.totalPrice}</td>
                    </tr>
                </table>
            </c:forEach>
        </c:if>
        <c:if test="${empty orders}">
            <div class="mt-auto">
                <p class="text-muted">You have no orders.</p>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>