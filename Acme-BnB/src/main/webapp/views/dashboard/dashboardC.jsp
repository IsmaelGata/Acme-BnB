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
				code="dashboard.averageNumberofAcceptedAndDeniedRequestPerLessor" /></b>
	</legend>

	<jstl:set var="i" value="0"></jstl:set>
	<display:table name="averageLessor" id="row"
		requestURI="administrator/dashboardC" class="displaytag">
		<spring:message code="dashboard.statistics" var="statistics" />
		<display:column title="${statistics}">
			<jstl:choose>
				<jstl:when test="${i == 0}">
					<spring:message code="dashboard.avg.accepted" var="title" />
					<jstl:set var="i" value="1"></jstl:set>
				</jstl:when>
				<jstl:otherwise>
					<spring:message code="dashboard.avg.denied" var="title" />
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
				code="dashboard.averageNumberofAcceptedAndDeniedRequestPerTenant" /></b>
	</legend>

	<jstl:set var="i" value="0"></jstl:set>
	<display:table name="averageTenant" id="row"
		requestURI="administrator/dashboardC" class="displaytag">
		<spring:message code="dashboard.statistics" var="statistics" />
		<display:column title="${statistics}">
			<jstl:choose>
				<jstl:when test="${i == 0}">
					<spring:message code="dashboard.avg.accepted" var="title" />
					<jstl:set var="i" value="1"></jstl:set>
				</jstl:when>
				<jstl:otherwise>
					<spring:message code="dashboard.avg.denied" var="title" />
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
				code="dashboard.lessorsWithMoreAcceptedRequests" /></b>
	</legend>
	<display:table name="lessorsWithMoreAcceptedRequests" id="row"
		requestURI="administrator/dashboardC" class="displaytag" pagesize="5">
		<spring:message code="dashboard.name" var="name" />
		<display:column title="${name}">
			<jstl:out
				value="${row.name} ${row.surname} (${row.userAccount.username})"></jstl:out>
		</display:column>
	</display:table>
</fieldset>

<br />

<fieldset>
	<legend>
		<b><spring:message code="dashboard.lessorsWithMoreDeniedRequests" /></b>
	</legend>
	<display:table name="lessorsWithMoreDeniedRequests" id="row"
		requestURI="administrator/dashboardC" class="displaytag" pagesize="5">
		<spring:message code="dashboard.name" var="name" />
		<display:column title="${name}">
			<jstl:out
				value="${row.name} ${row.surname} (${row.userAccount.username})"></jstl:out>
		</display:column>
	</display:table>
</fieldset>

<br />

<fieldset>
	<legend>
		<b><spring:message code="dashboard.lessorsWithMorePendingRequests" /></b>
	</legend>
	<display:table name="lessorsWithMorePendingRequests" id="row"
		requestURI="administrator/dashboardC" class="displaytag" pagesize="5">
		<spring:message code="dashboard.name" var="name" />
		<display:column title="${name}">
			<jstl:out
				value="${row.name} ${row.surname} (${row.userAccount.username})"></jstl:out>
		</display:column>
	</display:table>
</fieldset>

<br />

<fieldset>
	<legend>
		<b><spring:message
				code="dashboard.tenantsWithMoreAcceptedRequests" /></b>
	</legend>
	<display:table name="tenantsWithMoreAcceptedRequests" id="row"
		requestURI="administrator/dashboardC" class="displaytag" pagesize="5">
		<spring:message code="dashboard.name" var="name" />
		<display:column title="${name}">
			<jstl:out
				value="${row.name} ${row.surname} (${row.userAccount.username})"></jstl:out>
		</display:column>
	</display:table>
</fieldset>

<br />

<fieldset>
	<legend>
		<b><spring:message code="dashboard.tenantsWithMoreDeniedRequests" /></b>
	</legend>
	<display:table name="tenantsWithMoreDeniedRequests" id="row"
		requestURI="administrator/dashboardC" class="displaytag" pagesize="5">
		<spring:message code="dashboard.name" var="name" />
		<display:column title="${name}">
			<jstl:out
				value="${row.name} ${row.surname} (${row.userAccount.username})"></jstl:out>
		</display:column>
	</display:table>
</fieldset>

<br />

<fieldset>
	<legend>
		<b><spring:message code="dashboard.tenantsWithMorePendingRequests" /></b>
	</legend>
	<display:table name="tenantsWithMorePendingRequests" id="row"
		requestURI="administrator/dashboardC" class="displaytag" pagesize="5">
		<spring:message code="dashboard.name" var="name" />
		<display:column title="${name}">
			<jstl:out
				value="${row.name} ${row.surname} (${row.userAccount.username})"></jstl:out>
		</display:column>
	</display:table>
</fieldset>

<br />

<fieldset>
	<legend>
		<b><spring:message
				code="dashboard.lessorsWithMaximumRatioOfApprovedRequests" /></b>
	</legend>
	<display:table name="lessorsWithMaximumRatioOfApprovedRequests"
		id="row" requestURI="administrator/dashboardC" class="displaytag"
		pagesize="5">
		<spring:message code="dashboard.name" var="name" />
		<display:column title="${name}">
			<jstl:out
				value="${row.name} ${row.surname} (${row.userAccount.username})"></jstl:out>
		</display:column>
	</display:table>
</fieldset>

<br />

<fieldset>
	<legend>
		<b><spring:message
				code="dashboard.lessorsWithMinimumRatioOfApprovedRequests" /></b>
	</legend>
	<display:table name="lessorsWithMinimumRatioOfApprovedRequests"
		id="row" requestURI="administrator/dashboardC" class="displaytag"
		pagesize="5">
		<spring:message code="dashboard.name" var="name" />
		<display:column title="${name}">
			<jstl:out
				value="${row.name} ${row.surname} (${row.userAccount.username})"></jstl:out>
		</display:column>
	</display:table>
</fieldset>

<br />

<fieldset>
	<legend>
		<b><spring:message
				code="dashboard.tenantsWithMaximumRatioOfApprovedRequests" /></b>
	</legend>
	<display:table name="tenantsWithMaximumRatioOfApprovedRequests"
		id="row" requestURI="administrator/dashboardC" class="displaytag"
		pagesize="5">
		<spring:message code="dashboard.name" var="name" />
		<display:column title="${name}">
			<jstl:out
				value="${row.name} ${row.surname} (${row.userAccount.username})"></jstl:out>
		</display:column>
	</display:table>
</fieldset>

<br />

<fieldset>
	<legend>
		<b><spring:message
				code="dashboard.tenantsWithMinimumRatioOfApprovedRequests" /></b>
	</legend>
	<display:table name="tenantsWithMinimumRatioOfApprovedRequests"
		id="row" requestURI="administrator/dashboardC" class="displaytag"
		pagesize="5">
		<spring:message code="dashboard.name" var="name" />
		<display:column title="${name}">
			<jstl:out
				value="${row.name} ${row.surname} (${row.userAccount.username})"></jstl:out>
		</display:column>
	</display:table>
</fieldset>

<br />

<fieldset>
	<legend>
		<b><spring:message
				code="dashboard.averageMaximumAndMinimunNumberOfResultPerFinder" /></b>
	</legend>

	<jstl:set var="i" value="0"></jstl:set>
	<display:table name="stadistictsPerFinder" id="row"
		requestURI="administrator/dashboardC" class="displaytag">
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
				</jstl:otherwise>
			</jstl:choose>

			<fmt:formatNumber var="value" value="${row}" maxFractionDigits="2"></fmt:formatNumber>
			<b><jstl:out value="${title}:"></jstl:out></b>
			<jstl:out value="${value}"></jstl:out>
		</display:column>
	</display:table>
</fieldset>

