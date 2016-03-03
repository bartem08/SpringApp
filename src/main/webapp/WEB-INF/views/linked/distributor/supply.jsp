<%--suppress ELValidationInJSP --%>
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="qualityList" type="java.util.List"--%>
<%--@elvariable id="productList" type="java.util.List"--%>
<%--@elvariable id="supplyList" type="java.util.List"--%>
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
            <c:url value="/linked/distributor/${distributorID}/save-or-update" var="saveOrUpdateSupply"/>
            <form:form method="post" action="${saveOrUpdateSupply}" modelAttribute="supply">
                <td>
                    <c:if test="${!empty supply.ID}"><form:input path="ID" readonly="true"/></c:if>
                    <form:errors path="price"/>
                </td>
                <td><form:input path="distributorName" readonly="true"/></td>
                <td>
                    <form:select path="productName">
                        <form:options items="${productList}" />
                    </form:select>
                </td>
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
                        <a href="<c:url value="/linked/distributor/${distributorID}/delete/${supply.ID}"/>">
                            <img src="<c:url value="/resources/images/remove-icon.png"/>"/>
                        </a>
                        <a href="<c:url value="/linked/distributor/${distributorID}/update/supply?s_id=${supply.ID}"/>">
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