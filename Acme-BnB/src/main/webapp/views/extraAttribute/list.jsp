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

<display:table name="extraAttributes" id="row" requestURI="${RequestURI}"
	pagesize="5">

	<spring:message code="extraAttribute.name" var="name" />
	<display:column property="name" title="${name}" />

	<spring:message code="extraAttribute.spanishName" var="spanishName" />
	<display:column property="spanishName" title="${spanishName}" />
	
	<spring:message code="extraAttribute.type" var="type" />
	<display:column title="${type }">
		<jstl:if test="${cookie['language'].value.equals('en')}">
			<jstl:out value="${row.type.name}"></jstl:out>
		</jstl:if>
		
		<jstl:if test="${cookie['language'].value.equals('es')}">
			<jstl:out value="${row.type.spanishName}"></jstl:out>
		</jstl:if>
		
		<jstl:if test="${cookie['language'].value == null}">
			<jstl:out value="${row.type.name}"></jstl:out>
		</jstl:if>
	</display:column>
	
	<display:column>
		<jstl:if test="${!row.name.equals('City')}">
			<acme:cancel code="extraAttribute.edit" url="extraAttribute/edit.do?extraAttributeId=${row.id}"/>
		</jstl:if>
	</display:column>
	
	<display:column>
		<jstl:if test="${!row.name.equals('City')}">
			<acme:cancel code="extraAttribute.delete" url="extraAttribute/delete.do?extraAttributeId=${row.id}"/>
		</jstl:if>
	</display:column>
	
</display:table>

<br/>

<acme:cancel code="extraAttribute.create" url="extraAttribute/create.do"/>
