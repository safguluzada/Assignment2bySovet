<!DOCTYPE html>
<html>
<head>
    <title>webMobile2</title>
    <link rel="stylesheet" href="registerPageStyle.css">
</head>
<body>
<div class="register-form">
    <h1>Register</h1>
    <form action="register" method="post">
        <div class="form-input-material">
            <input type="text" name="email" id="email" required="required">
            <label for="email">Email</label>
            <h3><%
                request.getAttribute("emailDuplicate");
            %></h3>
        </div>
        <div class="form-input-material">
            <input type="password" name="password" id="password" required="required">
            <label for="password">Password</label>
        </div>
        <div class="form-input-material">
            <input type="Password" name="confirmPassword" id="confirmPassword" required="required">
            <label for="confirmPassword">Confirm Password</label>
        </div>

        <button type="submit" class="btn btn-primary btn-ghost">Register</button>
        <a href="auth.jsp">Login page</a>
    </form>
</div>
</body>

</html>