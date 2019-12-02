<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<main role="main" class="container-fluid">
<%--    <h3 class="card-title">${coffeeMachine.coffeeMachineName}</h3>--%>

    <div class="card" style="...">

        <div class="card-body">
            <p class="card-text">Aggregate type:</p>
            <h4 class="card-title">${aggregateStateDto.aggregateStateType}</h4>
            <p class="card-text">Last aggregate state:</p>
            <h6 class="card-title">${aggregateStateDto.aggregateStateData}</h6>
            <a href="${pageContext.request.contextPath}/aggregate/find" class="btn btn-secondary">Back to the
                find aggregate page</a>
        </div>
    </div>
    <h4>All aggregate's events:</h4>
    <table class="table table-bordered table-hover">
        <thead class="thead-light">
        <tr>
            <th scope="col">eventId</th>
            <th scope="col">eventData</th>
            <th scope="col">eventDate</th>
            <th scope="col">eventType</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="event" items="${aggregateEvents}">
            <tr>
                <th scope="row">${event.eventId}</th>
                <td>${event.eventData}</td>
                <td>${event.eventDate}</td>
                <td>${event.eventType}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
<jsp:include page="footer.jsp"/>