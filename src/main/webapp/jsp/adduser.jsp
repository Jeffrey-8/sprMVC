<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
    <div class="form-style-2-heading"><label style="backface-visibility: visible"> Please Sign Up! </label></div>
</head>
<body>
<form method="post" action="/users">
    <label>User name </label>
    <input type="text" value="Roman" name="username">
    <br>
    <label>Password </label>
    <input type="text" value="qwerty1" name="password">
    <input type="submit" value="Add user">
</form>
</body>
</html>