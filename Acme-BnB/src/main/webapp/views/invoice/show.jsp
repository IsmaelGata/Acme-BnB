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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
	<b><spring:message code="invoice.moment" />:</b>
	<jstl:out value="${invoice.moment}"></jstl:out>

	<br /> <b><spring:message code="invoice.vatNumber" />:</b>
	<jstl:out value="${invoice.vatNumber}"></jstl:out>

	<br /> <b><spring:message code="invoice.details" />:</b>
	<jstl:out value="${invoice.details}"></jstl:out>

	<br /> <b><spring:message code="invoice.information" />:</b>
	<jstl:out value="${invoice.information}"></jstl:out>
	
	<br /> <b><spring:message code="invoice.totalAmount" />:</b>
	<jstl:out value="${invoice.totalAmount}"></jstl:out>
	
	<br /> <b><spring:message code="invoice.creditCard" />:</b>
	<jstl:set var="ccnl" value="${fn:length(invoice.creditCard.number) }"></jstl:set>
	<jstl:out value="************${fn:substring(invoice.creditCard.number, ccnl-4, ccnl)}"></jstl:out>
</div>

<br/>
	<acme:cancel url="book/listBooksTenant.do" code="invoice.back"/>

