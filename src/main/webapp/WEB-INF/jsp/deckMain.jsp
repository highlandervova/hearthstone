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
<br/>
<h2>CREATE YOURS DECK</h2>

<c:choose>
    <c:when test="${mightSave< '10' }">

        1<br/>
        <b>Click mouse on cards for choosing</b>
        <br/>
        <table width="80%" align="center">
            Now you have ${mightSave} cards

<br/>


            <c:forEach items="${cardForDeck}" var="cardFor">


                <td>
                    <br/>

    <a href="${pathMainDeck}?id=${cardFor.id}">
    <img src="${pathHead}/adImage/imageDisplay?id=${cardFor.id}" width="240" height="300" />
    </a>
                </td>

            </c:forEach>


        </table>


    </c:when>
</c:choose>

<c:choose>
    <c:when test="${mightSave > 9}">
        Now you have   ${mightSave} cards<br/>
        <p>REMOVE CARD FROM YOURS DECK AND CHOOSE MORE<p>
        <c:forEach items="${cardForDeck}" var="cardFor">
            <td>


                    <img src="${pathHead}/adImage/imageDisplay?id=${cardFor.id}" width="240" height="300" />

            </td>

        </c:forEach>


        </table>


    </c:when>
</c:choose>

</body>
</html>
