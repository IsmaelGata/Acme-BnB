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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="properties" id="row" requestURI="${RequestURI}"
	pagesize="5">

	<spring:message code="property.name" var="name" />
	<display:column property="name" title="${name}" />

	<spring:message code="property.rate" var="rate" />
	<display:column property="rate" title="${rate}" />

	<spring:message code="property.description" var="description" />
	<display:column property="description" title="${description}" />

	<spring:message code="property.address" var="address" />
	<display:column property="address" title="${address}" />
	
	<spring:message code="property.address" var="books" />
	<display:column title="books" sortable="true">
		<jstl:out value="${fn:length(row.books)}"></jstl:out>
	</display:column>

	<display:column>
		<acme:cancel url="property/moreInfo.do?propertyId=${row.id}" code="property.moreInfo"/>
	</display:column>
	
	<display:column>
		<acme:cancel url="lessor/show.do?lessorId=${row.lessor.id}" code="property.lessor"/>
	</display:column>
	<security:authorize access="hasRole('TENANT')">
		<display:column>
			<acme:cancel url="book/create.do?propertyId=${row.id}" code="property.book.create"/>
		</display:column>
	</security:authorize>
	<security:authorize access="isAuthenticated()">
		<display:column>
			<acme:cancel url="audit/list.do?propertyId=${row.id}" code="property.audit.list"/>
		</display:column>
	</security:authorize>
	<security:authorize access="hasRole('AUDITOR')">
		<display:column>
			<jstl:set var="demon" value="false"/>
			<jstl:forEach items="${row.audits}" var="audit">
				<jstl:if test="${audit.auditor.id == auditorId}">
					<jstl:set var="demon" value="true"/>
				</jstl:if>
			</jstl:forEach>
			<jstl:if test="${demon == false}">
				<acme:cancel url="audit/create.do?propertyId=${row.id}" code="property.audit.create"/>
			</jstl:if>
		</display:column>
	</security:authorize>
	<jstl:if test="${lessorId == row.lessor.id}">
		<display:column>
			<jstl:if test="${fn:length(row.books) >= 0}">
					<acme:cancel url="book/booksByPorperty.do?propertyId=${row.id}" code="property.books"/>
			</jstl:if>
		</display:column>
		<display:column>
			<jstl:if test="${lessorId == row.lessor.id}">
				<acme:cancel url="property/edit.do?propertyId=${row.id}" code="property.edit"/>
			</jstl:if>
		</display:column>
		<display:column>
			<jstl:if test="${fn:length(row.books) <= 0}">
					<acme:cancel url="property/delete.do?propertyId=${row.id}" code="property.delete"/>
			</jstl:if>
		</display:column>
		<display:column>
			<jstl:if test="${fn:length(row.books) >= 0}">
					<acme:cancel url="tenant/listByLessor.do?lessorId=${row.lessor.id}" code="property.tenant"/>
			</jstl:if>
		</display:column>
	</jstl:if>
	
</display:table>

<jstl:if test="${editErrorMessage != null}">
	<spring:message code="${editErrorMessage}" var="editErrorM" />
	<p><font size="4" color="red"><jstl:out value="${editErrorM}"/></font></p>
	<br/>
</jstl:if>
<jstl:if test="${deleteErrorMessage != null}">
	<spring:message code="${deleteErrorMessage}" var="deleteErrorM" />
	<p><font size="4" color="red"><jstl:out value="${deleteErrorM}"/></font></p>
	<br/>
</jstl:if>

<jstl:if test="${createErrorMessage != null}">
	<spring:message code="${createErrorMessage}" var="createErrorM" />
	<p><font size="4" color="red"><jstl:out value="${createErrorM}"/></font></p>
	<br/>
</jstl:if>

<jstl:if test="${createAuditError != null}">
	<spring:message code="${createAuditError}" var="createAuditErr" />
	<p><font size="4" color="red"><jstl:out value="${createAuditErr}"/></font></p>
	<br/>
</jstl:if>

<br/>
<security:authorize access="hasRole('LESSOR')">
		<acme:cancel url="property/create.do" code="property.create"/>
	</security:authorize>
