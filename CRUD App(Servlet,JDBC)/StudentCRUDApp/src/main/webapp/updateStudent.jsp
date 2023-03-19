<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<center>
<h1>RECORD BEFORE UPDATE</h1>
<table border=1>
		<tr>
			<th><h2>ID</h2></th>
			<th><h2>NAME</h2></th>
			<th><h2>AGE</h2></th>
			<th><h2>ADDRESS</h2></th>
		</tr>
		<tr>
			<td><h3>${student1.sid}</h3></td>
			<td><h3>${student1.sname}</h3></td>
			<td><h3>${student1.sage}</h3></td>
			<td><h3>${student1.saddress}</h3></td>
		</tr>
	</table>
<h1>RECORD AFTER UPDATE</h1>
<table border=1>
		<tr>
			<th><h2>ID</h2></th>
			<th><h2>NAME</h2></th>
			<th><h2>AGE</h2></th>
			<th><h2>ADDRESS</h2></th>
		</tr>
		<tr>
			<td><h3>${student2.sid}</h3></td>
			<td><h3>${student2.sname}</h3></td>
			<td><h3>${student2.sage}</h3></td>
			<td><h3>${student2.saddress}</h3></td>
		</tr>
	</table>
</center>