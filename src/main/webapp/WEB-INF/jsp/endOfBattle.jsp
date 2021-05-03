<%@ page import="data.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <style type='text/css'>

        #bold0{
            font-weight: bold;
        }
        body{
            background:#b0e0e6;
            /*  background: gradient(to bottom, #00bfff )*/;}


    </style>

    <title>${title}</title>
    <br/>
    <h2>End of battle page</h2>
    <h2>${mess}</h2>
<br/>
</head> <body>
<br/>
<table width="80%" align="center">
    <th>


        <form action=${pathWaitBattle} method='GET'>
            <input type='submit' class='buttonEnabled' value='START GAME'/>
        </form>

    </th>
</table><br/><br/><br/>
        <b>

            <table width="80%" align="center">
                <th>
                    <center>
                        <img src="${pathHead}/adImage/imageDisplay?id=${idUser}" width="240" height="280"/>
                    </center>
                </th>
                <b>
                    <th align="left">
                        <b> ${login} has:
                            YOUR GOLD now &nbsp ${gold} <br/>
                            YOUR POINTS now &nbsp ${point} <br/></b >
                      <br/>
                        <br/>
                        <form action=${pathMain} method='GET'>
                            <input type='submit' value='Main page'/>
                        </form>
                        <br/>
                    </th>
            </table>

                        <br/> <br/>
        </b>
        <br/>


<br/>
<br/>



</body>
</html>