<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/x-icon">
    <title>Coffee Machine App</title>
    <%--    <script type="text/javascript">--%>
    <%--        function activePage(page) {--%>
    <%--            $('#' + page).addClass("active")--%>
    <%--        }--%>
    <%--    </script>--%>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/coffee-machine/welcome">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/coffee-machine/list">Coffee Machine
                    Catalog</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/coffee-machine/add">Add Coffee Machine</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/aggregate/find">Find all events for aggregate</a>
            </li>
        </ul>
        <div>
            <sec:authentication var="user" property="principal"/>
            <sec:authorize access="!isAuthenticated()">
                <div class="nav-item">
                    <a class="nav-link small" style="display: inline; color: #28a745"
                       href="${pageContext.request.contextPath}/registration">Registration</a>
                    <a class="nav-link small" style="display: inline; color: #28a745"
                       href="${pageContext.request.contextPath}/login">Login</a>
                </div>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <div class="nav-item">
                    <a class="nav-link small" style="display: inline; color: #28a745"
                       href="${pageContext.request.contextPath}/logout">Logout</a>
                    <p class="nav-link small" style="display: inline; color: #28a745" >Hello, ${user.username}</p>
                </div>
            </sec:authorize>
            <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/search">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"
                       name="search-str">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>