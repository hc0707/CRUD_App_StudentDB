<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<table border='1' align='center'>
<tr>
<th><h2>ID</h2></th>
<th><h2>NAME</h2></th>
<th><h2>AGE</h2></th>
<th><h2>ADDRESS</h2></th>
</tr>
<c:forEach var='row' items='${student}'>
<tr>
<td>
<h3>${row.sid}</h3>
</td>
<td>
<h3>${row.sname}</h3>
</td>
<td>
<h3>${row.sage}</h3>
</td>
<td>
<h3>${row.saddress}</h3>
</td>
</tr>
</c:forEach>
</table>