<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<c:if test='${status eq "success"}'>
<h1 align='center'>Record Added Successfully</h1>
</c:if>
<c:if test='${status eq "failure"}'>
<h1 align='center'>Record Insertion Failed</h1>
</c:if>