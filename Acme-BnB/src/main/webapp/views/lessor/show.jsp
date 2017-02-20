<%--
 * action-1.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div>
	<b><spring:message code="lessor.name" />:</b>
	<jstl:out value="${lessor.name}"></jstl:out>

	<br /> <b><spring:message code="lessor.surname" />:</b>
	<jstl:out value="${lessor.surname}"></jstl:out>

	<br /> <b><spring:message code="lessor.phone" />:</b>
	<jstl:out value="${lessor.phone}"></jstl:out>

	<br /> <b><spring:message code="lessor.email" />:</b>
	<jstl:out value="${lessor.email}"></jstl:out>

	<br /> <spring:message code="lessor.picture" var="picture" />
	<img src="${lessor.picture}" alt="picture" width="150" height="150">

</div>

