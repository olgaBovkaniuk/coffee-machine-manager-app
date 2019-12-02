<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/custom.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/x-icon">
    <title>Coffee machine login page</title>
</head>

<body class="customBody" data-gr-c-s-loaded="true">
<div class="wrapper fadeInDown">
    <div id="formContent">
        <div class="fadeIn first">
            <h5 style="padding-top: 10px;">Welcome to Coffee Machine Application</h5>
        </div>
        <!-- Login Form -->
        <form method="post" action="/login">
            <c:if test="${not empty error}">
                <div class="error" style="color: #b21f2d">${error}</div>
            </c:if>
            <label for="username"></label>
            <input type="text" id="username" class="fadeIn second" name="username" placeholder="Username" required=""
                   autofocus="">

            <label for="password"></label>
            <input type="password" id="password" class="fadeIn third" name="password" placeholder="Password"
                   required="">
            <button type="submit" class="fadeIn fourth" value="Log In">Log In</button>
        </form>
        <div id="formFooter">
            <a class="underlineHover" href="${pageContext.request.contextPath}/registration">Don't have an account?
                Please, register.</a>
        </div>

    </div>
</div>
</body>

<jsp:include page="footer.jsp"/>