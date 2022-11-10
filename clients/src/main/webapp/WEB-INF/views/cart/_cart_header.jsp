<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<% 
String ctxPath = request.getContextPath(); 
%>

<c:choose>
<c:when test="${cartSize eq 0}">
	<li><div class="dropdown-item d-flex justify-content-center">Panier vide</div></li>
</c:when>
<c:otherwise>
	<c:forEach items="${cartContent}" var="entry">
		<li><div class="dropdown-item d-flex justify-content-between">
			<span>x${entry.value} <c:out value="${entry.key.name}"/></span>
			<f:formatNumber value="${entry.key.price * entry.value / 100}"
				type="currency"
				currencySymbol="€"
				pattern="0.00¤"
				var="entryPrice"/>
			<span>${entryPrice}</span>
		</div></li>
	</c:forEach>
		<li><hr class="dropdown-divider"></li>
		<li>
			<div class="d-flex justify-content-start dropdown-item">
				<f:formatNumber value="${totalPrice / 100}"
				type="currency"
				currencySymbol="€"
				pattern="0.00¤"
				var="formatedTotalPrice"/>
				<span>Total TTC : ${formatedTotalPrice}</span>
			</div>
		</li>
		<li>
			<div class="dropdown-item">
				<a role="button" href="<%=ctxPath%>/orders/validate.html" class="btn btn-success btn-block">Valider</a>
			</div>
		</li>
</c:otherwise>
</c:choose>