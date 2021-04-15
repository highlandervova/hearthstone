<%--
  Created by IntelliJ IDEA.
  User: M.Panarina
  Date: 13.04.2021
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">--%>
    <title>CREATE YOUR DECK</title>
</head>
<frameset rows="85%,15%">

    <frameset cols="35%,65%">
        <frame src="${pathUserDeck}?id=all" name="MYDECK">
        <frame src="${pathMainDeck}?id=all" name="CONTENT">
    </frameset>
    <frame src=${pathDeckBottom} name="BOTTOM" scrolling="no" noresize>
</frameset>

</html>

