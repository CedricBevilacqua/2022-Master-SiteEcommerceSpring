<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="_header.jsp"%>

<div class="container p-5 text-center w-50">
<div class="card text-center">
	<div class="card-header"></div>
	<div class="card-body">
		<h5 class="card-title">${errorMessage}</h5>

	</div>
	<div class="card-footer text-muted"></div>
	<c:choose>
		<c:when test="${(!empty expired) && (!empty accountActivation)}">
			<form action="send-new-confirmation-mail.html" method="post">
				<input name="id_client" value="${savedClient.id}" hidden="hidden">
				<input id="demander un nouveau lien" class="btn btn-primary my-2"
					type="submit" value="Demander un nouveau lien"></input>
			</form>
		</c:when>
	</c:choose>
</div>
</div>

<%@include file="_footer.jsp"%>