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
<body class="first">
<div class="first">
    <center>
        <h1>WELCOME &nbsp;<b>${Login}</b></h1>
        <br/>
        <c:if test="${not empty sessionScope.authenticated}">


            DO YOU READY START GAME?<br/>
            <br/>

            <c:choose>
                <c:when test="${deck > '0'}">
                    <table width="80%" align="center">
                        <tr>


                                <form action=${pathWaitBattle} method='GET'>
                                    <input type='submit' class='buttonEnabled' value='START GAME'/>
                                </form>

                        </tr>
                    </table>
                </c:when>
            </c:choose>








            <c:choose>
                <c:when test="${deck=='0'}">
                    CREATE YOUR DECK
                    <form  method='GET'>
                        <input type='submit' class='buttonEnabled' name='deck' value='Create DECK'/>
                    </form>

                </c:when>
            </c:choose>
            <br/>
            <br/>

            <table width="80%" align="center">
                <th>
                    <center>
                        <img src="${pathHead}/adImage/imageDisplay?id=${idUser}" width="240" height="280"/>
                    </center>
                </th>
                <b>
                    <th align="left">
                        <b>YOUR GOLD &nbsp ${gold} <br/>

                            YOUR LEVEL &nbsp; ${lvl} <br/>
                            <b>YOUR POINTS &nbsp; ${point} <br/>
                                <b>YOUR DATE REGISTRATION &nbsp; ${dateReg} <br/>
                             Local time &nbsp;   ${dateNow}</b>
                        <br/><br/>
                        <form action=${pathEditUser} method='GET'>
                            <input type='submit' id='bold4' class='buttonEnabled' value='Edit Account'/>
                        </form>
                        <br/><br/>
                        <form action=${pathMain} method='GET'>
                            <input type='submit' name='logoff' value='Log off'/>
                        </form>
                        <br/>


                    <c:choose>
                    <c:when test="${deck > '0'}">

                    <br/>

                                <form  method='GET'>
                                    <input type='submit' class='buttonEnabled' name='deck' value='CHANGE DECK'/>
                                </form>



                    </c:when>
                    </c:choose>

                    </th>
            </table>


            <br/>


        </c:if>


    </center>


    <c:if test="${empty sessionScope.authenticated}">
        <table width="80%" align="center">
            <tr>

                <th>
                    <form action=${pathAuth} method='GET'>
                        <input type='submit' id='bold2' class='buttonEnabled' value='Login'/>
                    </form>
                </th>
                <th>
                    <form action=${pathReg} method='GET'>
                        <input type='submit' id='bold1' class='buttonEnabled' value='Register'/>
                    </form>
                </th>
            </tr>
        </table>
    </c:if>


    <c:choose>
        <c:when test="${yesOnlineUser > '0'}">
            <table width="10%" align="center">
                <tr>
                    <th>ONLINE</th>
                </tr>
                <tr>
                    <th>${yesOnlineUser}</th>
                </tr>
            </table>
        </c:when>
    </c:choose>
</div>
<div class="first">
</div>
</body>
</html>