<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<%@include file="../_header.jsp" %>

<div class="row d-flex">

<c:forEach items="${articles}" var="a">
	<div class="col-xxl-2 col-xl-3 col-xs-6">
		<div class="card" style="width: 18rem;">
		  <img src="https://static1.chronodrive.com${a.img}" class="card-img-top" alt="...">
		  <div class="card-body bg-light">
		    <h5 class="card-title">${a.name}</h5>
		    <div class="d-flex justify-content-between">
			    <button class="btn btn-primary add-to-cart" type="button" id="${a.id}">
			    	<i class="fa fa-cart-arrow-down" aria-hidden="true"></i> Ajouter au panier
			    </button>
			    <f:formatNumber value="${a.price / 100}"
				type="currency"
				currencySymbol="€"
				pattern="0.00¤"
				var="price"/>
			    <span>${price}</span>
		    </div>
		  </div>
		</div>
	</div>
</c:forEach>

</div>

<%@include file="../_footer.jsp" %>