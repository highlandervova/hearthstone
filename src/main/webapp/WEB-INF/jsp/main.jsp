<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <style type='text/css'>
        body {background: #b0e0e6 url(fon.png) top;  }
</style>
        <title>${title}</title>
</head>
<body>
<center>
    <h1>WELCOME &nbsp;<b>${Login}</b> </h1>
    <br/>
    <c:if test="${not empty sessionScope.authenticated}">
        DO YOU READY START GAME?



                 <c:choose>
                <c:when test="${deck=='0'}">
                CREATE YOUR DECK

                <form action=${pathDeck} method='GET' >
                    <input type='submit'    class='buttonEnabled' name='logoff' value='Create DECK'/>
                </form>

            </c:when>
                 </c:choose>









    <c:choose>
        <c:when test="${deck > '0'}">
        <table width="80%"  align="center">
            <tr>

            <th><form action=${start} method='GET' >
                        <input type='submit'    class='buttonEnabled' value='START GAME'/>
                    </form>
                    </th>
                    <th><form action=${pathDeck} method='GET' >
                        <input type='submit'    class='buttonEnabled' name='logoff' value='CHANGE DECK'/>
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



<c:if test="${not empty sessionScope.authenticated}">
    <table width="80%"  align="center">
<tr><center>
            <img src="${pathHead}/adImage/imageDisplay?id=${idUser}" width="240" height="280"/>
</center>

        </tr>


        <tr>


            <th><form action=${pathEditUser} method='GET' >
                <input type='submit' id='bold4'   class='buttonEnabled' value='Edit Account'/>
            </form>
            </th>
            <th><form action=${pathMain} method='GET' >
                <input type='submit'   name='logoff' value='Log off'/>
            </form>
            </th>

        </tr>
    </table>
</c:if>


</body>
</html>