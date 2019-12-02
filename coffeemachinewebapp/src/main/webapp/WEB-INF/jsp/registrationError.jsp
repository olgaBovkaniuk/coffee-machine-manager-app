<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<main role="main" class="container-fluid">
    <div class="alert alert-danger" role="alert">
        You have not been registered!
        Something went wrong!
        Please, <a style="color: #28a745" href="${pageContext.request.contextPath}/registration">try again</a>.
    </div>
    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/coffee-machine/welcome" role="button">Or continue as a guest</a>
</main>
<jsp:include page="footer.jsp"/>