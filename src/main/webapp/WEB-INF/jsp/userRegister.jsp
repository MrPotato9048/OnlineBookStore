<!DOCTYPE html>
<head>
    <title>Online Book Store - User Registration</title>
    <style>
        .error {
            color: red;
            font-weight: bold;
            display: block;
        }
    </style>
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
            <h2 class="text-center">User Registration</h2>
            <form:form method="POST" modelAttribute="appUser" class="d-flex flex-column align-items-center">
                <div class="form-group">
                    <form:label path="username">Username</form:label>
                    <form:errors path="username" cssClass="error" />
                    <form:input type="text" path="username" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="password">Password</form:label>
                    <form:errors path="password" cssClass="error" />
                    <form:input type="password" path="password" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="confirmPassword">Confirm Password</form:label>
                    <form:errors path="confirmPassword" cssClass="error" />
                    <form:input type="password" path="confirmPassword" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="fullName">Full Name</form:label>
                    <form:errors path="fullName" cssClass="error" />
                    <form:input type="text" path="fullName" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="emailAddress">Email Address</form:label>
                    <form:errors path="emailAddress" cssClass="error" />
                    <form:input type="email" path="emailAddress" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="deliveryAddress">Delivery Address</form:label>
                    <form:errors path="deliveryAddress" cssClass="error" />
                    <form:input type="text" path="deliveryAddress" cssClass="form-control"/>
                </div>
                <style>
                    .hidden {
                        display: none;
                    }
                </style>
                <form:checkbox path="roles" value="ROLE_USER" checked="true" class="hidden"/>
                <span class="hidden">ROLE_USER</span>
                <input type="submit" value="Create account" class="btn btn-primary mt-3">
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
</html>