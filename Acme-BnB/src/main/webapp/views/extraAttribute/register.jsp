<%--
 * action-1.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${RequestURI}" modelAttribute="extraAttribute">

		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="relatedValues"/>
	
	<fieldset>
		<legend><spring:message code="extraAttribute.details"/></legend>
		<acme:textbox code="extraAttribute.name" path="name" />
		<br/>
		<acme:textbox code="extraAttribute.spanishName" path="spanishName" />
		<br/>
		<jstl:if test="${cookie['language'].value.equals('en')}">
			<acme:select2 code="extraAttribute.types" path="type" items="${enumConstants}" itemLabel="name"/>
		</jstl:if>
		<jstl:if test="${cookie['language'].value.equals('es')}">
			<acme:select2 code="extraAttribute.types" path="type" items="${enumConstants}" itemLabel="spanishName"/>
		</jstl:if>
		<br/>
	</fieldset>
	<br/>
		

		<br/>
		<acme:submit code="extraAttribute.save" name="save"/>
		
		<acme:cancel code="extraAttribute.cancel" url="welcome/index.do"/>
		
</form:form>
