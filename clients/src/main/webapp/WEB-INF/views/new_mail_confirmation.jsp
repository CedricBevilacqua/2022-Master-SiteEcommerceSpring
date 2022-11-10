<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="_header.jsp" %>

	<div class="container p-5 text-center">
		<div class="card align-center">
			<div class="card-header">Vous avez essayé de modifier votre adresse mail.</div>
			<div class="card-body">
				<h5 class="card-title">Valider votre nouvelle adresse mail</h5>
				<p class="card-text">À l'issue de la modification de votre adresse mail, un mail de confirmation vous est envoyé à la nouvelle adresse mail que vous avez indiquée. 
					
				</p>
				<p class="card-text">Cet email contient un lien permettant de mettre à jour votre adresse mail.
				</p>
				<p class="card-text">Votre adresse mail courante reste active tant que la nouvelle adresse n'a pas été confirmée.
				</p>
				<a href="<%= ctxPath %>/home/page.html" class="btn btn-primary">Retour à l'accueil</a>
			</div>
		</div>
	</div>


<%@include file="_footer.jsp" %>
