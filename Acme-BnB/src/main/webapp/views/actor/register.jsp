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

<form:form action="${RequestURI}" modelAttribute="actor">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="surname"/>
	<form:hidden path="name"/>

	<fieldset >
		<legend><spring:message code="actor.personalDataDetails"/></legend>
		<br/>
		<acme:textbox code="actor.phone" path="phone" />
		<br/>
		<acme:textbox code="actor.email" path="email" />
		<br/>
		<acme:textbox code="actor.picture" path="picture" />
	</fieldset>

		<br/>
		<acme:submit code="actor.save" name="save"/>
		
		<acme:cancel code="actor.cancel" url="welcome/index.do"/>
		
</form:form>
