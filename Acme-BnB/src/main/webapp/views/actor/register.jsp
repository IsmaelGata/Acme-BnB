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

<form:form action="${RequestURI}" modelAttribute="actorForm">
<%-- 	<security:intercept-url pattern="/banner/list.do" access="hasAnyRole('SPONSOR','ADMINISTRATOR')" /> --%>
	
	<fieldset >
		<legend><spring:message code="actor.personalDataDetails"/></legend>
		<br/>
		<acme:textbox code="actor.phone" path="phone" />
		<br/>
		<acme:textbox code="actor.email" path="email" />
		<br/>
		<acme:textbox code="actor.picture" path="picture" />
	</fieldset>
	<security:authorize access="hasRole('LESSOR')">
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
	 </security:authorize>
		<br/>
		
		<acme:submit code="actor.save" name="save"/>
		
		<acme:cancel code="actor.cancel" url="welcome/index.do"/>
		
		<br/>
		
	<h2><spring:message code="actor.socialIdentities" /></h2>		
		
	<display:table name="socialIdentities" id="row" requestURI="${SocialIdentitiesRequestURI}"
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

<jstl:if test="${correctMessage != null}">
	<spring:message code="${correctMessage}" var="correct" />
	<p><font size="4" color="green"><jstl:out value="${correct}"/></font></p>
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
		
</form:form>
