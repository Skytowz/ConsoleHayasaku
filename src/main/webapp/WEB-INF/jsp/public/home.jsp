<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
<link rel="stylesheet" href="style/utils.css">
<link rel="stylesheet" href="style/pnp.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta charset="UTF-8">
<security:authorize var="loggedIn" access="isAuthenticated()" />
<title>Purple and Pink - Home</title>
</head>
<body>
	<jsp:include page="/navbar"></jsp:include>
	<section class="main-content columns is-fullheight">
		<jsp:include page="/menu"></jsp:include>
		<main class="column container-fluid is-max-widescreen is-fullheight is-fluid ">
			<section class="container">
				<h2 class="title is-2">Purple and Pink</h2>
				<c:if test="${not empty param.error}">
					<div class="notification is-danger is-light">This Username /
						Password combinaison do not exist.</div>
				</c:if>
				<section>
					<section class="columns">
						<section class="column is-one-third">
							<section class="box">
								<section>
									<article class="block">
										<div class="content">
											<h1>What's PnP ?</h1>
											<p>
												Lorem ipsum<sup><a>[1]</a></sup> dolor sit amet, consectetur
												adipiscing elit. Nulla accumsan, metus ultrices eleifend
												gravida, nulla nunc varius lectus, nec rutrum justo nibh eu
												lectus. Ut vulputate semper dui. Fusce erat odio, sollicitudin
												vel erat vel, interdum mattis neque. Sub<sub>script</sub> works
												as well!
											</p>
										</div>
									</article>
									<c:if test="${ not loggedIn }">
										<article class="block">								
											<div class="field is-grouped">
												<div class="control is-expanded open-login">
													<button type="submit" class="button is-medium">Log In</button>
												</div>
												<div class="control open-register">
													<button type="submit" class="button is-small">Sign In</button>
												</div>
											</div>
										</article>
									</c:if>
								</section>
							</section>
						</section>
						<section class="column">
							<section class="box container">
								<c:forEach var="i" begin="1" end="4">
									<div class="tile is-ancestor ">
										<c:forEach var="j" begin="1" end="5">
											<div class="tile is-parent">
												<div class="tile is-child">
													<div title="User<c:out value="${i}"/><c:out value="${j}"/>" class="card is-mobile is-centered is-vcentered">
														<div class="card-image">
															<figure class="image is-rounded">
																<img src="http://placekitten.com/128/128"
																	alt="Placeholder image">
															</figure>
														</div>
														<div class="is-overlay is-flex is-align-items-flex-end">
															<div class="tags is-flex">
																<span class="tag is-normal is-rounded is-danger">5</span>
															</div>
														</div>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>
								</c:forEach>
							</section>
						</section>
					</section>
				</section>
			</section>
		</main>
	</section>
	<footer class="footer">
		<div class="content has-text-centered">
			<div class="columns">
				<div class="column">
					<p><strong>Purple And Pink</strong> by Ruendan <span class="icon is-small"><img src="https://flagicons.lipis.dev/flags/4x3/fr.svg"></span> ! The source code is licensed by nothing yet.</p>
				</div>
				<div class="column is-2">
					<figure class="image"><img class="is-pulled-right" src="https://bulma.io/images/made-with-bulma.png" alt="Made with Bulma" width="128" height="24"></figure>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>