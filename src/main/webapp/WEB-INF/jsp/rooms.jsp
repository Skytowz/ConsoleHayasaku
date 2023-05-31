<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style/pnp.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Hayasaku and Pink - Rooms</title>
</head>
<body>
	<jsp:include page="/navbar"></jsp:include>
	<section class="main-content columns is-fullheight">
		<jsp:include page="/menu"></jsp:include>
		<main class="column container is-fluid">
			<h1 class="title">Rooms</h1>
			<c:if test="${ not empty noroomerror }">
				<p class="help is-warning">
					<c:out value="${noroomerror}" />
				</p>
			</c:if>
			<div class="columns is-multiline">
				<c:forEach items="${rooms}" var="room">
					<div class="column is-one-fifth">
						<div class="card">
							<div class="card-image">
								<figure class="image is-4by3">
									<img src="http://placekitten.com/1280/960"
										alt="Placeholder image" height="200px">
								</figure>
							</div>
							<div class="card-content">
								<div class="media-content">
									<p class="title"><c:out value="${fn:toUpperCase(fn:substring(room.name, 0, 1))}${fn:toLowerCase(fn:substring(room.name, 1,fn:length(room.name)))}" /></p>
									<p class="subtitle">
										
									</p>
								</div>
							</div>
							<footer class="card-footer">
								<p class="card-footer-item"><span> Population : <b><c:out value="${room.Hayasakuusers.size()}" /></b></span></p>
								<form:form action="room" method="GET" class="card-footer-item">
									<input type="hidden" name="roomname" value="<c:out value='${room.name}'/>" />
									<p class="card-footer-item"><span><input type="submit" value="Go" class="button" /></span></p>
								</form:form>
							</footer>
						</div>
					</div>
				</c:forEach>
			</div>
		</main>
	</section>
	<footer class="footer">
		<div class="content has-text-centered">
			<div class="columns">
				<div class="column">
					<p><strong>Hayasaku And Pink</strong> by Ruendan <span class="icon is-small"><img src="https://flagicons.lipis.dev/flags/4x3/fr.svg"></span> ! The source code is licensed by nothing yet.</p>
				</div>
				<div class="column is-2">
					<figure class="image"><img class="is-pulled-right" src="https://bulma.io/images/made-with-bulma.png" alt="Made with Bulma" width="128" height="24"></figure>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>