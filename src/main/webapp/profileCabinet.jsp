<%@ page import="java.sql.ResultSet" %>
<%@ page import="utils.DBUtils" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<head>
    <title>webmobile2</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="login-box" style="left: 25%!important;">
    <form action="profile" method="post" class="login-form">
        <h1 style="text-align: center">Profile</h1>

        <div class="form-input-material">
            <label for="firstname">Firstname</label>
            <input type="text" name="firstname" id="firstname" required="required" >
        </div>
        <div class="form-input-material">
            <label for="surname">Surname</label>
            <input type="text" name="surname" id="surname" required="required" >
        </div>
        <div class="form-input-material">
            <label for="country">Country</label>
            <input type="text" name="country" id="country" required="required" >
        </div>
        <div class="form-input-material">
            <label for="age">Age</label>
            <input type="text" name="age" id="age" required="required" >
        </div>
        <div class="form-input-material">
            <label for="gender">Gender</label>
            <input type="text" name="gender" id="gender" required="required" >
        </div>

        <button type="submit" class="btn btn-primary btn-ghost">Save</button>
    </form>
</div>
    <div class="clearfix">

    </div>
    <div class="login-box" style="left:75%!important; padding: 50px;">
        <h1 style="text-align: center">Courses</h1>
        <table border="1" cellpadding="5" align="center" width="40%" style="background-color: aquamarine">
            <tr>
                <th>Course Name</th>
                <th>Enrollment</th>
            </tr>
            <%
                try{
                    ResultSet result = DBUtils.query("SELECT name FROM courses");
                    if(result != null) {
                        while(result.next()) {
            %>
                <form action="student_course" method="POST" class="login-form">
                    <tr>
                        <td><%=result.getString("name")%>
                            <input type='hidden' name='course' value='<%=result.getString("name")%>' />
                        </td>
                        <td>
                            <button type="submit">enroll course</button>
                        </td>
                    </tr>
                </form>

            <%} } } catch(SQLException e){
                System.out.println("exception: " + e.getMessage());
                    e.printStackTrace();
                }
            %>
        </table>
    </div>
</body>

</html>
