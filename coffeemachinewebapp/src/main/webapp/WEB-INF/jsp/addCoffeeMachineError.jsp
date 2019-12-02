<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<main role="main" class="container-fluid">
<div class="alert alert-danger" role="alert">
    Coffee Machine has not been added!
     Something went wrong!
</div>
<a class="btn btn-secondary" href="${pageContext.request.contextPath}/coffee-machine/add" role="button">Back to the form</a>
</main>
<jsp:include page="footer.jsp"/>