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

<form:form action="${RequestURI}" modelAttribute="auditForm">

		<form:hidden path="idProperty"/>
		<form:hidden path="id"/>
	
	<fieldset>
		<legend><spring:message code="audit.auditDetails"/></legend>
		<acme:textbox code="audit.text" path="text" />
		<br/>
		<acme:textbox code="audit.attachments" path="attachments" />
		<br/>
		<acme:checkbox code="audit.draft" path="draft" />
		<br/>
	</fieldset>
		
		<br/>
		<br/>
		<acme:submit code="audit.save" name="save"/>
		
		<acme:cancel code="audit.cancel" url="welcome/index.do"/>
		
</form:form>
