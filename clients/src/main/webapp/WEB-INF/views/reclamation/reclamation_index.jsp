<%@include file="../_header.jsp" %>

<div class="container">
<h1 class="my-5">Réclamations</h1>

<a href="create.html" role="button" class="btn btn-lg btn-primary m-3">Créer une réclamation</a>
<a href="listReclamations/${sessionScope.id_client}.html" role="button" class="btn btn-lg btn-primary">Liste de mes réclamations</a>

</div>
<%@include file="../_footer.jsp" %>