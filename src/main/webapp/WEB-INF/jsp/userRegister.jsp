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
<h2>User Registration</h2>
<form:form method="POST" modelAttribute="appUser">
    <form:label path="username">Username</form:label><br/>
    <form:errors path="username" cssClass="error" />
    <form:input type="text" path="username"/><br/><br/>
    <form:label path="password">Password</form:label><br/>
    <form:errors path="password" cssClass="error" />
    <form:input type="text" path="password"/><br/><br/>
    <form:label path="confirmPassword">Confirm Password</form:label><br/>
    <form:errors path="confirmPassword" cssClass="error" />
    <form:input type="text" path="confirmPassword"/><br/><br/>
    <form:label path="fullName">Full Name</form:label><br/>
    <form:errors path="fullName" cssClass="error" />
    <form:input type="text" path="fullName" /><br/><br/>
    <form:label path="emailAddress">Email Address</form:label><br/>
    <form:errors path="emailAddress" cssClass="error" />
    <form:input type="email" path="emailAddress" /><br/><br/>
    <form:label path="deliveryAddress">Delivery Address</form:label><br/>
    <form:errors path="deliveryAddress" cssClass="error" />
    <form:input type="text" path="deliveryAddress" /><br/><br/>
    <style>
        .hidden {
            display: none;
        }
    </style>
    <form:checkbox path="roles" value="ROLE_USER" checked="true" class="hidden"/>
    <span class="hidden">ROLE_USER</span>
    <br/><br/>
    <input type="submit" value="Create account">
</form:form>
</body>
</html>
</html>