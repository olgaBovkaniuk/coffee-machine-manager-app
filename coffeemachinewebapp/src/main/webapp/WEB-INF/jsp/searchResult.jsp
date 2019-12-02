<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<main role="main" class="container-fluid">
    <h1>Search result</h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Event number</th>
            <th scope="col">Image</th>
            <th scope="col">Coffee Machine Id</th>
            <th scope="col">Coffee Machine Name</th>
            <th scope="col">Status</th>
            <sec:authentication var="user" property="principal"/>
            <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                <th scope="col">Operation</th>
                <th scope="col">Delete</th>
            </sec:authorize>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="coffeeMachine" items="${result}">
            <tr>
                <th scope="row">${coffeeMachine.eventId}</th>
                <td>
                    <img src="${pageContext.request.contextPath}/files/coffee-machine/${coffeeMachine.coffeeMachineId}?size=SMALL"
                         alt="...">
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/coffee-machine/${coffeeMachine.coffeeMachineId}"
                       class="badge badge-light">${coffeeMachine.coffeeMachineId}</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/coffee-machine/${coffeeMachine.coffeeMachineId}"
                       class="badge badge-light">${coffeeMachine.coffeeMachineName}</a>
                </td>
                <td>${coffeeMachine.status}</td>
                <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                    <c:if test="${coffeeMachine.status == 'OFF'}">
                        <td>
                            <form id="submitForm"
                                  action="${pageContext.request.contextPath}/coffee-machine/${coffeeMachine.coffeeMachineId}/start"
                                  method="GET">
                                <input id="${coffeeMachine.coffeeMachineId}"
                                       class="btn btn-success" type="submit" value="START"/>
                            </form>
                        </td>
                    </c:if>
                    <c:if test="${coffeeMachine.status == 'ON'}">
                        <td>
                            <form id="submitForm1"
                                  action="${pageContext.request.contextPath}/coffee-machine/${coffeeMachine.coffeeMachineId}/stop"
                                  method="GET">
                                <input id="${coffeeMachine.coffeeMachineId}"
                                       class="btn btn-danger" type="submit" value="STOP"/>
                            </form>
                        </td>
                    </c:if>

                    <c:if test="${coffeeMachine.status == 'OFF'}">
                        <td>
                            <form id="submitForm2"
                                  action="${pageContext.request.contextPath}/coffee-machine/${coffeeMachine.coffeeMachineId}/delete"
                                  method="GET">
                                <input id="${coffeeMachine.coffeeMachineId}"
                                       class="btn btn-danger" type="submit" value="DELETE"/>
                            </form>
                        </td>
                    </c:if>
                    <c:if test="${coffeeMachine.status == 'ON'}">
                        <td>
                            Please, STOP <br/> the Coffee Machine <br/> before deleting!
                        </td>
                    </c:if>
                </sec:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/coffee-machine/list" class="btn btn-secondary">Back to the
        Catalog</a>
</main>
<jsp:include page="footer.jsp"/>