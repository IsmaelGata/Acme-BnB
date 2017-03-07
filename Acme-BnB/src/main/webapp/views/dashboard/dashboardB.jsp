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
				code="dashboard.averageMaximumAndMinimunNumberOfAuditsPerProperty" /></b>
	</legend>
	<jstl:set var="i" value="0"></jstl:set>
	<display:table name="${stadistictsPerProperty}" id="row"
		requestURI="administrator/dashboardB" class="displaytag">
		<spring:message code="dashboard.statistics" var="statistics" />
		<display:column title="${statistics}">
			<jstl:choose>
				<jstl:when test="${i == 0}">
					<spring:message code="dashboard.avg" var="title" />
					<jstl:set var="i" value="1"></jstl:set>
				</jstl:when>
				<jstl:when test="${i == 1}">
					<spring:message code="dashboard.min" var="title" />
					<jstl:set var="i" value="2"></jstl:set>
				</jstl:when>
				<jstl:otherwise>
					<spring:message code="dashboard.max" var="title" />
					<jstl:set var="i" value="3"></jstl:set>
				</jstl:otherwise>
			</jstl:choose>

			<fmt:formatNumber var="value" value="${row}" maxFractionDigits="2"></fmt:formatNumber>
			<b><jstl:out value="${title}:"></jstl:out></b>
			<jstl:out value="${value}"></jstl:out>
		</display:column>
	</display:table>
</fieldset>

<br />

<fieldset>
	<legend>
		<b><spring:message
				code="dashboard.extraAttributesOrderByUse" /></b>
	</legend>
	<display:table name="${extraAttributesOrderByUse}" id="row"
		requestURI="administrator/dashboardB" class="displaytag">
		
		<jstl:if test="${cookie['language'].value.equals('en')}">
			<spring:message code="dashboard.attributes" var="attributes" />
			<display:column property="name" title="${attributes}"/>
		</jstl:if>
		
		<jstl:if test="${cookie['language'].value.equals('es')}">
			<spring:message code="dashboard.attributes" var="attributes" />
			<display:column property="spanishName" title="${attributes}"/>
		</jstl:if>
			
	</display:table>
</fieldset>
<br/>
<fieldset>
	<legend>
		<b><spring:message
				code="dashboard.listPropertiesSortedAccordingNumberOfAuditsThatTheyHaveGot" /></b>
	</legend>
	<jstl:forEach items="${propertyOrderBook}" var="item">
		<h2>
			<jstl:out value="${item.key}"></jstl:out>
		</h2>
		<display:table name="${item.value}" id="rowTable"
			requestURI="administrator/dashboardB" class="displaytag">
			<spring:message code="dashboard.property" var="property" />
			<display:column title="${property}">
				<jstl:out value="${rowTable.name}"></jstl:out>
			</display:column>
			<spring:message code="dashboard.numberOfAudits" var="numberOfAudits" />
			<display:column title="${numberOfAudits}">
				<jstl:out value="${rowTable.audits.size()}"></jstl:out>
			</display:column>
		</display:table>
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

			<spring:message code="dashboard.property" var="property" />
			<display:column title="${property}">
				<jstl:out value="${row.name}"></jstl:out>
			</display:column>

			<jstl:set var="count" value="0"></jstl:set>
			<jstl:forEach items="${row.books}" var="book">
				<jstl:if test="${book.status.getName() == 'ACEPTED'}">
					<jstl:set var="count" value="${count+1}"></jstl:set>
				</jstl:if>
			</jstl:forEach>

			<spring:message code="dashboard.numberOfBookAproved"
				var="numberOfBookAproved" />
			<display:column title="${numberOfBookAproved}">
				<jstl:out value="${count}"></jstl:out>
			</display:column>
		</display:table>
		<br />
	</jstl:forEach>
</fieldset>

<br />

<fieldset>
	<legend>
		<b><spring:message
				code="dashboard.listPropertiesSortedAccordingNumberOfDeniedBooksThatTheyHaveGot" /></b>
	</legend>

	<jstl:forEach items="${propertyOrderBookDenied}" var="item">
		<h2>
			<jstl:out value="${item.key}"></jstl:out>
		</h2>


		<display:table name="${item.value}" id="row"
			requestURI="administrator/dashboardB" class="displaytag">

			<spring:message code="dashboard.property" var="property" />
			<display:column title="${property}">
				<jstl:out value="${row.name}"></jstl:out>
			</display:column>

			<jstl:set var="count" value="0"></jstl:set>
			<jstl:forEach items="${row.books}" var="book">
				<jstl:if test="${book.status.getName() == 'DENIED'}">
					<jstl:set var="count" value="${count+1}"></jstl:set>
				</jstl:if>
			</jstl:forEach>

			<spring:message code="dashboard.numberOfBookDenied"
				var="numberOfBookDenied" />
			<display:column title="${numberOfBookDenied}">
				<jstl:out value="${count}"></jstl:out>
			</display:column>
		</display:table>
		<br />
	</jstl:forEach>
</fieldset>

<br />

<fieldset>
	<legend>
		<b><spring:message
				code="dashboard.listPropertiesSortedAccordingNumberOfPendingBooksThatTheyHaveGot" /></b>
	</legend>

	<jstl:forEach items="${propertyOrderBookPending}" var="item">
		<h2>
			<jstl:out value="${item.key}"></jstl:out>
		</h2>


		<display:table name="${item.value}" id="row"
			requestURI="administrator/dashboardB" class="displaytag">

			<spring:message code="dashboard.property" var="property" />
			<display:column title="${property}">
				<jstl:out value="${row.name}"></jstl:out>
			</display:column>

			<jstl:set var="count" value="0"></jstl:set>
			<jstl:forEach items="${row.books}" var="book">
				<jstl:if test="${book.status.getName() == 'PENDING'}">
					<jstl:set var="count" value="${count+1}"></jstl:set>
				</jstl:if>
			</jstl:forEach>

			<spring:message code="dashboard.numberOfBookPending"
				var="numberOfBookPending" />
			<display:column title="${numberOfBookPending}">
				<jstl:out value="${count}"></jstl:out>
			</display:column>
		</display:table>
		<br />
	</jstl:forEach>
</fieldset>


