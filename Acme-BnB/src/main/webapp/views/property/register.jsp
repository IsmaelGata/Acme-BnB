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
	<form:hidden path="id"/>
	<form:hidden path="extraAttributes"/>
	
	<fieldset >
		<legend><spring:message code="property.simpleData"/></legend>
			<br/>
		<acme:textbox code="property.name" path="name" />
			<br/>
		<acme:textbox code="property.rate" path="rate" />
			<br/>
		<acme:textbox code="property.description" path="description" />
			<br/>
		<acme:textbox code="property.address" path="address" />
	</fieldset>
	<br/>
	<fieldset >
		<legend><spring:message code="property.extraAttributeData"/></legend>
			<br/>
		<jstl:forEach items="${propertyForm.relatedValues}" var="relatedValue" varStatus="vstatus">
			<form:hidden path="relatedValues[${vstatus.index}].id"/>
			<form:hidden path="relatedValues[${vstatus.index}].extraAttribute"/>
			<form:hidden path="relatedValues[${vstatus.index}].property"/>
			<acme:textbox2 attributeName="${relatedValue.extraAttribute.name}" path="relatedValues[${vstatus.index}].value" />
			<br/>
		</jstl:forEach>
	</fieldset>
	<br/>
		
		<acme:submit code="property.save" name="save"/>
		
		<acme:cancel code="property.cancel" url="welcome/index.do"/>
		
		
</form:form>