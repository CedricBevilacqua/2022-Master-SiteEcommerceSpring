<%@ page contentType="text/html; charset=UTF-8"%>	
<%@include file="_header.jsp" %>
<!-- 
<div class="d-flex justify-content-center">
	<div class="row">
		<h1>ERREUR !</h1>	
	</div>
	<c:if test="${errorMessage != null}">
		<div class="row">
			<div> ${errorMessage}</div>
		</div>
	</c:if>
	<div class="row">
		<a role="button" class="btn btn-danger" href="<%=ctxPath%>/home/page.html">Retour au site</a>
	</div>
</div>-->
<div class="row">
	<div class="d-flex align-items-center justify-content-center">
		<h2 class="align-middle">
			Erreur !
		</h2>
	</div>
</div>
<c:if test="${errorMessage != null}">
	<div class="row">
		<div class="d-flex align-items-center justify-content-center">
			<div> ${errorMessage}</div>
		</div>
	</div>
</c:if>
<div class="row">
	<div class="d-flex align-items-center justify-content-center">
		<a role="button" class="btn btn-danger" href="<%=ctxPath%>/catalog/categories.html">Retour au site</a>
	</div>
</div>

<%@include file="_footer.jsp" %>
