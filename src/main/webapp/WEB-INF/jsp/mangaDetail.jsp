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
<script type="text/javascript" src="/scripts/mangaDetail.js" defer></script>


</head>
<body class="is-fullheight">
	<jsp:include page="/navbar"></jsp:include>
	<section class="main-content columns is-fullheight">
		<main class="column container-fluid is-max-widescreen is-fullheight is-fluid ">	
			<nav class="breadcrumb is-left mt-1 ml-2" aria-label="breadcrumbs">
			  <ul>
			    <li><a class="has-text-white" href="/home">Home</a></li>
			    <li><a class="has-text-white" href="/guild/<c:out value="${ currentGuild.id	 }"/>"><c:out value="${ currentGuild.name }"></c:out> </a></li>
			    <li><a class="has-text-white" href="/guild/<c:out value="${ currentGuild.id	 }"/>/manga">Manga</a></li>
			    <c:choose>
			    	<c:when test="${ mode eq  'update' }">
					    <li><a class="has-text-white" href="#"><c:out value="${ manga.name }"/></a></li>
			    	</c:when>
			    	<c:when test="${ mode eq  'create' }">
					    <li><a class="has-text-white" href="#">Nouveau</a></li>
			    	</c:when>
			    </c:choose>
			  </ul>
			</nav>
			<section class="container">
				<h2 class="title is-2 has-text-white"><c:out value="${ empty manga.name ? 'Nouveau' : manga.name }"/></h2>
				<div class="columns is-flex is-justify-content-center">
					<div class="column is-two-thirds">
						<c:if test="${ not empty error }">
							<div class="box has-background-discord has-border-danger has-text-white"><c:out value="${ error }"></c:out></div>
						</c:if>
						<form:form class="has-text-white" method="post" action="/guild/${ currentGuild.id }/manga/${ mode }">
							<input type="hidden" name="mode" value="<c:out value="${ mode }"/>"/>
							<input type="hidden" name="type" value=1 />
							<c:if test='${ mode eq  "update" }'>
								<input type="hidden" name="commandId" value="<c:out value="${ manga.commandId }" />"/>
								<input type="hidden" name="id" value="<c:out value="${ manga.id }" />"/>
							</c:if>
							<div class="block" id="form">
								<div class="block has-background-discord">
									<label class="label has-text-white has-text-centered">Nom</label>
									<div class="columns">
										<div class="control column is-two-thirds is-offset-2">
									    	<input <c:if test='${ mode ne "create"}'><c:out value="disabled='disabled'"/></c:if> class="input has-text-light has-background-discord" type="text" placeholder="Nom" name="name" value="<c:out value="${ manga.name }"/>"/>
										</div>
									</div>
								</div>
								<div class="block has-background-discord">
									<label class="label has-text-white has-text-centered">Description</label>
									<div class="columns">
										<div class="control column is-two-thirds is-offset-2">
									    	<input <c:if test='${ mode ne "create"}'><c:out value="disabled='disabled'"/></c:if> class="input has-text-light has-background-discord" type="text" placeholder="Description" name="description" value="<c:out value="${ manga.description }"/>"/>
										</div>
									</div>
								</div>
								<div class="block has-background-discord">
									<label class="label has-text-white has-text-centered">ID Mangadex</label>
									<div class="columns">
										<div class="control column is-two-thirds is-offset-2">
									    	<input <c:if test='${ mode ne "create"}'><c:out value="disabled='disabled'"/></c:if> class="input has-text-light has-background-discord" type="text" placeholder="ID Mangadex" name="idManga" value="<c:out value="${ manga.idManga }"/>"/>
										</div>
									</div>
								</div>
								<div class="block has-background-discord">
									<label class="label has-text-white has-text-centered">Commande</label>
									<div class="columns">
										<div class="control column is-two-thirds is-offset-2">
									    	<input <c:if test='${ mode ne "create"}'><c:out value="disabled='disabled'"/></c:if> class="input has-text-light has-background-discord" type="text" placeholder="Trigger" name="trigger" value="<c:out value="${ manga.trigger }"/>"/>
										</div>
									</div>
								</div>
							</div>
							<div class="columns">
								<div class="column is-four-fifths is-offset-1 is-flex is-justify-content-flex-end mt-5">
									<c:choose>
										<c:when test='${ mode eq "update" }'>
											<input type="hidden" name="cancel" class="button is-danger" value="Annuler">
											<input type="hidden" name="send" class="button is-link" value="Enregistrer">
											<input type="button" name="edit" class="button is-link" value="Modifier">
										</c:when>
										<c:when test='${ mode eq "create" }'>
											<input type="button" name="send" class="button is-link" value="Enregistrer">
										</c:when>
									</c:choose>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</section>
		</main>
	</section>
</body>
</html>