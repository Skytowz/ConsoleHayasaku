<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<security:authorize var="loggedIn" access="isAuthenticated()" />
<security:authentication var="user" property="principal" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
<link rel="stylesheet" href="style/menu.css">
<script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
<script defer src="scripts/menu.js"></script>
<title>Menu</title>
</head>
<body>
	<aside id="side-menu" class="column has-background-light is-2 is-narrow-mobile is-fullheight is-hidden-mobile">
		<div id="side-menu-tabs" class="tabs is-small is-fullwidth is-centered is-fullheight">
			<ul>
				<c:if test="${ not empty room }">
					<li title="Users in current room" class="is-active"><a data-content="all-users"> <span class="icon"><i
								class="fas fa-users" aria-hidden="true"></i></span> <span></span>
					</a></li>
				</c:if>
				<li title="Friends" class=""><a data-content="friendlist"> <span class="icon"><i
							class="fas fa-user-friends" aria-hidden="true"></i></span> <span></span>
				</a></li>
				<li title="All Rooms" class="<c:if test="${ empty room }">is-active</c:if>"><a data-content="all-rooms"> <span class="icon"><i
							class="fas fa-map-marker" aria-hidden="true"></i></span> <span></span>
				</a></li>
				<li title="Search"><a data-content="search"> <span class="icon"><i
							class="fas fa-search" aria-hidden="true"></i></span> <span></span>
				</a></li>
				<li id="hide-menu-li" title="Hide Menu"><a id="hide-menu"> <span class="icon"><i 
							class="fas fa-bars" aria-hidden="true"></i></span> <span></span>
				</a></li>
			</ul>
		</div>
		<section id="side-menu-text" class="has-text-centered">
			<c:if test="${ not empty room }">
				<div id="all-users-content" class="menu-content container">
					<div class="notification is-info">
						<button class="delete"></button>
						<u><b>Rules :</b></u> <c:out value="${ room.rules }"/>
					</div>
					<h3 class="subtitle is-5">Users in Room</h3>
					<c:forEach items="${room.purpleusers}" var="pu">
						<c:out value="${pu.username}" />
					</c:forEach>
				</div>
			</c:if>
			<div id="all-rooms-content" class="menu-content container <c:if test="${ not empty room }">is-hidden</c:if>">
				<article class="">
				<h3 class="subtitle is-5">All Rooms</h3>
					<div class="panel-block">
						<p class="control has-icons-left">
							<input class="input is-info" id="searchRoomInput" type="text" placeholder="Search Room">
							<span class="icon is-left"> <i class="fas fa-search" aria-hidden="true"></i></span>
						</p>
					</div>
					<c:forEach items="${rooms}" var="r">
						<a href="/room?roomname=<c:out value="${r.name}" />" class="panel-block is-active room-panel-block" data-roomname="<c:out value="${r.name}" />">
							<span class="panel-icon">
								<c:if test="${r.official}"><span title="Official Room" class="icon is-right"><i class="fas fa-star"></i></span></c:if>
							</span>
							<c:out value="${r.name}" /> (<c:out value="${r.purpleusers.size()}" />)
						</a>
					</c:forEach>
				</article>
			</div>
			<div id="friendlist-content" class="menu-content container is-hidden">
				<!-- TODO : FriendRequests -->
				<c:if test="${ not empty friends }">
					<div class="box">
						<h6 class="subtitle is-5"><span class="icon"><i class="fas fa-user-plus"></i></span> Friend Requests</h6>
						<c:forEach items="${friends}" var="pu">
							<c:out value="${pu.username}" />
						</c:forEach>
					</div>
				</c:if>
				<div class="box">
					<h6 class="subtitle is-5"><span class="icon"><i class="fas fa-user-friends"></i></span> Friends</h6>
					<c:forEach items="${friends}" var="pu">
						<c:out value="${pu.username}" />
					</c:forEach>
				</div>
			</div>
			<div id="search-content" class="menu-content container is-hidden">
				<h3 class="subtitle is-5">Search</h3>
				<c:forEach items="${room.purpleusers}" var="pu">
					<c:out value="${pu.username}" />
				</c:forEach>
			</div>
		</div>
	</aside>
</body>
</html>