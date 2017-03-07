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

<br><br>

<div>
	<display:table name="lessor.socialIdentities" id="row" requestURI="${RequestURI}"
		pagesize="5">
	
		<spring:message code="socialIdentity.nick" var="nick" />
		<display:column property="nick" title="${nick}" />
	
		<spring:message code="socialIdentity.nameSocialNetwork" var="nameSocialNetwork" />
		<display:column property="nameSocialNetwork" title="${nameSocialNetwork}" />
	
		<spring:message code="socialIdentity.URL" var="URL" />
		<display:column property="URL" title="${URL}" />
	
		<display:column>
			<acme:cancel url="socialIdentity/edit.do?socialIdentityId=${row.id}" code="socialIdentity.edit"/>
		</display:column>
		
		<display:column>
			<acme:cancel url="socialIdentity/delete.do?socialIdentityId=${row.id}" code="socialIdentity.delete"/>
		</display:column>
		
	</display:table>
</div>

