<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Hayasaku and Pink - Register</title>
<script defer
	src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
</head>
<body>
	<jsp:include page="/navbar"></jsp:include>
	<main class="container">
		<h1 class="title">Register</h1>
		<section class="box">
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
	</main>
</body>
<script src="scripts/register.js" type="text/javascript"></script>
</html>