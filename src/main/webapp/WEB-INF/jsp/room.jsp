<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<security:authentication var="user" property="principal" />
<html>
<head>
<meta name="currentUser:id" content="<c:out value="${ user.id }"/>"/>
<meta name="currentUser:username" content="<c:out value="${ user.username }"/>"/>
<meta name="currentRoom:id" content="<c:out value="${ room.id }"/>"/>
<meta name="currentRoom:name" content="<c:out value="${ room.name }"/>"/>

<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
<link rel="stylesheet" href="style/room.css">
<link rel="stylesheet" href="style/pnp.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Hayasaku and Pink - <c:out value="${room.name}" /></title>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js



"></script>
</head>
<body class="is-fullheight">
	<jsp:include page="/navbar"></jsp:include>
	<section class="main-content columns is-fullheight">
		<jsp:include page="/menu"></jsp:include>
		<main class="column container-fluid is-max-widescreen is-fullheight is-fluid ">
			<section class="chat container is-fullheight">
				<section id="messages-container" class="message-list box is-fullheight">
					<c:forEach var="i" begin="1" end="3">
						<section class="columns message-item">
							<div class="column is-1">
									<figure class="image is-32x32">
										<img class="is-rounded" src="images/default_user.webp"
											alt="Placeholder image">
									</figure></div>
							<div class="column is-11">coucou</div>
						</section>
					</c:forEach>
				</section>
	            <div class="chat-box tile box is-child">
					<form class="form-horizontal" id="SendMessageForm">
						<fieldset>
							<div class="field has-addons">
								<div class="control">
									<input id="ChatRoomMessageInput" name="prependedtext-0"
										class="input " placeholder="Show them your lust ~ " type="text" autocomplete="off">
								</div>
								<div class="control">
									<button type="submit" id="ChatRoomSendBtn" class="button is-primary">
										<span class="icon is-medium"> <i class="fas fa-paper-plane"></i>
										</span>
									</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</section>
		</main>
	</section>
</body>
<script type="text/javascript" src="scripts/room.js"></script>
</html>