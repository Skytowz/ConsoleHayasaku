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

<link rel="stylesheet" href="style/hayasaku.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">

<script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
</head>
<body>
	<nav id="navbar" class="navbar is-dark" role="navigation" aria-label="main navigation">
		<div class="navbar-menu is-active">
			<div class="navbar-start">
				<a title="To Home" class="navbar-item" href="/home"><i class="fas fa-home"></i></a>
			</div>
			<div class="navbar-end">
				<a class="navbar-item signout-link" href="/logout"><span class="icon-text"><span class="icon"><i class="fas fa-sign-out-alt"></i></span><span>Disconnect</span></span></a>
			</div>
		</div>
	</nav>
</body>
<script type="text/javascript" src="scripts/navbar.js"></script>
</html>