<%--
 * action-1.jsp
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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<fieldset >
		<legend><spring:message code="property.moreDetails"/></legend>
		<jstl:forEach items="${relatedValues}" var="relatedValue">
			<jstl:out value="${relatedValue.extraAttribute.name}"/>: 
			<jstl:if test="${relatedValue.extraAttribute.type == 'BOOLEAN'}">
				<jstl:if test="${relatedValue.value == 'true'}">
					<spring:message code="property.booleanTrue" var="varTrue"/>
					<jstl:out value="${varTrue}"/>
				</jstl:if>
				
				<jstl:if test="${relatedValue.value == 'false'}">
					<spring:message code="property.booleanFalse" var="varFalse"/>
					<jstl:out value="${varFalse}"/>
				</jstl:if>
				
				<jstl:if test="${relatedValue.value != 'true' && relatedValue.value != 'false'}">
					<spring:message code="property.booleanError" var="booleanError"/>
					<jstl:out value="${booleanError}"/>
					<br/>
				</jstl:if>
			</jstl:if>
			<jstl:if test="${relatedValue.extraAttribute.type != 'BOOLEAN'}">
				<jstl:if test="${relatedValue.extraAttribute.type == 'URL'}">
					<a href="${relatedValue.value}"><jstl:out value="${relatedValue.value}"/></a>
				</jstl:if>
				
				<jstl:if test="${relatedValue.extraAttribute.type == 'PICTURE'}">
					<img src="${relatedValue.value}" style="width:304px;height:228px;">
				</jstl:if>
				
				<jstl:if test="${relatedValue.extraAttribute.type != 'PICTURE' || relatedValue.extraAttribute.type == 'URL'}">
					<jstl:out value="${relatedValue.value}"/>
				</jstl:if>
			</jstl:if>	
			<br/>
		</jstl:forEach>
		
</fieldset>
	<br/>
	<acme:cancel url="property/list.do" code="property.back"/>
	

