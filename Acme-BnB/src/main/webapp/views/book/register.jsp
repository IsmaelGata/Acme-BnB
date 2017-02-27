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

<form:form action="${RequestURI}" modelAttribute="bookForm">

		<form:hidden path="idProperty"/>
	
	<fieldset>
		<legend><spring:message code="book.bookDetails"/></legend>
		<acme:textbox code="book.checkIn" path="checkIn" />
		<br/>
		<acme:textbox code="book.checkOut" path="checkOut" />
		<br/>
		<acme:checkbox code="book.smoker" path="smoker" />
		<br/>
	</fieldset>
	<br/>
		
	<fieldset>
		<legend><spring:message code="book.creditCardDetails"/></legend>
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
		<acme:submit code="book.save" name="save"/>
		
		<acme:cancel code="book.cancel" url="welcome/index.do"/>
		
</form:form>
