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
        <h2>Your Shopping Cart</h2>
        <c:if test="${not empty shoppingCart.shoppingCartItems}">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Book</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Total Price</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${shoppingCart.shoppingCartItems}" var="item" varStatus="status">
                    <tr>
                        <td>${item.book.title}</td>
                        <td>${item.quantity}</td>
                        <td><fmt:formatNumber type="currency" currencySymbol="$" value="${itemTotalPrices[status.index]}" /></td>
                        <td>
                            <form action="<c:url value='/shoppingCart/remove/${item.book.id}' />" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-danger">Remove</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <th colspan="2">Total</th>
                    <td>$${totalPrice}</td>
                    <td></td>
                </tr>
                </tbody>
            </table>
            <form action="<c:url value='/shoppingCart/checkout' />" method="get">
                <button type="submit" class="btn btn-primary">Proceed to Checkout</button>
            </form>
            <a href="<c:url value='/book/list' />" class="btn btn-secondary mt-2">Continue Shopping</a>
        </c:if>
        <c:if test="${empty shoppingCart.shoppingCartItems}">
            <div class="mt-auto">
                <p class="text-muted">Your shopping cart is empty.</p>
            </div>
            <a href="<c:url value='/book/list' />" class="btn btn-secondary mt-2">Return</a>
        </c:if>
    </div>
</div>
</body>
</html>