<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update User Page</title>
</head>
<body>

<div align="center">
    <form method="POST" action="/jsp_hibernate_project_war/update">
        <table bordercolor="red" border="1" cellpadding="4" cellspacing="0">
            <caption>
                <h2>Update page</h2>
            </caption>

            <input type="hidden" name="id" value="<c:out value='${userToUpdate.id}' />"/>

            <br/><br/>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Login</th>
                <th>Password</th>
                <th>Confirmation - Update the record</th>
            </tr>

            <jsp:useBean id="userToUpdate" scope="request" type="model.User"/>

            <tr>
                <td align="center" size="20"><p>Текущее значение:</p><h5><c:out value="${userToUpdate.id}"/></h5>
                    <p>Здесь данные не менять!</p>
                </td>
                <td align="center" size="30"><p>Текущее значение:</p><h5><c:out value="${userToUpdate.name}"/></h5>
                    <p>Новое значение</p>
                    <input type="text" name="name" required="required"
                           value="<c:out value='${userToUpdate.name}'/>"/></td>
                <td align="center" size="30"><p>Текущее значение:</p><h5><c:out value="${userToUpdate.login}"/></h5>
                    <p>Новое значение</p>
                    <input type="text" name="login" required="required"
                           value="<c:out value='${userToUpdate.login}'/>"/></td>
                <td align="center" size="30"><p>Текущее значение:</p><h5><c:out value="${userToUpdate.password}"/></h5>
                    <p>Новое значение</p>
                    <input
                            type="text" name="password" required="required"
                            value="<c:out value='${userToUpdate.password}'/>"/></td>

                <td>
                    <input type="submit" value="Update this User"/>
                </td>

            </tr>

        </table>
    </form>
    <hr>

    <br/><br/>
    <hr>

    <form action="/jsp_hibernate_project_war/read">
        <input type="submit" value="Read User's List"/>
    </form>

</div>

</body>
</html>
