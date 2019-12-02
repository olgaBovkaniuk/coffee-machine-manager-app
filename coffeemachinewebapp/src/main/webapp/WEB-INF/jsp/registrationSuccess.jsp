<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<main role="main" class="container-fluid">
    <div class="alert alert-success" role="alert">
        You have been registered successfully! Please,
        <a style="color: #28a745" href="${pageContext.request.contextPath}/login">login</a>.
    </div>
    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/coffee-machine/welcome" role="button">Or continue as a guest</a>
</main>
<jsp:include page="footer.jsp"/>