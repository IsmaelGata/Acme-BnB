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

<display:table name="socialIdentities" id="row" requestURI="${RequestURI}"
	pagesize="5">

	<spring:message code="socialIdentity.nick" var="nick" />
	<display:column property="nick" title="${nick}" />

	<spring:message code="socialIdentity.nameSocialNetwork" var="nameSocialNetwork" />
	<display:column property="nameSocialNetwork" title="${nameSocialNetwork}" />

	<spring:message code="socialIdentity.URL" var="URL" />
	<display:column property="URL" title="${URL}" />

	<display:column>
		<acme:cancel url="socialIdentity/edit.do?socialIdentityId=${row.id}" code="socialIdentity.edit"/>
	</display:column>
	
	<display:column>
		<acme:cancel url="socialIdentity/delete.do?socialIdentityId=${row.id}" code="socialIdentity.delete"/>
	</display:column>
	
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

<br/>

<acme:cancel url="socialIdentity/create.do" code="socialIdentity.create"/>
