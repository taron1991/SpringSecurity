<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<body>

<h2> Hello Employee. Write your name! </h2>

<br>
<br>

<form:form action="success" method="get">
    Name: <form:input path="firstname"/>
    <br>
    Surname: <form:input path="lastname"/>
    <br>
    <input type="submit" value="OK">
</form:form>

</body>
