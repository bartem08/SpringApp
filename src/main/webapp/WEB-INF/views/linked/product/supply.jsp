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
    <h1>Supplies table</h1>
    <form:form cssClass="error" modelAttribute="supply"><form:errors path="price"/></form:form>
    <table>
        <tr>
            <th>ID</th>
            <th>Distributor</th>
            <th>Product</th>
            <th>Quality</th>
            <th>Price</th>
            <th>Action</th>
        </tr>
        <tr>
            <c:url value="/linked/product/${productID}/save-or-update" var="saveOrUpdateSupply"/>
            <form:form method="post" action="${saveOrUpdateSupply}" modelAttribute="supply">
                <td>
                    <form:input path="ID" readonly="true"/>
                </td>
                <td>
                    <form:select path="distributorName">
                        <form:options items="${distributorList}" />
                    </form:select>
                </td>
                <td><form:input path="productName" readonly="true"/></td>
                <td>
                    <form:select path="quality">
                        <form:options items="${qualityList}"/>
                    </form:select>
                </td>
                <td><form:input path="price"/></td>
                <td>
                    <button type="submit">
                        <c:if test="${supply.ID == null}">Add</c:if>
                        <c:if test="${supply.ID != null}">Update</c:if>
                    </button>
                </td>
            </form:form>
        </tr>
        <c:if test="${!empty supplyList}">
            <c:forEach items="${supplyList}" var="supply">
                <tr>
                    <td>${supply.ID}</td>
                    <td>${supply.distributorName}</td>
                    <td>${supply.productName}</td>
                    <td>${supply.quality}</td>
                    <td>${supply.price}</td>
                    <td>
                        <a href="<c:url value="/linked/product/${productID}/delete/${supply.ID}"/>">
                            <img src="<c:url value="/resources/images/remove-icon.png"/>"/>
                        </a>
                        <a href="<c:url value="/linked/product/${productID}/update/supply?s_id=${supply.ID}"/>">
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