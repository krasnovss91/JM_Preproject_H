<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD-App</title>
</head>
<body>

<div align="center">

    <table bordercolor="red" border="1" cellpadding="4" cellspacing="0">
        <caption>
            <h2>Users</h2>
        </caption>
        <br/><br/>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Login</th>
            <th>Password</th>
            <th>Change the record - Update</th>
            <th>Change the record - Delete</th>
        </tr>

        <jsp:useBean id="allUsers" scope="request" type="java.util.List"/>

        <c:forEach var="user" items="${allUsers}">
            <tr>
                <td align="center" size="200"><c:out value="${user.id}"/></td>
                <td align="center" size="30"><c:out value="${user.name}"/></td>
                <td align="center" size="30"><c:out value="${user.login}"/></td>
                <td align="center" size="30"><c:out value="${user.password}"/></td>
                <td>
                    <a href="/jsp_hibernate_project_war/update?id=<c:out value='${user.id}' />">Update this User</a>
                </td>
                <td>
                    <form method="POST" action="/jsp_hibernate_project_war/delete?id=<c:out value='${user.id}' />">
                        <input type="submit" value="Delete this User"/>
                    </form>
                </td>

            </tr>
        </c:forEach>
    </table>
    <hr>

    <br/><br/>
    <hr>

    <div class="form-style-2">
        <div class="form-style-2-heading">
            Please input New User info!
        </div>
        <form method="post" action="/jsp_hibernate_project_war/create">


            <label for="name">User name
                <input class="input-field" type="text" required="required" id="name" name="name">
            </label>
            <label for="login">Login
                <input class="input-field" type="text" required="required" id="login" name="login">
            </label>
            <label for="password">Password
                <input class="input-field" type="password" required="required" id="password" name="password">
            </label>
            <input type="submit" value="Create New User">
        </form>

    </div>


    &nbsp;
</div>

</body>
</html>
