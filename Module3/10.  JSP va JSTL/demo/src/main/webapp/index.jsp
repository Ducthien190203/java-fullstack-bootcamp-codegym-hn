<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${not empty param.language}">
    <c:set var="language" value="${param.language}" scope="session"/>
</c:if>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="org.o7planning.bundles.MyBundle" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fmt:bundle example</title>
</head>
<body>
<h2>fmt:bundle example</h2>
<form action="">
    <table border="0">
        <tr>
            <td>
                <fmt:message key="login.label.userName"/>
            </td>
            <td>
                <input type="text" name="userName" />
            </td>
        </tr>
        <tr>
            <td>
                <fmt:message key="login.label.password"/>
            </td>
            <td><input type="text" name="userName" /></td>
        </tr>
    </table>
    <input type="submit"  value="Submit"/>
</form>
</body>
</html>