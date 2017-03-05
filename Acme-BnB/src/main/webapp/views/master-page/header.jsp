<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript">
		function relativeRedir(loc) {	
			var b = document.getElementsByTagName('base');
			if (b && b[0] && b[0].href) {
	  			if (b[0].href.substr(b[0].href.length - 1) == '/' && loc.charAt(0) == '/')
	    		loc = loc.substr(1);
	  			loc = b[0].href + loc;
			}
			window.location.replace(loc);
		}
	</script>

<div>
	
	<a href="/Acme-BnB"><img src="images/logo.png" alt="Acme-BnB." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMINISTRATOR')">
			<li><a href="extraAttribute/list.do"><spring:message code="master.page.extraAttribute" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.administrator.dashboard" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/dashboardC.do"><spring:message code="master.page.administrator.dashboradC" /></a></li>
					<li><a href="administrator/dashboardB.do"><spring:message code="master.page.administrator.dashboradB" /></a></li>
					<li><a href="administrator/dashboardA.do"><spring:message code="master.page.administrator.dashboradA" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="auditor/register.do"><spring:message code="master.page.auditor.register" /></a></li>
				</ul>
			</li>
			
			<li><a href="actor/edit.do"><spring:message code="master.page.edit.actor" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('LESSOR')">
			<li><a class="fNiv"><spring:message	code="master.page.lessor.book" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="book/list.do"><spring:message code="master.page.lessor.book.list" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('TENANT')">
			<li><a class="fNiv"><spring:message	code="master.page.tenant.finder" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="finder/configure.do"><spring:message code="master.page.tenant.finder.create" /></a></li>
				</ul>
			</li>
			<li><a href="book/listBooksTenant.do"><spring:message code="master.page.tenant.book.list" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="tenant/register.do"><spring:message code="master.page.tenant.register" /></a></li>
					<li><a href="lessor/register.do"><spring:message code="master.page.lessor.register" /></a></li>					
				</ul>
			</li>
		</security:authorize>

		
		<security:authorize access="permitAll">
			<li><a class="fNiv"><spring:message	code="master.page.property" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="property/list.do"><spring:message code="master.page.list.property" /></a></li>
					<security:authorize access="hasRole('LESSOR')">
						<li><a href="property/ownProperties.do"><spring:message code="master.page.property.ownProperty" /></a></li>
					</security:authorize>
				</ul>
			</li>
			
			
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/edit.do"><spring:message code="master.page.edit.actor" /></a></li>
					<li><a href="socialIdentity/list.do"><spring:message code="master.page.list.socialIdentity" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

