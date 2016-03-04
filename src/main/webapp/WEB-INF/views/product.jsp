<%--suppress ALL --%>
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/table.css"/> " />
</head>
<body>
<div class="wrapper">
    <div>
        <a href="<c:url value="/distributor/all"/>">Distributors</a>
        <a href="<c:url value="/product/all"/>">Products</a>
    </div>
    <h1>Products table</h1>
    <form:form cssClass="error" modelAttribute="product"><form:errors path="name"/></form:form>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Category</th>
            <th>Action</th>
        </tr>
        <tr>
            <c:url value="/product/save-or-update" var="saveOrUpdateProduct"/>
            <form:form method="post" action="${saveOrUpdateProduct}" modelAttribute="product">
                <td><form:input path="ID" readonly="true"/></td>
                <td><form:input path="name" /></td>
                <td><form:select path="category" items="${categoryList}"/></td>
                <td>
                    <button type="submit">
                        <c:if test="${product.ID == null}">Add</c:if>
                        <c:if test="${product.ID != null}">Update</c:if>
                    </button>
                </td>
            </form:form>
        </tr>
        <c:if test="${!empty productList}">
            <c:forEach items="${productList}" var="product">
                <c:url value="/linked/product/${product.ID}" var="productSupply"/>
                <tr>
                    <td>${product.ID}</td>
                    <td><a href="${productSupply}">${product.name}</a></td>
                    <td>${product.category}</td>
                    <td>
                        <a href="<c:url value="/product/delete/${product.ID}" />">
                            <img src="<c:url value="/resources/images/remove-icon.png"/>"/>
                        </a>
                        <a href="<c:url value="/product/update/product?id=${product.ID}"/>">
                            <img src="<c:url value="/resources/images/update-icon.png"/>"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
</body>
</html>