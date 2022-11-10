<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="_header.jsp" %>


	<div class="container p-5 text-center">
		<div class="card align-center">
			<div class="card-header">Merci de vous être inscrit au WEBDRIVE.</div>
			<div class="card-body">
				<h5 class="card-title">Valider votre compte</h5>
				<p class="card-text">À l'issue de la création de votre compte, un email de confirmation vous est envoyé à l'adresse mail que vous avez indiquée. 
					
				</p>
				<p class="card-text">Cet email contient un lien permettant de terminer le processus de validation de votre compte.
				</p>
				<a href="<%= ctxPath %>/home/page.html" class="btn btn-primary">Retour à l'accueil</a>
			</div>
		</div>
	</div>


<%@include file="_footer.jsp" %>
