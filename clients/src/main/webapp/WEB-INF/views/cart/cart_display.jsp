<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<%@include file="../_header.jsp" %>
<div class="col-md-5 mx-auto">

<c:choose>
<c:when test="${cartSize eq 0}">
	<div class="d-flex justify-content-center my-auto">
		<div><h2>Votre panier est vide.</h2></div>
	</div>
</c:when>
<c:otherwise>
	<h4 class="d-flex justify-content-between align-items-center">
		<span>Votre panier</span>
	</h4>
	<ul class="list-group">
	
	<!-- display all cart items -->
	<c:forEach items="${cartContent}" var="entry">
		<li class="list-group-item">
			<div class="d-flex justify-content-between">
				<div>
					<span>x${entry.value} <c:out value="${entry.key.name}"/></span>
					<span><button id="${entry.key.id}" type="button" class="btn btn-danger btn-sm delete-from-cart"><i class="fa fa-trash" aria-hidden="true"></i></button></span>
				</div>
				 <f:formatNumber value="${entry.key.price * entry.value / 100}"
				type="currency"
				currencySymbol="€"
				pattern="0.00¤"
				var="price"/>
				<span>${price}</span>
			</div>
		</li>
	</c:forEach>
	
	<!-- display total price and validate button -->
		<li class="list-group-item">
			<div class="d-flex justify-content-between">
				<f:formatNumber value="${totalPrice / 100}"
				type="currency"
				currencySymbol="€"
				pattern="0.00¤"
				var="formatedTotalPrice"/>
				<span>Total TTC : ${formatedTotalPrice}</span>
				<a role="button" href="<%=ctxPath%>/orders/validate.html" class="btn btn-success">Valider</a>
			</div>
		</li>
	</ul>
</c:otherwise>
</c:choose>
</div>

<%@include file="../_footer.jsp" %>