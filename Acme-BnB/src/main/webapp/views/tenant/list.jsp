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

<display:table name="tenants" id="row" requestURI="${RequestURI}"
	pagesize="5">

	<spring:message code="tenant.name" var="name" />
	<display:column property="name" title="${name}" />

	<spring:message code="tenant.surname" var="surname" />
	<display:column property="surname" title="${surname}" />
	
	<spring:message code="tenant.phone" var="phone" />
	<display:column property="phone" title="${phone}" />

	<spring:message code="tenant.email" var="email" />
	<display:column property="email" title="${email}" />

	<br /> <spring:message code="tenant.picture" var="picture" />
	<img src="${tenant.picture}" alt="picture" width="150" height="150">
	

	<security:authorize access="hasRole('LESSOR')">
		<display:column>
			<acme:cancel
				url="tenant/doComment.do?tenantId=${row.id}"
				code="tenant.comment" />
		</display:column>
	</security:authorize>

</display:table>
