<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
</head>
<body>
<h2>User Registration</h2>
<form action="/register" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <label for="fullName">Full Name:</label>
    <input type="text" id="fullName" name="fullName" required><br>

    <label for="emailAddress">Email Address:</label>
    <input type="email" id="emailAddress" name="emailAddress" required><br>

    <label for="deliveryAddress">Delivery Address:</label>
    <input type="text" id="deliveryAddress" name="deliveryAddress" required><br><br/>

    <input type="submit" value="Create account">
</form>
</body>
</html>