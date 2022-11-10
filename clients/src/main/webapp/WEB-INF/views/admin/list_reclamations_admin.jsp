<%@include file="../_header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1 class="m-5">Liste des réclamations</h1>

<c:choose>
	<c:when test="${empty reclamations }">
		Aucune réclamation
	</c:when>
	<c:otherwise>
	<div class="mx-5">
		<table class= "table w-50">
			<thead>
				<tr>
					<th>Titre</th>
					<th>Date</th>
					<th>Lien d'accès</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${reclamations}" var="reclamation">
				<tr>
					<td><c:out value="${reclamation.title}"></c:out></td>
					<td><fmt:formatDate pattern="d/M/YY" value="${reclamation.dateTime}"/></td>
					<td><a href="../reclamation/${reclamation.id}.html" role="button" class="btn btn-primary">Accéder à la réclamation</a></td>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</c:otherwise>
</c:choose>

<br/>

<a href="../admin/page.html" role="button" class="btn btn-primary mx-5">Retour</a>

<%@include file="../_footer.jsp" %>