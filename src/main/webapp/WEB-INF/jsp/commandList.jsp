<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/style/utils.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta charset="UTF-8">
<security:authorize var="loggedIn" access="isAuthenticated()" />
<jsp:useBean id="sakuBot" class="com.hayasaku.console.entity.DiscordUser" scope="application"></jsp:useBean>
<title>Hayasaku - Home</title>
<script type="text/javascript" src="/scripts/global.js" defer></script>

</head>
<body class="is-fullheight">
	<jsp:include page="/navbar"></jsp:include>
	<section class="main-content columns is-fullheight">
		<main class="column container-fluid is-max-widescreen is-fullheight is-fluid ">	
			<nav class="breadcrumb is-left mt-1 ml-2" aria-label="breadcrumbs">
			  <ul>
			    <li><a class="has-text-white" href="/home">Home</a></li>
			    <li><a class="has-text-white" href="/guild/<c:out value="${ currentGuild.id	 }"/>"><c:out value="${ currentGuild.name }"></c:out> </a></li>
			    <li><a class="has-text-white" href="#"><c:out value="${ type.typeName }"/></a></li>
			  </ul>
			</nav>
			<section class="container">
				<h2 class="title is-2 has-text-white">Select category</h2>
				<div class="columns is-flex is-justify-content-center">
					<div class="column is-two-thirds">
						<c:if test="${ not empty error }">
							<div class="box has-background-discord has-border-danger has-text-white"><c:out value="${ error }"></c:out></div>
						</c:if>
						<form:form name="guild" method="get" action="/guild/${ currentGuild.id }/${ type.typeName.toLowerCase() }/new">
							<div class="box has-background-link is-hover-darker is-clickable is-hover-up mt-3 submit-form">
								<p class="is-size-4 has-text-white has-text-centered">Nouveau</p>
							</div>
						</form:form>
						<c:if test="${ not empty commands }">
							<c:forEach items="${ commands }" var="command">
								<form:form name="guild" method="get" action="/guild/${ currentGuild.id }/${ type.typeName.toLowerCase() }/${ command.id }/${ command.name.toLowerCase() }">
									<div class="box has-background-grey-darker is-hover-darker is-clickable is-hover-up mt-3 submit-form">
										<p class="is-size-4 has-text-white has-text-centered"><c:out value="${ command.name } : "/></p>
										<p class="is-size-6 has-text-white has-text-centered"><c:out value="${ command.description }"></c:out></p>
									</div>
								</form:form>
							</c:forEach>
						</c:if>
					</div>
				</div>
			</section>
		</main>
	</section>
</body>
</html>