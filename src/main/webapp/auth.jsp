<!DOCTYPE html>
<html>
<head>
    <title>webMobile2</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>
<%--    Login page --%>
    <form class="login-form" action="login" method="post">
        <h1>Login</h1>
        <div class="form-input-material">
            <input type="text" name="email" id="email" placeholder="sovet@mail.ru"  class="form-control-material"  />
            <label for="email">Email</label>
        </div>
        <div class="form-input-material">
            <input type="password" name="confirmPassword" id="password" class="form-control-material" />
            <label for="password">Password</label>
        </div>
        <button type="submit" class="btn btn-primary btn-ghost">Login</button>
        <a href="registrationPage.jsp">Registration</a>
    </form>
</body>

</html>


