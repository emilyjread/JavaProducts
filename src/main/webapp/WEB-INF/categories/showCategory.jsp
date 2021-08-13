<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Show Category</title>
</head>
<body>
<h1>Category: <c:out value="${category.name}"/> </h1>

<p>Products:</p>
<ul>
	<c:forEach var="p" items="${productsByCategory}">
		<li><c:out value="${p.name}"/></li>
	</c:forEach>
</ul>

<form:form action="/categories/${category.id}" method="post" modelAttribute="category">
	<input type="hidden" name="_method" value="put">
	<p>
        <form:label path="products">Category: </form:label>
        <form:errors path="products"/>
        <form:select path="products" items="${productsNotInCategory}" itemLabel="name"/>
    </p>
    
    <input type="submit" value="Add Product"/>
    
</form:form>
	
</body>
</html>