
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="form-style-2-heading">
<h2>Already registered!</h2>
</div>


<div class="form-style-2">
    <form method="post" action="/users">
        <label for="firstName">First Name
            <input class="input-field" type="text" id="firstName" name="firstName">
        </label>
        <label for="lastName">Last Name
            <input class="input-field" type="text" id="lastName" name="lastName">
        </label>
        <input name="submit" type="submit" value="Add User">

    </form>
</div>


<div class="form-style-2">
<table class="select-field" border="2", title="table", about="table just some table">
    <tr>l
        <th>Id</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Age</th>
    </tr>
    <c:forEach  items="${usersFromServer}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
                <%--        <td>dfgsdghdgggdg</td>--%>
        </tr>
    </c:forEach>
</table>
</div>

</body>
</html>
