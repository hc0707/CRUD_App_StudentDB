<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<c:if test='${status eq "success"}'>
<h1 align='center'>Record Deleted Successfully</h1>
</c:if>
<c:if test='${status eq "failure"}'>
<c:import url="recordNotFound.jsp">
<c:param name="id" value='${id}'></c:param>
</c:import>
</c:if>