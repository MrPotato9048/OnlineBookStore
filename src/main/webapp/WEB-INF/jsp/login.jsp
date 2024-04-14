<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store - Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/OnlineBookStore/">Online Book Store</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
</nav>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <c:if test="${param.error != null}">
                <div class="alert alert-danger" role="alert">Login failed.</div>
            </c:if>
            <c:if test="${param.logout != null}">
                <div class="alert alert-success" role="alert">You have logged out.</div>
            </c:if>
            <h2 class="text-center">Login</h2>
            <form action="login" method="POST" class="d-flex flex-column align-items-center">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" class="form-control"/>
                </div>
                <div class="form-check">
                    <input type="checkbox" id="remember-me" name="remember-me" class="form-check-input"/>
                    <label for="remember-me" class="form-check-label">Remember me</label>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary mt-3">Log In</button>
            </form>
            <form action="register" method="get" class="d-flex justify-content-center mt-3">
                <button type="submit" class="btn btn-secondary">Register</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>