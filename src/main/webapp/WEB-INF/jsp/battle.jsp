
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <style type='text/css'>

        #bold0 {
            font-weight: bold;
        }

        body {
            background: #b0e0e6;
        }


    </style>
    <meta http-equiv="Refresh" content="5"/>

    <br/>
    <center>

        <b>OPPONENT'S DECK</b>
        <br/></center>
</head>
<body>
<br/>
<c:choose>
    <c:when test="${whoIs==1}">
        <table width="1000px" height="180">
            <tr width="1000px" height="180px">
                <td width="400px" height="100px">


                    <img src="${pathHead}/adImage/imageDisplay?id=0" width="80" height="100px"/>

                    <br/>
                    <b>Deck:&nbsp; ${deckCardHero2}&nbsp; cards </b>
                </td>

                <td width="400px" height="110px">
                    <center>
                        <b>${idUser2Login}</b>
                        <c:choose>
                        <c:when test="${whoTurn==1}">

                        <a href="${pathBattle}?id=${idBattle}tgHero2">

                            </c:when>
                            </c:choose>

                            <img src="${pathHead}/adImage/imageDisplay?id=${idUser2}" width="90" height="110px"
                                 style="border-radius:100%"/>
                            <c:choose>
                            <c:when test="${whoTurn==1}">

                        </a>
                        </c:when>
                        </c:choose>

                        <br/>

                        <b>Attack:&nbsp; ${attackHero2}&nbsp; </b> <b>HP:&nbsp; ${hpHero2}&nbsp; </b>
                    </center>


                </td>

                <c:forEach items="${handCollectionHero2}" var="cardForHand2">


                    <td>

                        <img src="${pathHead}/adImage/imageDisplay?id=-2" width="140"
                             height="180"/>

                    </td>
                </c:forEach>


            </tr>
        </table>
        <table width="1000px" height="180">
            <tr width="1000px" height="180px">
                <td width="400px" height="100px">


                    <img src="${pathHead}/adImage/imageDisplay?id=-1" width="80" height="100px"
                         style="border-radius:100%"/>
                    <br/> <b>Mana:&nbsp; ${currentManaHero2}
                    /Total&nbsp; ${manaHero2} </b>
                </td>

                <c:forEach items="${tableCollectionHero2}" var="cardForTable2">


                    <td>

                        <c:choose>
                        <c:when test="${whoTurn==1}">

                        <a href="${pathBattle}?id=${idBattle}target2${cardForTable2.id}">

                            </c:when>
                            </c:choose>

                            <img src="${pathHead}/adImage/imageDisplay?id=${cardForTable2.id}" width="140"
                                 height="180"/><br/>

                            <c:choose>
                            <c:when test="${whoTurn==1}">

                        </a>
                        </c:when>
                        </c:choose>

                        <br/>
                        <b>Attack:&nbsp; ${cardForTable2.damage}&nbsp; </b> <b>HP:&nbsp; ${cardForTable2.hp}&nbsp; </b>
                    </td>
                </c:forEach>
            </tr>


        </table>


    </c:when>
</c:choose>

<c:choose>
    <c:when test="${whoIs==2}">
        <table width="1000px" height="180">
            <tr width="1000px" height="180px">
                <td width="400px" height="100px">


                    <img src="${pathHead}/adImage/imageDisplay?id=0" width="80" height="100px"/>

                    <br/>
                    <b>Deck:&nbsp; ${deckCardHero1}&nbsp; cards </b>
                </td>

                <td width="400px" height="110px">
                    <center>
                        <b>${idUser1Login}</b>

                        <c:choose>
                        <c:when test="${whoTurn==2}">

                        <a href="${pathBattle}?id=${idBattle}tgHero1">
                            </c:when>
                            </c:choose>


                            <img src="${pathHead}/adImage/imageDisplay?id=${idUser1}" width="90" height="110px"
                                 style="border-radius:100%"/>
                            <c:choose>
                            <c:when test="${whoTurn==2}">

                        </a>
                        </c:when>
                        </c:choose>

                        <br/>

                        <b>Attack:&nbsp; ${attackHero1}&nbsp; </b> <b>HP:&nbsp; ${hpHero1}&nbsp; </b>
                    </center>


                </td>

                <c:forEach items="${handCollectionHero1}" var="cardForHand1">


                    <td>

                        <img src="${pathHead}/adImage/imageDisplay?id=-2" width="140"
                             height="180"/>

                    </td>
                </c:forEach>


            </tr>
        </table>
        <table width="1000px" height="180">
            <tr width="1000px" height="180px">
                <td width="400px" height="100px">


                    <img src="${pathHead}/adImage/imageDisplay?id=-1" width="80" height="100px"
                         style="border-radius:100%"/>
                    <br/> <b>Mana:&nbsp; ${currentManaHero1}
                    /Total&nbsp; ${manaHero1} </b>
                </td>

                <c:forEach items="${tableCollectionHero1}" var="cardForTable1">


                    <td>

                        <c:choose>
                        <c:when test="${whoTurn==2}">

                        <a href="${pathBattle}?id=${idBattle}target1${cardForTable1.id}">
                            </c:when>
                            </c:choose>


                            <img src="${pathHead}/adImage/imageDisplay?id=${cardForTable1.id}" width="140"
                                 height="180"/>
                            <c:choose>
                            <c:when test="${whoTurn==2}">

                        </a>
                        </c:when>
                        </c:choose>

                        <br/>
                        <b>Attack:&nbsp; ${cardForTable1.damage}&nbsp; </b> <b>HP:&nbsp; ${cardForTable1.hp}&nbsp; </b>
                    </td>
                </c:forEach>
            </tr>


        </table>


    </c:when>
</c:choose>


<table width="1200px" height="80">
    <tr>
        <td width="400px"></td>
        <td width="400px"></td>
        <td width="400px"><h2>Now turn ${turnLogin}</h2></td>
        <c:choose>
            <c:when test="${whoIs==1}">
                <c:choose>
                    <c:when test="${whoTurn==1}">

                        <td width="400px" height="80px">
                            <p>&nbsp;&nbsp;${mess} </p>
                            <form method='GET'>
                                <input type='submit' class='buttonEnabled' name='id' value='RETURN'/>
                            </form>

                        </td>
                    </c:when>
                </c:choose>

            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${whoIs==2}">
                <c:choose>
                    <c:when test="${whoTurn==2}">

                        <td width="400px" height="80px">
                            <p>&nbsp;&nbsp;${mess} </p>
                            <form method='GET'>
                                <input type='submit' class='buttonEnabled' name='id' value='RETURN'/>
                            </form>

                        </td>
                    </c:when>
                </c:choose>

            </c:when>
        </c:choose>


    </tr>
</table>

<%----%>
<c:choose>
    <c:when test="${whoIs==1}">

        <table>
            <tr>
                <td>
                    <img src="${pathHead}/adImage/imageDisplay?id=-1" width="80" height="100px"
                         style="border-radius:100%"/>
                    <br/> <b>Mana:&nbsp; ${currentManaHero1}
                    /Total&nbsp; ${manaHero1} </b>
                </td>
                <td width="130" height="165"></td>


                <c:forEach items="${tableCollectionHero1}" var="cardForTable1">

                    <c:if test="${cardForTable1.active == false}">

                        <td width="130" height="165" style="border: 3px solid #0000cd">

                    </c:if>

                    <c:if test="${cardForTable1.active != false}">

                        <c:if test="${cardForTable1.id == cardOneId}">

                            <td width="130" height="165" style="border: 3px solid #f26522">

                        </c:if>

                        <c:if test="${cardForTable1.id != cardOneId}">
                            <td width="140" height="180">
                        </c:if>

                    </c:if>

                    <c:choose>
                        <c:when test="${whoTurn==1}">

                            <a href="${pathBattle}?id=${idBattle}table1${cardForTable1.id}" >

                        </c:when>
                    </c:choose>


                    <img src="${pathHead}/adImage/imageDisplay?id=${cardForTable1.id}" width="100"
                         height="120"/>


                    <c:choose>
                        <c:when test="${whoTurn==1}">

                            </a>

                        </c:when>
                    </c:choose>
                    <br/> Active:&nbsp;    ${cardForTable1.active}<br/>
                    <br/>
                    <b>Attack:&nbsp; ${cardForTable1.damage}&nbsp; </b> <b>HP:&nbsp; ${cardForTable1.hp}&nbsp; </b>
                    </td>
                </c:forEach>
            </tr>


        </table>

        <br/><br/>
        <table>
            <td>

                <img src="${pathHead}/adImage/imageDisplay?id=0" width="80" height="100px"/>

                <br/>
                <b>Deck:&nbsp; ${deckCardHero1}&nbsp; cards </b>
            </td>

            <td width="150" height="165"> </td>

            <c:if test="${activateHero1 == false}">
            <td width="150" height="165" style="border: 3px solid #0000cd">
                <center>
                    <b>${idUser1Login}</b>
                    <c:choose>
                    <c:when test="${whoTurn==1}">
                    <a href="${pathBattle}?id=${idBattle}avatarHero1">
                        </c:when>
                        </c:choose>


                        <img src="${pathHead}/adImage/imageDisplay?id=${idUser1}" width="90" height="110px"
                             style="border-radius:100%" />

                        </c:if>

                        <c:if test="${activateHero1 != false}">

                        <c:if test="${cardOneId == -1}">

            <td width="150" height="165" style="border: 3px solid #f26522">
                <center>
                    <b>${idUser1Login}</b>
                    <c:choose>
                    <c:when test="${whoTurn==1}">
                    <a href="${pathBattle}?id=${idBattle}avatarHero1">
                        </c:when>
                        </c:choose>

                        <img src="${pathHead}/adImage/imageDisplay?id=${idUser1}" width="90" height="110px"
                             style="border-radius:100%" />
                        </c:if>

                        <c:if test="${cardOneId != -1}">
            <td width="150" height="165" >

                <center>
                    <b>${idUser1Login}</b>
                    <c:choose>
                    <c:when test="${whoTurn==1}">
                    <a href="${pathBattle}?id=${idBattle}avatarHero1">
                        </c:when>
                        </c:choose>

                        <img src="${pathHead}/adImage/imageDisplay?id=${idUser1}" width="90" height="110px"
                             style="border-radius:100%" />
                        </c:if>
                        </c:if>

                        <c:choose>
                        <c:when test="${whoTurn==1}">

                    </a>

                    </c:when>
                    </c:choose>

                    <br/>

                    Activate: ${activateHero1}<br/>

                    <b>Attack:&nbsp; ${attackHero1}&nbsp; </b> <b>HP:&nbsp; ${hpHero1}&nbsp; </b>
                </center>


            </td>

            <c:forEach items="${handCollectionHero1}" var="cardForHand1">


                <c:choose>
                    <c:when test="${whoTurn==1}">

                        <c:if test="${cardOneId == cardForHand1.intValue()}">
                            <td width="130" height="165" style="border: 3px solid #f26522">
                        </c:if>

                        <c:if test="${cardOneId != cardForHand1.intValue()}">
                            <td width="130" height="165">
                        </c:if>

                        <a href="${pathBattle}?id=${idBattle}hand1${cardForHand1.intValue()}">

                    </c:when>
                </c:choose>

                <c:choose>
                    <c:when test="${whoTurn != 1}">
                        <td width="130" height="165">
                    </c:when>
                </c:choose>

                <img src="${pathHead}/adImage/imageDisplay?id=${cardForHand1.intValue()}" width="140"
                     height="180"/>


                <c:choose>
                    <c:when test="${whoTurn==1}">

                        </a>

                    </c:when>
                </c:choose>


                </td>
            </c:forEach>


            </tr>


        </table>
    </c:when>
</c:choose>

<%---hero2----%>

<c:choose>
    <c:when test="${whoIs==2}">

        <table>
            <tr>
                <td>
                    <img src="${pathHead}/adImage/imageDisplay?id=-1" width="80" height="100px"
                         style="border-radius:100%"/>
                    <br/> <b>Mana:&nbsp; ${currentManaHero2}
                    /Total&nbsp; ${manaHero2} </b>
                </td>
                <td width="130" height="165"></td>


                <c:forEach items="${tableCollectionHero2}" var="cardForTable2">

                    <c:if test="${cardForTable2.active == false}">

                        <td width="130" height="165" style="border: 3px solid #0000cd">

                    </c:if>

                    <c:if test="${cardForTable2.active != false}">

                        <c:if test="${cardForTable2.id == cardOneId}">

                            <td width="130" height="165" style="border: 3px solid #f26522">

                        </c:if>

                        <c:if test="${cardForTable2.id != cardOneId}">
                            <td width="140" height="180">
                        </c:if>

                    </c:if>

                    <c:choose>
                        <c:when test="${whoTurn==2}">

                            <a href="${pathBattle}?id=${idBattle}table1${cardForTable2.id}" >

                        </c:when>
                    </c:choose>


                    <img src="${pathHead}/adImage/imageDisplay?id=${cardForTable2.id}" width="100"
                         height="120"/>


                    <c:choose>
                        <c:when test="${whoTurn==2}">

                            </a>

                        </c:when>
                    </c:choose>
                    <br/> Active:&nbsp;    ${cardForTable2.active}<br/>
                    <br/>
                    <b>Attack:&nbsp; ${cardForTable2.damage}&nbsp; </b> <b>HP:&nbsp; ${cardForTable2.hp}&nbsp; </b>
                    </td>
                </c:forEach>
            </tr>


        </table>

        <br/><br/>
        <table>
            <td>

                <img src="${pathHead}/adImage/imageDisplay?id=0" width="80" height="100px"/>

                <br/>
                <b>Deck:&nbsp; ${deckCardHero2}&nbsp; cards </b>
            </td>

            <td width="150" height="165"> </td>

            <c:if test="${activateHero2 == false}">
            <td width="150" height="165" style="border: 3px solid #0000cd">
                <center>
                    <b>${idUser2Login}</b>
                    <c:choose>
                    <c:when test="${whoTurn==2}">
                    <a href="${pathBattle}?id=${idBattle}avatarHero2">
                        </c:when>
                        </c:choose>


                        <img src="${pathHead}/adImage/imageDisplay?id=${idUser2}" width="90" height="110px"
                             style="border-radius:100%" />

                        </c:if>

                        <c:if test="${activateHero2 != false}">

                        <c:if test="${cardOneId == -1}">

            <td width="150" height="165" style="border: 3px solid #f26522">
                <center>
                    <b>${idUser2Login}</b>
                    <c:choose>
                    <c:when test="${whoTurn==2}">
                    <a href="${pathBattle}?id=${idBattle}avatarHero2">
                        </c:when>
                        </c:choose>

                        <img src="${pathHead}/adImage/imageDisplay?id=${idUser2}" width="90" height="110px"
                             style="border-radius:100%" />
                        </c:if>

                        <c:if test="${cardOneId != -1}">
            <td width="150" height="165" >

                <center>
                    <b>${idUser2Login}</b>
                    <c:choose>
                    <c:when test="${whoTurn==2}">
                    <a href="${pathBattle}?id=${idBattle}avatarHero2">
                        </c:when>
                        </c:choose>

                        <img src="${pathHead}/adImage/imageDisplay?id=${idUser2}" width="90" height="110px"
                             style="border-radius:100%" />
                        </c:if>
                        </c:if>

                        <c:choose>
                        <c:when test="${whoTurn==2}">

                    </a>

                    </c:when>
                    </c:choose>

                    <br/>

                    Activate: ${activateHero2}<br/>

                    <b>Attack:&nbsp; ${attackHero2}&nbsp; </b> <b>HP:&nbsp; ${hpHero2}&nbsp; </b>
                </center>


            </td>

            <c:forEach items="${handCollectionHero2}" var="cardForHand2">


                <c:choose>
                    <c:when test="${whoTurn==2}">

                        <c:if test="${cardOneId == cardForHand2.intValue()}">
                            <td width="130" height="165" style="border: 3px solid #f26522">
                        </c:if>

                        <c:if test="${cardOneId != cardForHand2.intValue()}">
                            <td width="130" height="165">
                        </c:if>

                        <a href="${pathBattle}?id=${idBattle}hand2${cardForHand2.intValue()}">

                    </c:when>
                </c:choose>

                <c:choose>
                    <c:when test="${whoTurn != 2}">
                        <td width="130" height="165">
                    </c:when>
                </c:choose>

                <img src="${pathHead}/adImage/imageDisplay?id=${cardForHand2.intValue()}" width="140"
                     height="180"/>


                <c:choose>
                    <c:when test="${whoTurn==2}">

                        </a>

                    </c:when>
                </c:choose>


                </td>
            </c:forEach>


            </tr>


        </table>
    </c:when>
</c:choose>



<center><b>MY DECK</b></center>


</body>
</html>