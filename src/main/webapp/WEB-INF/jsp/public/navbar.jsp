<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<security:authorize var="loggedIn" access="isAuthenticated()" />
<security:authentication var="user" property="principal" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="style/pnp.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">

<link rel="stylesheet" href="style/bulma/bulma-calendar.min.css">
<link rel="stylesheet" href="style/calendar.css">
<script defer type="text/javascript" src="scripts/bulma/bulma-calendar.min.js"></script>
<script defer type="text/javascript" src="scripts/calendar.js"></script>
<script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
</head>
<body>
	<nav id="navbar" class="navbar is-dark" role="navigation" aria-label="main navigation">
		<div class="navbar-brand">
			<a class="navbar-item" href="/"><b style="color: #A569BD">P
			</b> <b> n </b> <b style="color: Fuchsia"> P</b>
			</a>
		</div>
		<div class="navbar-menu is-active">
			<div class="navbar-start">
				<a title="To Home" class="navbar-item" href="/home"><i class="fas fa-home"></i></a>
				<c:if test="${ loggedIn }">
					<a title="All Rooms" class="navbar-item" href="/rooms"><i class="fas fa-sign"></i></a>
					<a title="Calendar" class="navbar-item open-calendar"><i class="fas fa-calendar-alt"></i></a>
					<c:if test="${not empty user.currentRoom }">
						<a title="To Current Room" class="navbar-item" href="/room?roomname=<c:out value="${user.currentRoom.name}"/>">Current Room :
						<c:out value="${fn:toUpperCase(fn:substring(user.currentRoom.name, 0, 1))}${fn:toLowerCase(fn:substring(user.currentRoom.name, 1,fn:length(user.currentRoom.name)))}" /> </a>
					</c:if>
				</c:if>
			</div>
			<div class="navbar-end">
				<div class="navbar-item has-dropdown is-hoverable">
					<a class="navbar-link"></a>
					<div class="navbar-dropdown is-right">
						<a class="navbar-item" href="about"><span class="icon-text"><span class="icon"><i class="fas fa-info"></i></span><span>About</span></span></a>
						<a class="navbar-item" href="contact"><span class="icon-text"><span class="icon"><i class="fas fa-phone"></i></span><span>Contact</span></span></a>
						<hr class="navbar-divider">
						<c:if test="${ not loggedIn }"><a class="navbar-item open-login" title="Log In" href="/login" id="login-button"><span class="icon-text"><span class="icon"><i class="fas fa-sign-in-alt"></i></span><span>Log In</span></span></a></c:if>
						<c:if test="${ not loggedIn }"><a class="navbar-item open-register" title="Sign Up" href="/signup" id="signup-button"><span class="icon-text"><span class="icon"><i class="fas fa-file-signature"></i></span><span>Sign up</span></span></a></c:if>
						<c:if test="${ loggedIn }"><a class="navbar-item" title="Profile" href="/profile" id="profile-button"><span class="icon-text"><span class="icon"><i class="fas fa-user"></i></span><span>Profile</span></span></a></c:if>
						<c:if test="${ loggedIn }"><a class="navbar-item signout-link" href="/logout"><span class="icon-text"><span class="icon"><i class="fas fa-sign-out-alt"></i></span><span>Disconnect</span></span></a></c:if>
						<hr class="navbar-divider">
				
						<a class="navbar-item" href="report"><span class="icon-text"><span class="icon"><i class="fas fa-bug"></i></span><span>Report a Bug</span></span></a>
					</div>
				</div>
			</div>
		</div>
	</nav>
	<c:if test="${ loggedIn }">
		<div class="modal calendar-modal">
			<div class="modal-background"></div>
			<div class="modal-card">
				<header class="modal-card-head">
					<p class="modal-card-title">Events</p>
					<button class="delete" aria-label="close"></button>
				</header>
				<section class="modal-card-body calendar-body">
					<form:form id="event" action="event" method="post">
						<div class="field is-grouped">
							<div class="control is-expanded">
								<label class="label">Date</label>
								<input class="calendar" type="date" placeholder="Event Date..." name="event-date"/>
							</div>
							<div class="control">
								<label class="label">Time</label>
								<input class="calendar" type="time" placeholder="Event Date..." name="event-date"/>
							</div>
						</div>
						<div class="columns">
							<div class="column is-8">
							
							</div>
							<div class="column is-4">
								<aside class="menu">
									<p class="menu-label">Friends</p>
									<ul class="menu-list overflow-y">
										<c:forEach begin="0" end="50">
											<li><a>Friend</a></li>
										</c:forEach>
									</ul>
								</aside>
							</div>
						</div>
					</form:form>
				</section>
				<footer class="modal-card-foot"> </footer>
			</div>
		</div>
	</c:if>
	
	<!-- Login Modal -->
	<div class="modal login-modal">
		<div class="modal-background"></div>
		<div class="modal-card">
			<header class="modal-card-head">
				<p class="modal-card-title">Login</p>
				<button class="delete" aria-label="close"></button>
			</header>
			<section class="modal-card-body">
				<form:form id="login" action="login" method="post">
					<div class="field">
						<label class="label">Username</label>
						<div class="control has-icons-left">
							<input class="input" type="text" placeholder="Username..."
								name="username" /> <span class="icon is-small is-left"> <i
								class="fas fa-user"></i>
							</span>
						</div>
					</div>
					<div class="field">
						<label class="label">Password</label>
						<div class="control has-icons-left">
							<input class="input" type="password" placeholder="Password..."
								name="password" /> <span class="icon is-small is-left"> <i
								class="fas fa-key"></i>
							</span>
						</div>
					</div>
					<div class="field is-grouped">
						<p class="control">
							<button class="button is-primary" type="submit"> Login </button>
						</p>
						<p class="control">
							<a class="button is-light  open-register"> Register </a>
						</p>
					</div>
				</form:form>
			</section>
			<footer class="modal-card-foot"> </footer>
		</div>
	</div>
	
	<!-- Register Modal -->
	<div class="modal register-modal">
		<div class="modal-background"></div>
		<div class="modal-card">
			<header class="modal-card-head">
				<p class="modal-card-title">Login</p>
				<button class="delete" aria-label="close"></button>
			</header>
			<section class="modal-card-body">
				<form:form id="register" action="register" method="post">
					<div class="field">
						<label class="label" for="username">Username</label>
						<div class="control has-icons-left">
							<input class="input" type="text" placeholder="Username..."
								name="username" required="required" /> <span
								class="icon is-small is-left"> <i class="fas fa-user"></i>
							</span>
						</div>
						<c:if test="${ error == 'usernamealreadyused'}">
							<p class="help is-danger">This email is used</p>
						</c:if>
					</div>
	
					<div class="field">
						<label class="label">Password</label>
						<div class="control has-icons-left">
							<input class="input" type="password" placeholder="Password..."
								name="password" required="required" /> <span
								class="icon is-small is-left"> <i class="fas fa-key"></i>
							</span>
						</div>
						<c:if test="${ error == 'passwordtooweak'}">
							<p class="help is-danger">Password is too weak</p>
						</c:if>
						<p class="help is-warning">Password must be at least 8
							characters long, have an upper case character and a special
							character</p>
					</div>
					<div class="field">
						<label class="label">Confirm Password</label>
						<div class="control has-icons-left">
							<input class="input" type="password"
								placeholder="Confirm Password..." name="confirm_password"
								required="required" /> <span class="icon is-small is-left">
								<i class="fas fa-key"></i>
							</span>
						</div>
						<c:if test="${ error == 'passworddonotmatch'}">
							<p class="help is-danger">Passwords must match</p>
						</c:if>
					</div>
	
	
					<div class="field">
						<label class="label">Email</label>
						<div class="control has-icons-left has-icons-right">
							<input class="input" type="email" name="email"
								placeholder="Email..." required="required" /> <span
								class="icon is-small is-left"> <i class="fas fa-envelope"></i>
							</span>
						</div>
						<c:if test="${ error == 'emailinvalid'}">
							<p class="help is-danger">This email is invalid</p>
						</c:if>
						<c:if test="${ error == 'emailalreadyused'}">
							<p class="help is-danger">This email is used</p>
						</c:if>
					</div>
					<div class="control">
						<input class="button is-link" type="submit" value="Create Account">
					</div>
				</form:form>
			</section>
			<footer class="modal-card-foot"> </footer>
		</div>
	</div>
	<div class="is-hidden">
		<form:form id="signout-form" action="logout" method="post">
			<p class="control">
				<input type="submit" value="Disconnect" class="button" />
			</p>
		</form:form>
	</div>
</body>
<script type="text/javascript" src="scripts/navbar.js"></script>
</html>