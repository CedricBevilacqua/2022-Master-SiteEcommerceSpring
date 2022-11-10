<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<%@include file="../_header.jsp" %>

<div class="d-flex justify-content-center">
	<div class="col-7">
	<ul class="list-group">
		<li class="list-group-item active mt-5">
			Vos commandes
		</li>
		<c:forEach items="${orders}" var="order">
			<li class="list-group-item">
				<div>Commande n° : ${order.id}</div>
				<div>
				Date : <f:formatDate type = "both" 
         		value = "${order.createdOn}" />
         		</div>
				<div class="d-flex justify-content-between">
					<f:formatNumber value="${order.totalPrice / 100}"
					type="currency"
					currencySymbol="€"
					pattern="0.00¤"
					var="price"/>
					<div>Montant total : ${price}</div>
					<div><button class="btn btn-success show-order-details" id="${order.id}"><i class="fa fa-eye" aria-hidden="true"></i> Détails</button></div>
				</div>
				<div id="order-details-${order.id}"><!-- replaced with order details --></div>
			</li>
		</c:forEach>
	</ul>
	</div>
</div>

<%@include file="../_footer.jsp" %>
