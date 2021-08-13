<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>New Product</title>
</head>
<body>
<h1>New Category</h1>
	<form:form action="/products" method="post" modelAttribute="product">
 
	<p>
        <form:label path="categories">Category </form:label>
        <form:errors path="categories"/>
       	<form:select path="categories" items="${categories}" itemLabel="name"/>

    </p> 
    <p>
       <form:label path="name">Name: </form:label>
        <form:errors path="name"/>
       	<form:input path="name"/>
    </p>
    
    <p>
        <form:label path="description">Description: </form:label>
        <form:errors path="description"/>
        <form:textarea path="description"/>
    </p>
    
      <p>
        <form:label path="price">Price</form:label>
        <form:errors path="price"/>
        <form:input type= "double" path="price"/>
    </p>
 
  
    <input type="submit" value="Create"/>
</form:form>	
</body>
</html>