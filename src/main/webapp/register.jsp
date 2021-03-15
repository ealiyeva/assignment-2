<!DOCTYPE html>
<html>
<head>
    <title>WebMobile 2 - Homework 2</title>
    <link rel="stylesheet" href="main.css">
</head>
<body>
    <div class="login-box">
    <h2>Register</h2>
    <form action="register" method="post">
        <div class="user-box">
            <input type="text" name="email" id="email" required="required">
            <label for="email">Email</label>
        </div>
        <div class="user-box">
            <input type="password" name="password" id="password" required="required">
            <label for="password">Password</label>
        </div>
        <div class="user-box">
            <input type="password" name="confirm_password" id="confirm_password" required="required">
            <label for="confirm_password">Password Confirmation</label>
        </div>
        <a href="#" onclick="this.closest('form').submit();return false;">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            Register
        </a>
    </form>
</div>
</body>

</html>
