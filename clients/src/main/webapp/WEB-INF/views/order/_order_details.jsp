<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<div class="my-2">
<ul class="list-group">
	<li class="list-group-item active">Détails de la commande</li>
	<c:forEach items="${orderLinesMap}" var="entry">
		<li class="list-group-item">
			<div class="justif-content-between">
				<div>${entry.value}x <c:out value="${entry.key.name}"/></div>
				<f:formatNumber value="${entry.key.price / 100 * entry.value}"
					type="currency"
					currencySymbol="€"
					pattern="0.00¤"
					var="price"/>
				<div>Montant : ${price}</div>
			</div>
		</li>
	</c:forEach>
	<li class="list-group-item">
		<button class="btn btn-small btn-danger hide-order-details" id="${orderId}">Masquer</button>
	</li>
</ul>
</div>