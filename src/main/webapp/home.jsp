<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Employee Id   : ${employee.eid}</br>
Employee Name : ${employee.department}</br>
Employee Deptt: ${employee.name}</br>

<form action="employee">
	Employee Id : <input type="text" name="eid"/></br>
	Employee Name : <input type="text" name="name"/></br>
	Employee Deptt: <input type="text" name="department"/></br>
	<input type="submit" value="Save Employee">

</form>

</body>
</html>