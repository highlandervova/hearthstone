и  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <title>Title</title>
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
    <style> <%@include file="/WEB-INF/jsp/css/style.css"%>
    </style>

</head>
<body class="first">
<br/>
<h2>CREATE YOURS DECK</h2>


<table width="20%" align="center">

    <th>
        <c:choose>
            <c:when test="${mightSave  == 10 }">

                <form  method='GET'>
                    <input type='submit' class='buttonEnabled' name='id' value='SaveDeck'/>
                </form>

            </c:when>
        </c:choose>
    </th>
    <th>
        <form action=${pathMain} method='GET' >
            <input type='submit' value='Main Page'/>
        </form>
    </th>
    <th>
        <form action=${pathMain} method='GET'>
            <input type='submit' value='Cancel'/>
        </form>
    </th>
</table>

<c:choose>
    <c:when test="${mightSave< '10' }">
        <br/>
        <b>Click mouse on cards for choosing</b>
        <br/>
        Now you have ${mightSave} cards

        <table border:none>
            <th>
                Yours deck
            </th>
            <th> Main deck </th>
            <tr border:none>
                <td width:600px; height: 650px; border:none>

                    <div style="width:600px; height: 650px; overflow:auto; border:none">
                        <table cellspacing="0" cellpadding="0" border:none  width="300px">




                            <c:forEach items="${cardForUs}" var="cardForUser">
                            <tr>

                                <a href="${pathMainDeck}?id=${cardForUser.id}us">
                                    <img src="${pathHead}/adImage/imageDisplay?id=${cardForUser.id}" width="240"
                                         height="300"/>
                                </a>

                            <tr>

                        </c:forEach>


                        </table>
                    </div>

                </td>

                <td width:1200px; word-wrap: break-word; border:none>
                    <div style="width:1200px; height: 650px; word-wrap: break-word; overflow:auto; border:none">
<%--                    <div style="width:600px; height:90%; overflow:auto; border:none">--%>
                        <table cellspacing="0" cellpadding="0" border:none width="300">
                            <tr border:none>


                                       <c:forEach items="${cardForDeck}" var="cardFor">





                                            <td>
                                        <a href="${pathMainDeck}?id=${cardFor.id}mn">
                                            <img src="${pathHead}/adImage/imageDisplay?id=${cardFor.id}" width="240"
                                                 height="300"/>
                                        </a>
                                    </td>





                                </c:forEach>
                            </tr>
                            <tr>

                        </table>
                    </div>

                </td>
            </tr>

        </table>
    </c:when>
</c:choose>




    <c:choose>
    <c:when test="${mightSave>9 }">
    <br/>
        <p>REMOVE CARD FROM YOURS DECK AND CHOOSE MORE<p>
    <br/>
    Now you have ${mightSave} cards

<table border:none>
    <th>
        Yours deck
    </th>
    <th> Main deck </th>
    <tr border:none>
        <td width:600px; height: 700px; border:none>

            <div style="width:600px; height: 700px; overflow:auto; border:none">
                <table cellspacing="0" cellpadding="0" border:none hidden=600px; width="300px">




                    <c:forEach items="${cardForUs}" var="cardForUser">
                    <tr>

                        <a href="${pathMainDeck}?id=${cardForUser.id}us">
                            <img src="${pathHead}/adImage/imageDisplay?id=${cardForUser.id}" width="240"
                                 height="300"/>
                        </a>

                    <tr>

                        </c:forEach>


                </table>
            </div>

        </td>

        <td width:1200px; word-wrap: break-word; border:none>
            <div style="width:1200px; height: 700px; word-wrap: break-word; overflow:auto; border:none">
                    <%--                    <div style="width:600px; height:90%; overflow:auto; border:none">--%>
                <table cellspacing="0" cellpadding="0" border:none width="300">
                    <tr border:none>


                        <c:forEach items="${cardForDeck}" var="cardFor">






                            <td>

                                    <img src="${pathHead}/adImage/imageDisplay?id=${cardFor.id}" width="240"
                                         height="300"/>

                            </td>





                        </c:forEach>
                    </tr>
                    <tr>

                </table>
            </div>

        </td>
    </tr>

</table>
</c:when>
</c:choose>

<br/>
<br/>


</body>
</html>
