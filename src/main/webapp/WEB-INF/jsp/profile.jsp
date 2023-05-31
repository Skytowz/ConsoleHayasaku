<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<security:authentication var="user" property="principal" />

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
<link rel="stylesheet" href="style/pnp.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>Purple and Pink - Profile</title>
<script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
<script defer src="scripts/profile.js"></script>
</head>
<body>
	<jsp:include page="/navbar"></jsp:include>
	<section class="main-content columns is-fullheight">
		<jsp:include page="/menu"></jsp:include>
		<main class="column container-fluid is-max-widescreen is-fullheight is-fluid ">
			<h1 class="title">Profile - <c:out value="${user.username}" /></h1>
			<div class="tabs is-boxed">
				<ul>
					<li class="is-active"><a> <span class="icon is-small"><i
								class="fas fa-info" aria-hidden="true"></i></span> <span>General</span>
					</a></li>
					<li><a> <span class="icon is-small"><i
								class="fas fa-comments" aria-hidden="true"></i></span> <span>Wall Post</span>
					</a></li>
					<li><a> <span class="icon is-small"><i
								class="fa-sharp fa-light fa-handcuffs" aria-hidden="true"></i></span> <span>Kinks</span>
					</a></li>
				</ul>
			</div>
			<div class="columns">
				<div class="column">
					
				</div>
				<c:forEach var="tagCategorie" items="${ tags }">
					<div class="column">
						<div class="tabs is-centered">
							<ul>
								<li><c:out value="${ tagCategorie.key.name }"/></li>
							</ul>
						</div>
						<c:forEach var="tag" items="${ tagCategorie.value }">
							<article class="purpletag message is-small is-<c:out value="${ tagCategorie.key.color }"/>">
								<div class="message-header"><c:out value="${ tag.name }"/></div>
								<div class="message-body">
									<p class="buttons">
										<button class="button is-small is-danger is-light rate">
											<span class="icon is-small"> <i class="fas fa-times"></i></span>
										</button>
										<button class="button is-small is-warning is-light rate">
											<span class="icon is-small"> <i class="fas fa-question"></i></span>
										</button>
										<button class="button is-small is-success is-light rate">
											<span class="icon is-small"> <i class="fas fa-check"></i></span>
										</button>
										<button class="button is-small is-success is-light rate">
											<span class="icon is-small"> <i class="fas fa-check-double"></i></span>
										</button>
									</p>
								</div>
							</article>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
		</main>
	</section>
</body>
</html>