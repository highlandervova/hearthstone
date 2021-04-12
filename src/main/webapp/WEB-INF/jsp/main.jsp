<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>


        <title>${title}</title>
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
    <style> <%@include file="/WEB-INF/jsp/css/style.css"%>
     </style>

</head>
<body class ="first">
    <div class = "first">
  <center>
    <h1>WELCOME &nbsp;<b>${Login}</b> </h1>
    <br/>
    <c:if test="${not empty sessionScope.authenticated}">


        DO YOU READY START GAME?<br/>
<br/>
        <c:choose>
            <c:when test="${deck=='0'}">
                CREATE YOUR DECK

                <form action=${pathDeck} method='GET' >
                    <input type='submit'    class='buttonEnabled' name='createDeck' value='Create DECK'/>
                </form>

            </c:when>
        </c:choose>
        <br/>
        <br/>

            <table width="80%"  align="center">
                <th><center>
                    <img src="${pathHead}/adImage/imageDisplay?id=${idUser}" width="240" height="280"/>
                </center></th><b>
                    <th align="left">
                        <b>YOUR GOLD &nbsp ${gold} <br/>
                    YOUR LEVEL &nbsp ${lvl} <br/>${dateNow}</b>
                    <br/><br/>
                        <form action=${pathEditUser} method='GET' >
                            <input type='submit' id='bold4'   class='buttonEnabled' value='Edit Account'/>
                        </form>
                    <br/><br/>
                        <form action=${pathMain} method='GET' >
                            <input type='submit'   name='logoff' value='Log off'/>
                        </form>
                        <br/>
                    </th>


            </table>


        <br/>












    <c:choose>
        <c:when test="${deck > '0'}">
        <table width="80%"  align="center">
            <tr>

            <th><form action=${start} method='GET' >
                        <input type='submit'    class='buttonEnabled' value='START GAME'/>
                    </form>
                    </th>
                    <th><form action=${pathDeck} method='GET' >
                        <input type='submit'    class='buttonEnabled' name='' value='CHANGE DECK'/>
                    </form>
                    </th>

                </tr>
            </table>
        </c:when>
    </c:choose>

        </c:if>



 </center>




<c:if test="${empty sessionScope.authenticated}">
    <table width="80%"  align="center">
        <tr>

            <th><form action=${pathAuth} method='GET' >
                <input type='submit' id='bold2'   class='buttonEnabled' value='Login'/>
            </form>
            </th>
            <th><form action=${pathReg} method='GET' >
                <input type='submit' id='bold1'   class='buttonEnabled' value='Register'/>
            </form>
            </th>
        </tr>
    </table>
</c:if>



        <c:choose>
        <c:when test="${yesOnlineUser > '0'}">
        <table width="80%"  align="left">
            <tr><b>User's wating for you </b><br/></tr>
            <tr><th>ONLINE</th><th>NAME</th>
                <th>GOLD</th><th>LEVEL</th>
                <th>LOCAL TIME</th>
            </tr>
<c:forEach items="${userOnline}" var="usOnline">
    <tr><th>YES
    </th><th>
    ${usOnline.login}<br/>
    <img src="${pathHead}/adImage/imageDisplay?id=${usOnline.id}" width="100" height="120"/><br/></th>
    <th>${usOnline.gold}<br/></th>
        <th>${usOnline.lvl}<br/></th><th>${dateNow}</th></tr>
      <br/>
</c:forEach>
        </table>
        </c:when>
        </c:choose>
</div>
<div class = "first">
</div>
</body>
</html>