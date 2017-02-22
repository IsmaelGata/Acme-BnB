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

	<fieldset >
		<legend><spring:message code="lessor.userAccountDetails"/></legend>
		<br/>
		<acme:textbox code="lessor.username" path="username" />
		<br/>
		<acme:password code="lessor.password" path="password" />
		<br/>
		<acme:password code="lessor.repeatPassword" path="repeatPassword" />
	</fieldset>
	<br/>
	<fieldset >
		<legend><spring:message code="lessor.personalDataDetails"/></legend>
		<br/>
		<acme:textbox code="lessor.name" path="name" />
		<br/>
		<acme:textbox code="lessor.surname" path="surname" />
		<br/>
		<acme:textbox code="lessor.phone" path="phone" />
		<br/>
		<acme:textbox code="lessor.email" path="email" />
		<br/>
		<acme:textbox code="lessor.picture" path="picture" />
		<br/>
		<acme:checkbox code="lessor.acceptCondition" path="acceptCondition" />
	</fieldset>
	<br/>
	<fieldset>
		<legend><spring:message code="lessor.creditCardDetails"/></legend>
		<br/>
		<acme:textbox code="creditCard.holderName" path="creditCard.holderName" />
		<br/>
		<acme:textbox code="creditCard.brandName" path="creditCard.brandName" />
		<br/>
		<acme:textbox code="creditCard.number" path="creditCard.number" />
		<br/>
		<acme:textbox code="creditCard.expirationMonth" path="creditCard.expirationMonth" />
		<br/>
		<acme:textbox code="creditCard.expirationYear" path="creditCard.expirationYear" />
		<br/>
		<acme:textbox code="creditCard.cvv" path="creditCard.cvv" />
	</fieldset>
	<br/>
	
		<acme:submit code="lessor.save" name="save"/>
		
		<acme:cancel code="lessor.cancel" url="welcome/index.do"/>
		
</form:form>
