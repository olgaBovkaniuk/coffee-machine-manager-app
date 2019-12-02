<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>

<main role="main" class="container-fluid">
    <h4>Please, register, if you don't have account, or <a style="color: #28a745" href="${pageContext.request.contextPath}/login">login</a></h4>
    <form:form modelAttribute="user" method="POST" action="${pageContext.request.contextPath}/registration">
        <div class="form-group">
            <label for="firstName">First name</label>
            <form:input path="firstName" type="text" name="firstName" class="form-control" id="firstName" placeholder="Enter first name"/>
            <small class="form-text text-muted">Please enter you first name</small>
            <form:errors path="firstName" cssStyle="color: brown"/>
        </div>
        <div class="form-group">
            <label for="lastName">Last name</label>
            <form:input path="lastName" type="text" name="lastName" class="form-control" id="lastName" placeholder="Enter last name"/>
            <small class="form-text text-muted">Please enter you last name</small>
            <form:errors path="lastName" cssStyle="color: brown"/>
        </div>
        <div class="form-group">
            <label for="email">Your email</label>
            <form:input path="email" type="email" name="email" class="form-control" id="email" placeholder="Enter your email"/>
            <small class="form-text text-muted">Your email will be used for login</small>
            <form:errors path="email" cssStyle="color: brown"/>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <form:input path="password" type="password" name="password" class="form-control" id="password" placeholder="Your password"/>
            <form:errors path="password" cssStyle="color: brown"/>
        </div>
        <button type="submit" class="btn btn-secondary">Submit</button>
    </form:form>
</main>
<jsp:include page="footer.jsp"/>