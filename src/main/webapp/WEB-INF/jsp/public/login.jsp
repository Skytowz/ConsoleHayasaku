<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>Purple and Pink - Login</title>
<script defer
	src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
</head>
<body>
	<jsp:include page="/navbar"></jsp:include>
	<main class="container">
		<h1 class="title">Login</h1>
		<section class="box">
			<c:if test="${not empty param.error}">
				<div class="notification is-danger is-light">This Username /
					Password combinaison do not exist.</div>
			</c:if>
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
			</form:form>
			<section class="field is-grouped">
				<div class="control">
					<input class="button is-link is-medium input" type="submit"
						form="login" value="Login">
				</div>
				<form:form action="register" method="get" class="column is-full">
					<div class="control">
						<input class="button is-link is-small is-outlined" type="submit"
							value="Register">
						<p class="help">Not Registered yet ?</p>
					</div>
				</form:form>
			</section>
		</section>
	</main>
</body>
</html>