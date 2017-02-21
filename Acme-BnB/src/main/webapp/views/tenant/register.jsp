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

<form:form action="${requestURI}" modelAttribute="tenantForm">

		<acme:textbox code="tenant.username" path="username" />
			
		<acme:password code="tenant.password" path="password" />
		
		<acme:password code="tenant.repeatPassword" path="repeatPassword" />

		<acme:textbox code="tenant.name" path="name" />
		
		<acme:textbox code="tenant.surname" path="surname" />
	
		<acme:textbox code="tenant.phone" path="phone" />
	
		<acme:textbox code="tenant.email" path="email" />
	
		<acme:textbox code="tenant.picture" path="picture" />
	
		<acme:checkbox code="tenant.acceptCondition" path="acceptCondition" />
		
		<acme:submit code="tenant.save" name="save"/>
		
		<acme:cancel code="tenant.cancel" url="welcome/index.do"/>
		
</form:form>
