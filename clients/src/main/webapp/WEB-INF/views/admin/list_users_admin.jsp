<%@include file="../_header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1 class="m-5">Liste des utilisateurs</h1>

<c:choose>
	<c:when test="${empty clients }">
		Aucun client
	</c:when>
	<c:otherwise>
		<div class="mx-5">
		<table class="table w-50">
			<thead>
				<tr>
					<th>Nom</th>
					<th>Prénom</th>
					<th>Mail</th>
					<th>Bannissement</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${clients}" var="client">
				<tr>
					<td><c:out value="${client.lastname}"></c:out></td>
					<td><c:out value="${client.firstname}"></c:out></td>
					<td><c:out value="${client.mail}"></c:out></td>
						<c:set var="banned" value="false"/>
						<c:set var="banId" value=""/>
						<c:if test="${not empty banished}">
							<c:forEach items="${banished}" var="ban">
								<c:if test="${ban.client.id == client.id}">
									<c:set var="banned" value="true"/>
									<c:set var="banId" value="${ban.id}"/>
								</c:if>
							</c:forEach>
						</c:if>
						<c:choose>
							<c:when test="${banned}">
								<td>Banni</td>
								<td><a href="unban/${banId}.data" role="button" class="btn btn-success">Débannir l'utilisateur</a></td>
							</c:when>
							<c:otherwise>
								<td>Non banni</td>
								<td><a href="ban/${client.id}.data" role="button" class="btn btn-danger">Bannir l'utilisateur</a></td>
							</c:otherwise>
						</c:choose>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</c:otherwise>
</c:choose>

<br/>

<a href="../admin/page.html" role="button" class="btn btn-primary mx-5">Retour</a>

<%@include file="../_footer.jsp" %>