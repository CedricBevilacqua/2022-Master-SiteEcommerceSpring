<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="../_header.jsp" %>

<div align="center">
	<h1>Commande confirmée.</h1>
	<div>
		Votre commande est désormais confirmée !<br/>
		
		<a href="<%=ctxPath%>/orders/history.html" role="button" class="btn btn-lg btn-primary">J'accède à mes commandes</a>
	</div>
</div>

<%@include file="../_footer.jsp" %>