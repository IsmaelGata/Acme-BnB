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

<display:table name="books" id="row" requestURI="${RequestURI}"
	pagesize="5">

	<spring:message code="book.checkIn" var="checkIn" />
	<display:column property="checkIn" title="${checkIn}" />

	<spring:message code="book.checkOut" var="checkOut" />
	<display:column property="checkOut" title="${checkOut}" />

	<spring:message code="book.smoker" var="smoker" />
	<display:column title="${smoker}">
		<jstl:if test="${row.smoker}">
			<spring:message code="book.smoker.true" var="smokerTrue" />
			<jstl:out value="${smokerTrue}"></jstl:out>
		</jstl:if>

		<jstl:if test="${!row.smoker}">
			<spring:message code="book.smoker.false" var="smokerFalse" />
			<jstl:out value="${smokerFalse}"></jstl:out>
		</jstl:if>
	</display:column>

	<jstl:if test="${cookie['language'].value.equals('en')}">
		<spring:message code="book.status" var="status" />
		<display:column title="${status}" property="status.name" />
	</jstl:if>
	<jstl:if test="${cookie['language'].value.equals('es')}">
		<spring:message code="book.status" var="status" />
		<display:column title="${status}" property="status.spanishName" />
	</jstl:if>

	<security:authorize access="hasRole('LESSOR')">

		<display:column>
			<jstl:if test="${not empty lessor.creditCard.brandName && not empty lessor.creditCard.number != null}">
				<jstl:if test="${row.status.name == 'PENDING'}">
					<acme:cancel
						url="book/changeStatus.do?bookId=${row.id}&value=ACEPTED"
						code="book.accept" />
				</jstl:if>
			</jstl:if>
		</display:column>
		<display:column>
			<jstl:if test="${row.status.name == 'PENDING'}">
				<acme:cancel
					url="book/changeStatus.do?bookId=${row.id}&value=DENIED"
					code="book.deny" />
			</jstl:if>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('TENANT')">
		<display:column>
			<jstl:if test="${row.status.name == 'ACCEPTED' && row.invoice == null}">
				<acme:cancel url="invoice/issueInvoice.do?bookId=${row.id}"
					code="book.invoice" />
			</jstl:if>
		</display:column>
		<display:column>
			<jstl:if test="${row.invoice != null}">
				<acme:cancel url="invoice/showInvoice.do?invoiceId=${row.invoice.id}"
					code="book.showInvoice" />
			</jstl:if>
		</display:column>
	</security:authorize>

</display:table>
