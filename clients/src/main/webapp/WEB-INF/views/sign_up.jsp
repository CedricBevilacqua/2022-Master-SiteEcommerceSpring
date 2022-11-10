<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="_header.jsp" %>

<style>
input:placeholder-shown {
   font-style: italic;
   font-color : red;
}
</style>

	<div align="center">
		<h2 class="my-3">Créer un compte :</h2>
		<form:form action="signup.html" method="post" modelAttribute="client">
				<c:if test="${errorMessage != null}">
					<div class="alert alert-danger" role="alert"> ${errorMessage}</div>"
				</c:if>
				
			<table border="0" cellpadding="7" class="w-50">
				<tr>
					<td class="w-25"><form:label path="firstname">Prénom</form:label><span class="text-danger"> *</span></td>
					<td>
						<form:input path="firstname" class="form-control" placeholder= "Jane" />
						<form:errors path="firstname" cssClass="text-danger"/>
					</td>
				</tr>
				<tr>
					<td class="w-25"><form:label path="lastname">Nom</form:label><span class="text-danger"> *</span></td>
					<td>
						<form:input path="lastname" class="form-control"  placeholder= "Doe"/>
						<form:errors path="lastname" cssClass="text-danger"/>
					</td>
				</tr>
				<tr>
					<td class="w-25"><form:label path="mail">Email</form:label><span class="text-danger"> *</span></td>
					<td>
						<form:input type="email" path="mail" class="form-control"  placeholder= "username@domaine.com"/>
						<form:errors path="mail" cssClass="text-danger"/>
					</td>
				</tr>
				<tr>
					<td class="w-25"><form:label path="password">Password</form:label><span class="text-danger"> *</span></td>
					<td>
						<form:password showPassword="true" path="password" class="form-control"/>
						<form:errors path="password" cssClass="text-danger"/>
					</td>					
				</tr>
				<tr>
					<td class="w-25"><form:label path="numberHouse">Numéro de rue</form:label><span class="text-danger"> *</span></td>
					<td>
						<form:input path="numberHouse" type="number" class="form-control"  placeholder= "201"/>
						<form:errors path="numberHouse" cssClass="text-danger"/>						
					</td>
				</tr>
				<tr>
					<td class="w-25"><form:label path="street">Rue</form:label><span class="text-danger"> *</span></td>
					<td>
						<form:input path="street" class="form-control"  placeholder= "rue champs élysées" />
						<form:errors path="street" cssClass="text-danger"/>						
					</td>
				</tr>
				<tr>
					<td class="w-25"><form:label path="zipCode">Code postal</form:label><span class="text-danger"> *</span></td>
					<td>
						<form:input path="zipCode" type="number" class="form-control"  placeholder= "75001"/>		
						<form:errors path="zipCode" cssClass="text-danger"/>						
					</td>				
				</tr>
				<tr>
					<td class="w-25"><form:label path="town">Ville</form:label><span class="text-danger"> *</span></td>
					<td>
						<form:input path="town" class="form-control"  placeholder= "Paris"/>
						<form:errors path="town" cssClass="text-danger"/>
					</td>
				</tr>
				<tr>
					<td class="w-25"><form:label path="country">Pays</form:label><span class="text-danger"> *</span></td>
					<td>
						<form:input path="country" class="form-control"  placeholder= "France"/>
						<form:errors path="country" cssClass="text-danger"/>
					</td>
				</tr>
			</table>
				<div class="py-4">
				<input type="submit" value="Créer mon compte" class="btn btn-primary">
				</div>
		</form:form>
	</div>
<%@include file="_footer.jsp" %>




