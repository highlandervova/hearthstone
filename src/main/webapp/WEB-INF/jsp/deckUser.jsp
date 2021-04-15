<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Refresh" content="4"/>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
    <style> <%@include file="/WEB-INF/jsp/css/style.css"%>
    </style>

</head>
<body class="first">
<br/>
<h2>YOURS DECK</h2>
<br/>

<b>Click mouse on cards to remove from yours deck</b>
<br/>
<b><p>YOU HAVE ${mightSave} CARDS </p></b>
<table width="80%" align="center">


    <c:forEach items="${cardForDeck}" var="cardFor">
        <tr>

            <a href="${pathUserDeck}?id=${cardFor.id}">
                <img src="${pathHead}/adImage/imageDisplay?id=${cardFor.id}" width="240" height="300"/>
            </a>


        </tr>

    </c:forEach>


</table>


</body>
</html>
