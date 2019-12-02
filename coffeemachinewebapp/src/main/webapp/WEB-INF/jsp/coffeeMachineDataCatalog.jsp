<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<main role="main" class="container-fluid">
<h1>Coffee Machine Data Catalog</h1>

<table class="table table-bordered table-hover">
    <thead class="thead-light">
    <tr>
        <th scope="col">#</th>
        <th scope="col">Coffee Machine Name</th>
        <th scope="col">Water, ml</th>
        <th scope="col">Milk, ml</th>
        <th scope="col">Coffee beans, gr</th>
        <th scope="col">Used coffee container, portion</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="coffeeMachine" items="${catalog}">
        <tr>
            <th scope="row">${coffeeMachine.id}</th>
            <td>
                <a href="${pageContext.request.contextPath}/coffee-machine-data/coffee-machine/${coffeeMachine.coffeeMachineId}"
                   class="badge badge-light">${coffeeMachine.coffeeMachineName}</a></td>
            <td>${coffeeMachineData.waterMl}</td>
            <td>${coffeeMachineData.milkMl}</td>
            <td>${coffeeMachineData.coffeeBeansGr}</td>
            <td>${coffeeMachineData.usedCoffeeContainerPortion}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</main>
<jsp:include page="footer.jsp"/>