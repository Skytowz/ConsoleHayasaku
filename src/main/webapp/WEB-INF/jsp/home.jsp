<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style/utils.css">
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
			    <li><a class="has-text-white" href="#">Home</a></li>
			  </ul>
			</nav>
			<section class="container">
				<h2 class="title is-2 has-text-white">Select Server</h2>
				<div class="columns is-flex is-justify-content-center">
					<c:choose>
						<c:when test="${ not empty user.guilds }">
							<c:forEach items="${ user.guilds }" var="guild">
								<div class="column is-one-quarter is-hover-up is-hover-darker is-clickable">
									<form:form name="guild" method="get" action="/guild/${ guild.id }">
										<div class="card has-background-grey-darker submit-form">
											<div class="card-image">
												<figure class="image is-square">
											      <img src='<c:out value="${ guild.urlIcon }"/>?size=512' alt='<c:out value="${ guild.name }_icon"></c:out>'>
											    </figure>
											</div>
											<div class="card-content has-background-grey-darker">
										    	<div class="content">
											      	<p class="has-text-white has-text-centered"><c:out value="${ guild.name }"></c:out></p>
											    </div>
										    </div>
										</div>
									</form:form>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="block">
								<p class="has-text-white is-size-4">You don't have any server with <c:out value="${ sakuBot.username }"></c:out></p>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</section>
		</main>
	</section>
</body>
</html>