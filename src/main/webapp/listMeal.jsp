<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h2>Ваша еда</h2>
<table>
    <thead>
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="listMealTo" scope="request" type="java.util.List"/>
    <c:forEach items="${listMealTo}" var="mealTo">
        <tr style="background-color:${mealTo.excess ? 'red' : 'greenyellow'}">
            <td>
                <fmt:parseDate value="${ mealTo.dateTime }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                               type="both"/>
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${ parsedDateTime }"/>
            </td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td><a href="MealServlet?action=edit&mealId=<c:out value="${mealTo.id}"/>">Update</a></td>
            <td><a href="MealServlet?action=delete&mealId=<c:out value="${mealTo.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="MealServlet?action=insert">Добавить еду</a></p>
<h3><a href="index.html">Home</a></h3>
</body>
</html>
