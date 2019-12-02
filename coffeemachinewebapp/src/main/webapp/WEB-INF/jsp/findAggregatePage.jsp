<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="header.jsp"/>

<main role="main" class="container-fluid">
    <h1>Find all events for the aggregate</h1>
    <form action="${pageContext.request.contextPath}/aggregate/events" method="get">
        <div class="form-group">
            <label for="aggregateId" class="col-sm-2 col-form-label">Aggregate UUID</label>
            <input type="text" name="aggregateId" class="form-control" id="aggregateId"
                   placeholder="Enter aggregate UUID"/>
<%--            <form:errors path="aggregateUUID" cssStyle="color: brown"/>--%>
        </div>
        <button type="submit" class="btn btn-secondary">Find</button>
    </form>
</main>
<jsp:include page="footer.jsp"/>