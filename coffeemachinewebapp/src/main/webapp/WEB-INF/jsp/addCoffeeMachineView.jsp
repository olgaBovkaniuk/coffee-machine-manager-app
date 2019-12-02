<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="header.jsp"/>

<main role="main" class="container-fluid">
    <h1>Add new coffee machine</h1>
    <form:form modelAttribute="item" action="${pageContext.request.contextPath}/coffee-machine/add" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="coffeeMachineNameUniqNumber" class="col-sm-2 col-form-label">Coffee Machine Uniq Number</label>
            <form:input path="coffeeMachineId" type="text" name="coffeeMachineId" class="form-control" id="coffeeMachineNameUniqNumber"
                   placeholder="Enter coffee machine uniq id"/>
            <form:errors path="coffeeMachineId" cssStyle="color: brown"/>
        </div>
        <div class="form-group">
            <label for="coffeeMachineName" class="col-sm-2 col-form-label">Coffee Machine name</label>
            <form:input path="coffeeMachineName" type="text" name="coffeeMachineName" class="form-control" id="coffeeMachineName"
                   placeholder="Enter coffee machine name"/>
            <form:errors path="coffeeMachineName" cssStyle="color: brown"/>
        </div>
        <div class="form-group">
            <label for="file">Upload file</label>
            <input type="file" name="file" class="form-control-file" id="file">
        </div>
        <button type="submit" class="btn btn-secondary">Submit</button>
    </form:form>
</main>
<jsp:include page="footer.jsp"/>