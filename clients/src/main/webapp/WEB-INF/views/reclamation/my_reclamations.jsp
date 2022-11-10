<%@include file="../_header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
<h1 class="my-5">Mes réclamations</h1>

<c:choose>
	<c:when test="${empty reclamationsUser }">
		Aucune réclamation
	</c:when>
	<c:otherwise>
	
		<table class="table">
			<thead>
				<tr>
					<th>Titre</th>
					<th>Date</th>
					<th>Lien d'accès</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${reclamationsUser}" var="reclamation">
				<tr>
					<td><c:out value="${reclamation.title}"></c:out></td>
					<td><fmt:formatDate pattern="d/M/YY" value="${reclamation.dateTime}"/></td>
					<td><a href="../../reclamation/${reclamation.id}.html" role="button" class="btn btn-primary">Accéder à la réclamation</a></td>
				</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>
<br>
<a href="../../reclamation/page.html" role="button" class="btn btn-primary mt-3">Retour</a>
</div>
<%@include file="../_footer.jsp" %>