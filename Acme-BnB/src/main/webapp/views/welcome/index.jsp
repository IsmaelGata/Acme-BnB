<%--
 * index.jsp
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

<security:authorize access="hasRole('LESSOR')">
	<jstl:if test="${totalFee >0}">
		<spring:message code="welcome.greeting.totalFee" var="feeToPay"/>
		<p><font size="4" color="red"><jstl:out value="${feeToPay}"/></font></p><b><jstl:out value="${totalFee}"></jstl:out>&euro;</b>
	</jstl:if>
	
	<jstl:if test="${!(totalFee >0)}">
		<spring:message code="welcome.greeting.totalFeePaid" var="paid"/>
		<p><font size="4" color="green"><jstl:out value="${paid}"/></font></p>
	</jstl:if>
		
</security:authorize>

<p>
	<spring:message code="welcome.greeting.current.time" />
	${moment}
</p>
