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

<display:table name="audits" id="row" requestURI="${RequestURI}"
	pagesize="5">

	<spring:message code="audit.moment" var="moment" />
	<display:column property="moment" title="${moment}" />

	<spring:message code="audit.text" var="text" />
	<display:column property="text" title="${text}" />
	
	<spring:message code="audit.attachments" var="attachments" />
	<display:column property="attachments" title="${attachments}" />

	<security:authorize access="hasRole('AUDITOR')">
		<display:column>
			<jstl:if test="${row.draft == true && auditorId == row.auditor.id}">
				<acme:cancel url="audit/edit.do?auditId=${row.id}" code="audit.edit"/>
			</jstl:if>
		</display:column>
		
		<display:column>
			<jstl:if test="${row.draft == true && auditorId == row.auditor.id}">
				<acme:cancel url="audit/delete.do?auditId=${row.id}" code="audit.delete"/>
			</jstl:if>
		</display:column>
	</security:authorize>

</display:table>

<jstl:if test="${editErrorMessage != null}">
	<br/>
	<spring:message code="${editErrorMessage}" var="editErrorM" />
	<p><font size="4" color="red"><jstl:out value="${editErrorM}"/></font></p>
</jstl:if>

<jstl:if test="${editErrorMessage != null}">
	<br/>
	<spring:message code="${deleteErrorMessage}" var="deleteErrorM" />
	<p><font size="4" color="red"><jstl:out value="${deleteErrorM}"/></font></p>
</jstl:if>
