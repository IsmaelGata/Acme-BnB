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

<form:form action="${RequestURI}" modelAttribute="propertyForm">
	
	<fieldset >
		<legend><spring:message code="property.simpleData"/></legend>
			<br/>
		<acme:textbox code="property.name" path="name" />
			<br/>
		<acme:textbox code="property.rate" path="rate" />
			<br/>
		<acme:password code="property.description" path="description" />
			<br/>
		<acme:password code="property.address" path="address" />
	</fieldset>
	<br/>
	<fieldset >
		<legend><spring:message code="property.extraAttributeData"/></legend>
			<br/>
		<jstl:forEach items="${propertyForm.relatedValues}" var="value">
			<acme:textbox2 attributeName="${value.extraAttribute.name}" path="${value.value}" />
			<br/>
		</jstl:forEach>
	</fieldset>
	<br/>
		
		<acme:submit code="tenant.save" name="save"/>
		
		<acme:cancel code="tenant.cancel" url="welcome/index.do"/>
		
		
</form:form>
