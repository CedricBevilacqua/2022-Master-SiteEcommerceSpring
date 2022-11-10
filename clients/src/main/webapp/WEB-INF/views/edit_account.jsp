<%@include file="_header.jsp" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<link rel="stylesheet" 
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

<body>

	<div>
		<h4 class="m-4 mt-5">Mes informations personnelles :</h4>
		<form:form action="edit.html" method="post" modelAttribute="clientToUpdate" class="col-6" >
				<c:if test="${errorMessage != null}">
					<div class="alert alert-danger text-center m2 mb-3" role="alert"> ${errorMessage}</div>
				</c:if>
                <c:if test="${successMessage != null}">
                    <div class="alert alert-success text-center m2 mb-3" role="alert"> ${successMessage}</div>
				</c:if>
				<c:if test="${mailUpdate != null}">
                    <div class="alert alert-success text-center m2 mb-3" role="alert"> ${mailUpdate}</div>
				</c:if>
			<table border="0" cellpadding="7" class="w-100">
				<tr>
					<td><form:label class= "pr-5" path="firstname">Prénom</form:label></td>
					<td class="col-12">
						<form:input path="firstname" class="form-control" />
						<form:errors path="firstname" cssClass="text-danger"/>
					</td>
				</tr>
				<tr>
					<td><form:label path="lastname">Nom</form:label></td>
					<td class="col-12">
						<form:input path="lastname" class="form-control" />
						<form:errors path="lastname" cssClass="text-danger"/>
					</td>
				</tr>
				<tr>
					<td><form:label path="mail">Email</form:label></td>
					<td class="col-12">
						<form:input type="email" path="mail" class="form-control" />
						<form:errors path="mail" cssClass="text-danger"/>
					</td>
				</tr>
				<tr>
					<td class="w-25"><form:label path="numberHouse">Numéro de rue</form:label></td>
					<td class="col-12">
						<form:input path="numberHouse" type="number" class="form-control"/>
						<form:errors path="numberHouse" cssClass="text-danger"/>						
					</td>
				</tr>
				<tr>
					<td><form:label path="street">Rue</form:label></td>
					<td class="col-12">
						<form:input path="street" class="form-control" />
						<form:errors path="street" cssClass="text-danger"/>						
					</td>
				</tr>
				<tr>
					<td><form:label path="zipCode">Code postal</form:label></td>
					<td class="col-12">
						<form:input path="zipCode" type="number" class="form-control"/>		
						<form:errors path="zipCode" cssClass="text-danger"/>						
					</td>				
				</tr>
				<tr>
					<td><form:label path="town">Ville</form:label></td>
					<td class="col-12">
						<form:input path="town" class="form-control"/>
						<form:errors path="town" cssClass="text-danger"/>
					</td>
				</tr>
				<tr>
					<td><form:label path="country">Pays</form:label></td>
					<td class="col-12">
						<form:input path="country" class="form-control"/>
						<form:errors path="country" cssClass="text-danger"/>
					</td>
				</tr>
			</table>
				<div class="py-4 px-5 text-center">
				<input type="submit" value="Sauvegarder" class="btn btn-primary">
				</div>
		</form:form>
	</div>
</body>
</html>




<%@include file="_footer.jsp" %>