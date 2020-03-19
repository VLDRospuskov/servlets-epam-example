<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<script>/js/script.js</script>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>User Info</title>
    </head>
    <body>
        <ul>
            <c:forEach var="customer" items="${listCustomers}">
                <li><c:out value="${customer.id}" /> <c:out value="${customer.firstName}" /></li>
            </c:forEach>
        </ul>
    </body>
</html>
