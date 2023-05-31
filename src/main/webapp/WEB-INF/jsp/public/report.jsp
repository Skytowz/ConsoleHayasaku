<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<security:authorize var="loggedIn" access="isAuthenticated()" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
<link rel="stylesheet" href="/style/utils.css">
<link rel="stylesheet" href="style/pnp.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta charset="UTF-8">
<title>Hayasaku and Pink - Report</title>
</head>
<body>
	<jsp:include page="/navbar"></jsp:include>
	<main class="container-fluid">
		<div class="section">
			<div class="columns is-fullheight">
				<section class="column is-2">
					<section class="box">
						<h3 class="title is-3">Bug Report</h3>
						<form:form action="newSuggestion" method="POST">
							<div class="field">
								<label class="label">Category</label>
								<div class="control has-icons-left">
									<div class="select">
										<select name="category" required="required">
											<option></option>
											<c:forEach var="c" items="${ categories }">
												<option><c:out value="${ c.name() }" /></option>
											</c:forEach>
										</select>
									</div>
									<span class="icon is-small is-left"> <i class="fas fa-location-arrow"></i></span>
								</div>
							</div>
							<div class="field">
								<label class="label">Title</label>
								<div class="control">
									<input name="title" class="input" type="text" placeholder="Title" required="required">
								</div>
							</div>
							<div class="field">
								<label class="label">Description</label>
								<div class="control">
									<textarea name="content" class="textarea" placeholder="Description" required="required"></textarea>
								</div>
							</div>
							<div class="field">
								<div class="control">
									<label class="checkbox" <c:if test="${ not loggedIn }">disabled="disabled" title="As you're not logged in, the ticket will be private" </c:if> > <input name="privateTicket" type="checkbox" <c:if test="${ not loggedIn }">disabled="disabled"</c:if> /> Private Ticket</label>
								</div>
							</div>
							<div class="field is-grouped">
								<div class="control">
									<button class="button is-link">Submit</button>
								</div>
							</div>
						</form:form>
					</section>
				</section>
				<section class="column is-fullheight">
					<nav class="panel is-fullheight">
						<div class="tabs is-fullwidth is-centered is-boxed">
							<ul>
								<li class="<c:if test="${ empty param.category }">is-active</c:if>" ><a href="/report">All</a></li>
								<c:forEach var="c" items="${ categories }">
									<li class="<c:if test="${ c.name().equalsIgnoreCase(param.category) }">is-active</c:if>"><a href="/report?category=<c:out value="${ c.name() }"/>">
										<span class="icon"><i class="<c:out value="${ c.fasClass }"/>" aria-hidden="true"></i></span>
										<span><c:out value="${ c.name() }"/></span>
									</a></li>
								</c:forEach>
							</ul>
						</div>
						
						<c:forEach var="b" items="${ bugs.toList() }">
							<a class="panel-block"> <span class="panel-icon"> <i
									class="<c:out value="${ b.category.fasClass }"/>" aria-hidden="true"></i>
							</span> <c:out value="${ b.title }"/>
							</a>
						</c:forEach>
						<c:if test="${ bugs.totalPages != 1 }">
							<nav class="panel-block pagination is-centered is-fullwidth"
								role="navigation" aria-label="pagination">
								<c:if test="${ bugs.number != 0 }"><a href="/report?pageNumber=<c:out value="${ bugs.number }"/>&category=<c:out value="${ param.category }"/>" class="pagination-previous">Previous</a></c:if>
								<c:if test="${ bugs.totalPages != bugs.number + 1 }"><a href="/report?pageNumber=<c:out value="${ bugs.number + 2 }"/>&category=<c:out value="${ param.category }"/>" class="pagination-next">Next page</a></c:if>
								<ul class="pagination-list">
									<c:if test="${ bugs.number >= 2 }"><li><a href="/report?pageNumber=<c:out value="${ 1 }"/>&category=<c:out value="${ param.category }"/>" class="pagination-link" aria-label="Goto page 1">1</a></li></c:if>
									<c:if test="${ bugs.number >= 3 }"><li><span class="pagination-ellipsis">&hellip;</span></li></c:if>
									<c:if test="${ bugs.number >= 1 }"><li><a href="/report?pageNumber=<c:out value="${ bugs.number }"/>&category=<c:out value="${ param.category }"/>" class="pagination-link" aria-label="Goto page <c:out value="${ bugs.number }"/>"><c:out value="${ bugs.number }"/></a></li></c:if>
									<c:if test="${ bugs.totalPages != 1 }"><li><a href="/report?pageNumber=<c:out value="${ bugs.number + 1 }"/>&category=<c:out value="${ param.category }"/>" class="pagination-link is-current" aria-label="Page <c:out value="${ bugs.number }"/>" aria-current="page"><c:out value="${ bugs.number + 1 }"/></a></li></c:if>
									<c:if test="${ bugs.number + 1 < bugs.totalPages }"><li><a href="/report?pageNumber=<c:out value="${ bugs.number + 2 }"/>&category=<c:out value="${ param.category }"/>" class="pagination-link" aria-label="Goto page <c:out value="${ bugs.number + 2 }"/>"><c:out value="${ bugs.number + 2 }"/></a></li></c:if>
									<c:if test="${ bugs.number + 3 < bugs.totalPages }"><li><span class="pagination-ellipsis">&hellip;</span></li></c:if>
									<c:if test="${ bugs.number + 2 < bugs.totalPages }"><li><a href="/report?pageNumber=<c:out value="${ bugs.totalPages }"/>&category=<c:out value="${ param.category }"/>" class="pagination-link" aria-label="Goto page <c:out value="${ bugs.totalPages }"/>"><c:out value="${ bugs.totalPages }"/></a></li></c:if>
								</ul>
							</nav>
						</c:if>
					</nav>
				</section>
			</div>
		</div>
	</main>
</body>
</html>