<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
        <meta http-equiv="Refresh" content="5"/>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
    <style> <%@include file="/WEB-INF/jsp/css/style.css"%>
    </style>
</head>
<body class="first">
<table width="80%" align="center">
    <th>
        <c:choose>
            <c:when test="${mightSave  == 10 }">
                <form  method='GET'  target="_top">
                    <input type='submit' name='savedeck' value='Save Deck and Main Page'/>
                </form>

            </c:when>
        </c:choose>
    </th>
    <th>
        <form action=${pathMain} method='GET' target="_top">
            <input type='submit' value='Main Page'/>
        </form>
    </th>
    <th>
        <form action=${pathMain} method='GET' target="_top">
            <input type='submit' value='Cancel'/>
        </form>
    </th>
</table>

</body>
</html>
