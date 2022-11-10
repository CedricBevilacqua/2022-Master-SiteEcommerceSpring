<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="_header.jsp" %>

	<div class="container p-5">
		<div class="card mx-auto" style="width:40%;box-shadow: 0 0px 25px -13px;border:0px;border-radius:25px;">
		  	<div class="card-body text-center">
		
			<h2 class="my-3">Se connecter</h2>
				<c:if test="${errorMessage != null}">
					<div class="alert alert-danger" role="alert"> ${errorMessage}</div>
				</c:if>
				<c:if test="${activationMessage != null}">
					<div class="alert alert-success" role="alert"> ${activationMessage}</div>
				</c:if>
				<c:if test="${mailUpdate != null}">
					<div class="alert alert-success" role="alert"> ${mailUpdate}</div>
				</c:if>
				<form:form action="login.html" method="post" modelAttribute="loggingClient" >
				<table border="0" cellpadding="7">
					<tr class="w-100">
						<td><form:label path="mail">Email</form:label></td>
						<td class="w-75"><form:input path="mail" required="required" class="form-control" /></td>
					</tr>
					<tr>
						<td><form:label path="password">Mot de passe</form:label></td>
						<td><form:password path="password" required="required" class="form-control" /></td>
					</tr>
				</table>		
				<div class="py-4">
					<input type="submit" value="Se connecter" class="btn btn-primary btn-lg">
				</div>
				</form:form>
				<hr>
			<h6 class="my-3">Nouveau client ?</h6>
				<div>
					<a id="create-an-account" class="btn btn-success btn-lg mb-3" href="<%= ctxPath %>/registration/signup.html"> Créer un compte </a>
				</div>
			</div>
		</div>
		
	</div>
	
<%@include file="_footer.jsp" %>
	