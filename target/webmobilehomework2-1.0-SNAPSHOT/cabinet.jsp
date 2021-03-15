<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<sql:setDataSource var="db" driver="org.postgresql.Driver"
                   url="jdbc:postgresql://localhost:5432/testdb"
                   user="air"  password=""/>

<sql:query dataSource="${db}" var="rs">
    SELECT * from courses;
</sql:query>
<sql:query dataSource="${db}" var="rr">
    SELECT * FROM users WHERE email = '<c:out value="${sessionScope['email']}" />'
</sql:query>


<!DOCTYPE html>
<html>
<head>
    <title>WebMobile 2 - Homework 2</title>
    <link rel="stylesheet" href="main.css">
</head>
<body>
    <div class="login-box" style="left: 25%!important;">
    <h2>User data</h2>
    <form action="profile" method="post">
        <div class="user-box">
            <input type="text" name="firstname" id="firstname" required="required" value="<c:out value="${rr.rows[0].firstname}" />">
            <label for="firstname">Firstname</label>
        </div>
        <div class="user-box">
            <input type="text" name="lastname" id="lastname" required="required" value="<c:out value="${rr.rows[0].lastname}" />">
            <label for="lastname">Lastname</label>
        </div>
        <div class="user-box">
            <input type="text" name="phone" id="phone" required="required" value="<c:out value="${rr.rows[0].phone}" />">
            <label for="phone">Phone</label>
        </div>
        <div class="user-box">
            <input type="text" name="age" id="age" required="required" value="<c:out value="${rr.rows[0].age}" />">
            <label for="age">Age</label>
        </div>
        <div class="user-box">
            <input type="text" id="course" disabled readonly value="<c:out value="${rr.rows[0].course_name}" />">
        </div>
        <div class="user-box">
            <input type="text" name="gender" id="gender" required="required" value="<c:out value="${rr.rows[0].gender}" />">
            <label for="gender">Gender</label>
        </div>
        <a href="#" onclick="this.closest('form').submit();return false;">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            Save
        </a>
    </form>
</div>
    <div class="login-box" style="left:75%!important;">
        <h2>Selectable courses</h2>
        <table border="2" width="100%">
            <tr style="background-color:#38668e">
                <th>Course ID</th>
                <th>Course Name</th>
                <td>Enrol!</td>
            </tr>
                <c:forEach var="table" items="${rs.rows}">
                    <form action="courses" method="post">
                        <tr style="background-color:#38668e">
                            <td><c:out value="${table.id}"/></td>
                            <td><c:out value="${table.name}"/></td>
                            <input hidden type="text" name="course_name" id="course_name" value="<c:out value="${table.name}"/>">
                            <td>
                                <input type="submit" value="Enrollment">
                            </td>
                        </tr>
                    </form>
                </c:forEach>
        </table>
    </div>
</body>

</html>
