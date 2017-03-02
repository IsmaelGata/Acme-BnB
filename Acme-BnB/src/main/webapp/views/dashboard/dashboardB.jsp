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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fieldset>
	<legend>
		<b><spring:message
				code="dashboard.listPropertiesSortedAccordingNumberOfAuditsThatTheyHaveGot" /></b>
	</legend>

	<jstl:forEach items="${propertyOrderAudits}" var="item">
		<h2>
			<jstl:out value="${item.key}"></jstl:out>
		</h2>
		<display:table name="${item.value}" id="row"
			requestURI="administrator/dashboardB" class="displaytag">
			<spring:message code="dashboard.property" var="property" />
			<display:column title="${property}">
				<jstl:out value="${row.name}"></jstl:out>
			</display:column>
			<spring:message code="dashboard.numberOfAudits" var="numberOfAudits" />
			<display:column title="${numberOfAudits}">
				<jstl:out value="${row.audits.size()}"></jstl:out>
			</display:column>
		</display:table>
		<br />
	</jstl:forEach>
</fieldset>

<br />

<fieldset>
	<legend>
		<b><spring:message
				code="dashboard.listPropertiesSortedAccordingNumberOfBooksThatTheyHaveGot" /></b>
	</legend>

	<jstl:forEach items="${propertyOrderBook}" var="item">
		<h2>
			<jstl:out value="${item.key}"></jstl:out>
		</h2>
		<display:table name="${item.value}" id="row"
			requestURI="administrator/dashboardB" class="displaytag">
			<spring:message code="dashboard.book" var="book" />
			<display:column title="${book}">
				<jstl:out value="${row.name}"></jstl:out>
			</display:column>
			<spring:message code="dashboard.numberOfBook" var="numberOfBook" />
			<display:column title="${numberOfBook}">
				<jstl:out value="${row.books.size()}"></jstl:out>
			</display:column>
		</display:table>
		<br />
	</jstl:forEach>
</fieldset>

<br />

<fieldset>
	<legend>
		<b><spring:message
				code="dashboard.listPropertiesSortedAccordingNumberOfApprovedBooksThatTheyHaveGot" /></b>
	</legend>

	<jstl:forEach items="${propertyOrderBookAcepted}" var="item">
		<h2>
			<jstl:out value="${item.key}"></jstl:out>
		</h2>


		<display:table name="${item.value}" id="row"
			requestURI="administrator/dashboardB" class="displaytag">
			<jstl:forEach items="${item.value}" var="item2">
				<spring:message code="dashboard.property" var="property" />
				<display:column title="${property}">
					<jstl:out value="${item2.key.name}"></jstl:out>
				</display:column>

				
				<spring:message code="dashboard.numberOfBookAproved"
					var="numberOfBookAproved" />
				<display:column title="${numberOfBookAproved}">
					<jstl:out value="${count}"></jstl:out>
				</display:column>

			</jstl:forEach>

		</display:table>
		<br />
	</jstl:forEach>
</fieldset>

<br />


