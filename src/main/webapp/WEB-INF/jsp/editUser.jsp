<%@ page import="data.User" %>
<%@ page import="static enums.SessionAttribute.AUTHENTICATED" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <style type='text/css'>
    body{
    background:#b0e0e6;
    /*  background: gradient(to bottom, #00bfff )*/;}
    </style>
    <title>${title}</title>
    <h1>Edit account</h1>
</head>
<body>
<form  method='POST'>
    <%User user = (User) session.getAttribute(AUTHENTICATED.getValue());%>
    Enter Login: <input type='text' required value="<%=user.getLogin()%>" required name='login'>
    Enter current password:  &nbsp <input type='password' name='curPass'>
    <br/> <br/>
    Enter new password: <input type='password' name='pass1'>
    Enter new password again: <input type='password' name='pass2'>
    <br/> <br/>
    Enter race:
    <select name="race">
        <c:forEach items="${allRace}" var="rac">
            <c:choose>
                <c:when test="${rac.id==raceIdUser}" >
                    <option value=${rac.id}> ${rac.name} </option>
                </c:when>
            </c:choose>
        </c:forEach>
        <c:forEach items="${otherRaces}" var="rac">
            <option value=${rac.id}> ${rac.name} </option>
        </c:forEach>
    </select>

    Enter Name: <input type='text' value="<%=user.getName()%>" required name='name'>
    Enter Lastname: <input type='text' value="<%=user.getLastname()%>" required name='lastname'>
    Enter Email: <input type='email' value="<%=user.getEmail()%>" required name= 'email'>
    <br/> <br/>
    <c:out value="${status}"/>
    <br/> <br/>
    <input type='submit' value='Save Changes'>
</form>

<br/>
<br/>


<form action=${pathMain}  method='GET'>
    <input type='submit' value='To Main Page'>
</form>


</body>
</html>
