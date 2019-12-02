<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<main role="main" class="container-fluid">
    <h3 class="card-title">${coffeeMachine.coffeeMachineName}</h3>
    <img src="${pageContext.request.contextPath}/files/coffee-machine/${coffeeMachine.coffeeMachineId}?size=LARGE"
         alt="..."/>
    <div class="card" style="...">

        <div class="card-body">
            <p class="card-text">Coffee Machine Id:</p>
            <h4 class="card-title">${coffeeMachine.coffeeMachineId}</h4>
            <a href="${pageContext.request.contextPath}/coffee-machine/list" class="btn btn-secondary">Back to the
                Catalog</a>
        </div>
    </div>
    <h4>Coffee Machine data:</h4>
    <table class="table table-bordered table-hover">
        <thead class="thead-light">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Water, ml</th>
            <th scope="col">Milk, ml</th>
            <th scope="col">Coffee beans, gr</th>
            <th scope="col">Used coffee container, portion</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="coffeeMachineData" items="${catalog}">
            <tr>
                <th scope="row">${coffeeMachineData.id}</th>
                <td>${coffeeMachineData.waterMl}</td>
                <td>${coffeeMachineData.milkMl}</td>
                <td>${coffeeMachineData.coffeeBeansGr}</td>
                <td>${coffeeMachineData.usedCoffeeContainerPortion}</td>
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
                               href="<c:url value="${pageContext.request.contextPath}/coffee-machine/${coffeeMachine.coffeeMachineId}/list">
                            <c:param name="page" value="${page}"/>${page}</c:url>">
                                    ${page}
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${currentPage != page}">
                        <li class="page-item" id="${page}">
                            <a class="page-link"
                               href="<c:url value="${pageContext.request.contextPath}/coffee-machine/${coffeeMachine.coffeeMachineId}/list">
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