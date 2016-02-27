<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="distributorList" type="java.util.List"--%>
<!DOCTYPE html>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/table.css"/> " />
</head>
<body>
<div class="wrapper">
    <div>
        <div>
            <a href="<c:url value="/distributor/all"/>">Distributors</a>
            <a href="<c:url value="/product/all"/>">Products</a>
        </div>
    </div>
    <h1>Distributors table</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Date</th>
            <th>Action</th>
        </tr>
        <c:if test="${!empty distributorList}">
        <c:forEach items="${distributorList}" var="distributor">
            <c:url value="/linked/distributor/${distributor.ID}" var="distributorSupply"/>
            <tr>
                <td>${distributor.ID}</td>
                <td><a href="${distributorSupply}">${distributor.name}</a></td>
                <td><fmt:formatDate value="${distributor.cooperatedDate.time}" pattern="dd.MM.yyyy" /></td>
                <td>
                    <c:url value="/distributor/delete/${distributor.ID}" var="deleteDistributor"/>
                    <a href="${deleteDistributor}"><img src="<c:url value="/resources/images/remove-icon.png"/>"/></a>
                    <c:url value="/distributor/update/distributor?id=${distributor.ID}" var="updateForm"/>
                    <a href="${updateForm}"><img src="<c:url value="/resources/images/update-icon.png"/>"/></a>
                </td>
            </tr>
        </c:forEach>
        </c:if>
        <tr>
            <c:url value="/distributor/save-or-update" var="saveOrUpdateDistributor"/>
            <form:form method="post" action="${saveOrUpdateDistributor}" modelAttribute="distributor">
                <td><form:input path="ID" readonly="true"/></td>
                <td><form:input path="name" /></td>
                <td><fmt:formatDate value="${distributor.cooperatedDate.time}" pattern="dd.MM.yyyy"/></td>
                <td>
                    <button type="submit">
                    <c:if test="${distributor.ID == null}">Add</c:if>
                    <c:if test="${distributor.ID != null}">Update</c:if>
                    </button>
                </td>
            </form:form>
        </tr>
    </table>
</div>
</body>
</html>