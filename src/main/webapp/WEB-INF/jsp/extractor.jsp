<?xml version="1.0" encoding="UTF-8" ?><%@
page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ 
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Extract Email Addresses</title>
</head>
<body>
<div>
	<div>
		<h3>Email Address Extractor</h3>
		<div>
			Email Address Extractor test version (v 0.0.1). -Created by <a href="http://lckymn.com" target="_blank">Kevin</a>
		</div>
	</div>
	<br /><c:if test="${not empty(message)}">
	<div style="margin: 10px; color: red; font-weight: bolder;">
		${message }
	</div>
	</c:if>
	<div>
		<form action="${pageContext.request.contextPath }/extract" method="post" name="emailExtractorForm">
			<div>
				<label>Please enter text from which you want to extract email addresses.</label>
			</div>
			<div>
				<textarea name="inputValue" style="width: 80%; height: 400px;"><c:if test="${not empty(inputValue)}">${inputValue }</c:if></textarea>
			</div>
			<div><input type="hidden" name="action" value="extract" />
				<input type="submit" name="submitButton" value="   Submit   " style="margin-top: 10px;" /> 
				<a href="${pageContext.request.contextPath }/clear" style="margin-left: 20px; margin-top: 10px;">Clear</a> 
			</div>
		</form>
	</div>
</div>
<br />
<div>
<div>
<label>Result: </label>
</div>
<br />
<c:if test="${not empty resultMessage}"><div>${resultMessage }</div><c:remove var="resultMessage" scope="page" /></c:if>
<div>
<textarea readonly  style="width: 80%; height: 400px;"><c:if test="${not empty(result)}">${result }</c:if></textarea>
</div>
</div>
</body>
</html>