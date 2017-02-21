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

<form:form action="${requestURI}" modelAttribute="lessorForm">

		<acme:textbox code="lessor.username" path="username" />
			
		<acme:password code="lessor.password" path="password" />
		
		<acme:password code="lessor.repeatPassword" path="repeatPassword" />

		<acme:textbox code="lessor.name" path="name" />
		
		<acme:textbox code="lessor.surname" path="surname" />
	
		<acme:textbox code="lessor.phone" path="phone" />
	
		<acme:textbox code="lessor.email" path="email" />
	
		<acme:textbox code="lessor.picture" path="picture" />
	
		<acme:checkbox code="lessor.acceptCondition" path="acceptCondition" />
		
		<acme:submit code="lessor.save" name="save"/>
		
		<acme:cancel code="lessor.cancel" url="welcome/index.do"/>
		
</form:form>
