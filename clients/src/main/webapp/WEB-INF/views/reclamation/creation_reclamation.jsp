<%@include file="../_header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div class="container my-5">
			<form class="form-group" action="create.html" method="post">
	
				<div class="form-group">
					<label for="title" class="">Objet : </label> 
					<input id="title" name="title" class="form-control mb-3"
						placeholder="Titre de la réclamation" required type="text">
				</div>
				
				<div class="form-group">
					<label for="orderId" class="">Commande problématique : </label>
					<select name="orderId" class="form-control mb-3">
	      				
	       				<option value="">--Aucune--</option>
						<c:forEach items="${ orders }" var="order">
							<option value="${ order.id }">${ order.id }</option>    
						</c:forEach>
						
					</select>
				</div>
				
				<div class="form-group">
					<label for="description" class="">Description : </label>
					<textarea class="form-control mb-3" id="description" name="description" placeholder="Explication du problème" required 
						rows="6"></textarea>
				</div>
	
			    <button type="submit" class="btn btn-primary">Envoyer</button>
			</form>
		</div>

<%@include file="../_footer.jsp" %>