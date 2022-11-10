<%@include file="../_header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div class="container mt-5">
			<c:if test="${reclamation.status eq 'CLOSED'}">Cette réclamation est fermée, vous ne pouvez plus y répondre</c:if>
			<form class="form-group" action="${reclamation.id}.html" method="post">
	
				<div class="form-group">
					<label for="title" class="">Objet : </label> 
					<input id="title" name="title" class="form-control mb-3"
						placeholder="Titre de la réclamation" required type="text" value="${reclamation.title}">
				</div>
				
				<div class="form-group">
					<label for="orderId" class="">Commande problématique : </label>
					<select name="orderId" class="form-control mb-3">
					
					    <c:if test="${reclamation.orderId != null}">
	         				<option value="${ reclamation.orderId }">${ reclamation.orderId }</option>
	      				</c:if>
	      				
	       				<option value="">--Aucune--</option>
						<c:forEach items="${ orders }" var="order">
							<option value="${ order.id }">${ order.id }</option>    
						</c:forEach>
						
					</select>
				</div>
				
				<div class="form-group">
					<label for="description" class="">Description : </label>
					<textarea class="form-control mb-3" id="description" name="description" placeholder="Explication du problème" required 
						rows="6">${reclamation.description}</textarea>
				</div>
				<div>
					<c:if test="${reclamation.status eq 'WAITING'}">
						<button type="submit" class="btn btn-primary">Modifier</button>
					</c:if>
					<c:if test="${reclamation.status eq 'WAITING'}">
						<a href="${reclamation.id}/close.data" role="button" class="btn btn-danger">Clôturer la réclamation</a>
					</c:if>
				</div>
			</form>
		</div>
		
		<br/>
		<br/>
				
		<div class="container">
			<h2>Messages</h2>
			<div class="row clearfix">
				<div class="col-lg-12">
					<div class="card chat-app">
						<div class="chat">
							<div class="chat-history">
								<ul class="m-b-0">
								
									<c:forEach items="${ messages }" var="message">
										<li class="clearfix">
										
											<c:choose>
												<c:when test="${message.customer.role.equals('CLIENT')}">
											    	
													<div class="message-data">
														<span class="message-data-time">${ message.messageDate }</span> <br/>
														<span class="">${ message.customer.firstname } ${ message.customer.lastname }</span>
													</div>
													
													<div class="message my-message">
														${ message.content }
													</div>
											    	
												</c:when>
												<c:otherwise>
											    	
													<div class="message-data text-end">
														<span class="message-data-time">${ message.messageDate }</span> <br/>
														<span class="">${ message.customer.firstname } ${ message.customer.lastname }</span>
													</div>
													
													<div class="message other-message float-end">
														${ message.content }
													</div>										    	
											    	
												</c:otherwise>
											</c:choose>
											
										</li>
									</c:forEach>
									
								</ul>
							</div>
							
							
							<div class="chat-message clearfix">
								<form class="form-group" action="${reclamation.id}/message.html" method="post">
									<c:if test="${reclamation.status eq 'WAITING'}">
									<div class="input-group mb-0">
										<input id="message" name="message" type="text" class="form-control" placeholder="Entrez votre texte ici...">
										<button type="submit" class="btn btn-primary">Envoyer</button>
									</div>
									</c:if>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

<%@include file="../_footer.jsp" %>