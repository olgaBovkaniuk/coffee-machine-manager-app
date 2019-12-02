<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>

<main role="main" class="container-fluid">
    <h1>Coffee Machine Catalog</h1>

    <table class="table table-bordered table-hover">
        <thead class="thead-light">
        <tr>
            <th scope="col">Event number</th>
            <th scope="col">Image</th>
            <th scope="col">Coffee Machine Id</th>
            <th scope="col">Coffee Machine Name</th>
            <%--            <c:if test=""--%>
            <th scope="col">Status</th>
            <sec:authentication var="user" property="principal"/>
            <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                <th scope="col">Operation</th>
                <th scope="col">Delete</th>
            </sec:authorize>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="coffeeMachine" items="${catalog}">
            <tr>
                <th scope="row">${coffeeMachine.order}</th>
                <td>
                    <img class="img-thumbnail" src="${pageContext.request.contextPath}/files/coffee-machine/${coffeeMachine.coffeeMachineId}?size=SMALL"
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
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">

            <c:if test="${pagesCount != 0}">
                <c:forEach begin="0" end="${pagesCount}" var="page">
                    <c:if test="${currentPage == page}">
                        <li class="page-item active" id="${page}">
                            <a class="page-link"
                               href="<c:url value="${pageContext.request.contextPath}/coffee-machine/list">
                            <c:param name="page" value="${page}"/>${page}</c:url>">
                                    ${page}
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${currentPage != page}">
                        <li class="page-item" id="${page}">
                            <a class="page-link"
                               href="<c:url value="${pageContext.request.contextPath}/coffee-machine/list">
                            <c:param name="page" value="${page}"/>${page}</c:url>">
                                    ${page}
                            </a>
                        </li>
                    </c:if>
                </c:forEach>
            </c:if>

        </ul>
    </nav>
</main>
<jsp:include page="footer.jsp"/>