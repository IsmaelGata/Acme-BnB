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
	<b><spring:message code="tenant.name" />:</b>
	<jstl:out value="${tenant.name}"></jstl:out>

	<br /> <b><spring:message code="tenant.surname" />:</b>
	<jstl:out value="${tenant.surname}"></jstl:out>

	<br /> <b><spring:message code="tenant.phone" />:</b>
	<jstl:out value="${tenant.phone}"></jstl:out>

	<br /> <b><spring:message code="tenant.email" />:</b>
	<jstl:out value="${tenant.email}"></jstl:out>

	<br /> <spring:message code="tenant.picture" var="picture" />
	<img src="${tenant.picture}" alt="picture" width="150" height="150">

</div>

<br><br>

<h2><spring:message code="tenant.socialIdentities" /></h2>	

<div>
	<display:table name="tenant.socialIdentities" id="row" requestURI="${RequestURI}"
		pagesize="5">
	
		<spring:message code="socialIdentity.nick" var="nick" />
		<display:column property="nick" title="${nick}" />
	
		<spring:message code="socialIdentity.nameSocialNetwork" var="nameSocialNetwork" />
		<display:column property="nameSocialNetwork" title="${nameSocialNetwork}" />
	
		<spring:message code="socialIdentity.URL" var="URL" />
		<display:column property="URL" title="${URL}" />
		
	</display:table>
</div>

<div>
	<h2><spring:message code="tenant.comments" /></h2>
	
	<display:table name="comments" id="row" requestURI="${RequestURI}" pagesize="5">
	
		<spring:message code="tenant.comment.title" var="title" />
		<display:column property="title" title="${title}" />
	
		<spring:message code="tenant.comment.moment" var="moment" />
		<display:column property="moment" title="${moment}" />
	
		<spring:message code="tenant.comment.text" var="text" />
		<display:column property="text" title="${text}" />
		
		<spring:message code="tenant.comment.stars" var="stars" />
		<display:column property="stars" title="${stars}" />
	
		<spring:message code="tenant.comment.author" var="authorVar" />
		<display:column title="${authorVar}">
			<jstl:out value="${row.author.name} ${row.author.surname}"></jstl:out>
		</display:column>
		
	</display:table>
	
	<security:authorize access="hasRole('TENANT')">
		<acme:cancel url="comment/create.do?comentableId=${tenant.id}&comentableType=Tenant" code="tenant.newComment" />
	</security:authorize>
</div>

