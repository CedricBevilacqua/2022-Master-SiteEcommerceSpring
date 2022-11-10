<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="../_header.jsp" %>

<div class="row d-flex">
<c:forEach items="${categories}" var="c">
	<div class="col-xxl-2 col-md-3 col-sm-4 col-xs-6">
		<div class="card" style="width: 18rem;">
		  <img src="../img/cat0${c.id}.jpg" class="card-img-top" alt="...">
		  <div class="card-body bg-light">
		    <h5 class="card-title">${c.name}</h5>
		    <div>
		    	<a role="button" href="<%=ctxPath%>/catalog/category/${c.id}.html" class="btn btn-success"><i class="fa fa-arrow-right" aria-hidden="true"></i> Voir les articles</a>
		    </div>
		  </div>
		</div>
	</div>
</c:forEach>
</div>

<%@include file="../_footer.jsp" %>