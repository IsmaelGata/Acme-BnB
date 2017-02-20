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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="descriptions" id="row" requestURI="${RequestURI}"
	pagesize="5">

	<spring:message code="property.name" var="name" />
	<display:column property="name" title="${name}" />

	<spring:message code="property.rate" var="rate" />
	<display:column property="rate" title="${rate}" />

	<spring:message code="property.description" var="description" />
	<display:column property="description" title="${description}" />

	<spring:message code="property.address" var="address" />
	<display:column property="address" title="${address}" />

	<spring:message code="property.country" var="country" />
	<display:column property="country" title="${country}" />

	<spring:message code="property.province" var="province" />
	<display:column property="province" title="${province}" />

	<spring:message code="property.state" var="state" />
	<display:column property="state" title="${state}" />

	<spring:message code="property.city" var="city" />
	<display:column property="city" title="${city}" />

	<spring:message code="property.capability" var="capability" />
	<display:column property="capability" title="${capability}" />
	
	<acme:cancel url="lessor/lessorByProperty.do?idProperty=${row.id}" code="property.lessor"/>

</display:table>
