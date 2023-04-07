<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix = 'c'%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Search Student Form</title>
</head>
<body>
<div class="row">
	<div class="col-md-12">
	<c:choose>
	<c:when test="${student ne null || !empty student }">
		<form action="./controller/updateform" method="post">
			<h1>Update Student</h1>

			<fieldset>
				
				<label for="name">Id:</label>
				<input type="text" readonly="readonly" name='id' value='${student.sid }'/>
				<br/>
				<label for="name">New Name:</label> 
				<input type="text" id="name" name="name" value='${student.sname }'/> 
				<br/>
				<label for="name">New Age:</label> 
				<input type="text" id="name" name="age" value='${student.sage }'/>
				<br/>
				<label for="name">New Address:</label> 
				<input type="text" id="name" name="address" value='${student.saddress }'/>
				<br/>

			</fieldset>


			<button type="submit">Update</button>

		</form>
		</c:when>
		<c:otherwise>
			<h1 style='color:red;text-align: center'>
				NO RECORD TO DISPLAY
			</h1>
		</c:otherwise>
	</c:choose>
	</div>
</div>
</body>
</html>