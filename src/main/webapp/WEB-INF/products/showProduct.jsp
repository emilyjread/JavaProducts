<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Show Product</title>
</head>
<body>
<h1>Product: <c:out value="${product.name}"/> </h1>

<p>Categories: </p>
<ul>
	<c:forEach var="c" items="${categoriesOfProduct}">
		<li><c:out value="${c.name}"/></li>
	</c:forEach>
</ul>

<form:form action="/products/${product.id}" method="post" modelAttribute="product">
	<input type="hidden" name="_method" value="put">
	<p>
        <form:label path="categories">Category: </form:label>
        <form:errors path="categories"/>
        <form:select path="categories" items="${categoriesNotApplied}" itemLabel="name"/>
    </p>
    
    <input type="submit" value="Add Category"/>
    
</form:form>
	
</body>
</html>